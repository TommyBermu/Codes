CC = gcc
CFLAGS = -Wall -g
LDFLAGS = -lrt -lpthread

# Ejecutables
EXEC_BINHASH = createBinAndHashTable
EXEC_SERVER = servidor_busqueda
EXEC_CLIENT = p2-dataProgram

.PHONY: all clean

# Compilar, preparar base de datos, ejecutar y limpiar
all: compilar preparar ejecutar limpiar

compilar:
	@echo "Compilando programas..."
	@$(CC) $(CFLAGS) createBinAndHashTable.c -o $(EXEC_BINHASH)
	@$(CC) $(CFLAGS) servidor_busqueda.c -o $(EXEC_SERVER) $(LDFLAGS)
	@$(CC) $(CFLAGS) p2-dataProgram.c -o $(EXEC_CLIENT) $(LDFLAGS)
	@echo ""

preparar:
	@if [ ! -f songs.bin ]; then \
		echo "Convirtiendo CSV a Binario y creando tabla Hash..."; \
		./$(EXEC_BINHASH); \
		echo ""; \
	fi

ejecutar:
	@echo "Iniciando servidor..."
	@./$(EXEC_SERVER) &
	@echo "Iniciando cliente..."
	@./$(EXEC_CLIENT)

limpiar:
	@echo ""
	@echo "Limpiando..."
	@rm -f $(EXEC_BINASH)
	@rm -f /dev/shm/song_search_shm /dev/shm/sem.sem_*
	@echo "Listo"

clean:
	@rm -f $(EXEC_BINHASH) $(EXEC_SERVER) $(EXEC_CLIENT)
	@rm -f songs.bin hashTableSongs.bin
	@rm -f /dev/shm/song_search_shm /dev/shm/sem.sem_*
	@echo "Todo limpio"
