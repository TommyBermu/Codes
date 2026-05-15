#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h>
#include <arpa/inet.h>

// Estructura para guardar los datos crudos de /proc/stat
struct cpu_raw {
    long user;
    long nice;
    long system;
    long idle;
    long iowait;
    long irq;
    long softirq;
    long steal;
};


int get_cpu_stats(struct cpu_raw *cpu){
    FILE *file = fopen("/proc/stat", "r");
    if (file == NULL) {
        perror("Failed to open /proc/stat");
        return -1;
    }

    char buffer[1024];
    if (fgets(buffer, sizeof(buffer), file)) {
        sscanf(buffer, "cpu  %ld %ld %ld %ld %ld %ld %ld %ld",
               &cpu->user,
               &cpu->nice,
               &cpu->system,
               &cpu->idle,
               &cpu->iowait,
               &cpu->irq,
               &cpu->softirq,
               &cpu->steal);
    } else {
        perror("Failed to read /proc/stat");
        fclose(file);
        return -1;
    }

    fclose(file);
    return 0;
}


int main(int argc, char *argv[]){

    if(argc <4 ){
        printf("Uso: ./agent_cpu <ip_recolector> <puerto> <ip_logica_agente>\n");
        printf("Ejemplo: ./agent_cpu 192.168.1.100 5000 192.168.1.101\n");
    }

    char *server_ip=argv[1];
    int server_port=atoi(argv[2]);
    char *ip_logica=argv[3];

    struct sockaddr_in server_addr;
    
    struct cpu_raw prev_cpu, curr_cpu;

    while(1){

        //Creación de socket
        int sock=socket(AF_INET, SOCK_STREAM, 0);
        if(sock<0){
            perror("Error al crear el socket");
            exit(1);
        }

        server_addr.sin_family=AF_INET;
        server_addr.sin_port=htons(server_port);
        //Indicar dirección ip de destino
        if(inet_pton(AF_INET, server_ip, &server_addr.sin_addr)<=0){
            perror("Dirección IP inválida");
            close(sock);
            exit(1);
        }

        if(connect(sock, (struct sockaddr *)&server_addr, sizeof(server_addr))<0){
            perror("Error en connect, intentando de nuevo en 2 segundos...");
            close(sock);
            sleep(2);
            continue;
        }

        //Calculos de cpu 
        get_cpu_stats(&prev_cpu);
        sleep(1);
        get_cpu_stats(&curr_cpu);

        long prev_idle = prev_cpu.idle + prev_cpu.iowait;
        long curr_idle = curr_cpu.idle + curr_cpu.iowait;

        long prev_non_idle = prev_cpu.user + prev_cpu.nice + prev_cpu.system + prev_cpu.irq + prev_cpu.softirq + prev_cpu.steal;
        long curr_non_idle = curr_cpu.user + curr_cpu.nice + curr_cpu.system + curr_cpu.irq + curr_cpu.softirq + curr_cpu.steal;

        long prev_total = prev_idle + prev_non_idle;
        long curr_total = curr_idle + curr_non_idle;

        long total_diff = curr_total - prev_total;
        long idle_diff = curr_idle - prev_idle;

        if(total_diff == 0){
            total_diff = 1; // Evitar división por cero
        }

        //Porcentajes
        double cpu_total_pct = (double)(total_diff - idle_diff) / total_diff * 100.0;
        double user_pct = (double)(curr_cpu.user - prev_cpu.user) / total_diff * 100.0;
        double sys_pct = (double)(curr_cpu.system - prev_cpu.system) / total_diff * 100.0;
        double idle_pct = (double)(idle_diff) / total_diff * 100.0;

        //Poner formato
        char buffer[256];
        snprintf(buffer, sizeof(buffer), 
                 "CPU;%s;%.2f;%.2f;%.2f;%.2f", 
                 ip_logica, cpu_total_pct, user_pct, sys_pct, idle_pct);
        
        //Enviar datos al recolector
        int bytes_sent=send(sock, buffer, strlen(buffer), 0);
        if(bytes_sent<0){
            perror("Error en send");
        } else {
            printf("Datos enviados: %s\n", buffer);

        }

        close(sock);
        sleep(1);
    }

    return 0;

}