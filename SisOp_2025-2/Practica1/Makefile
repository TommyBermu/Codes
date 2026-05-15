CC = gcc
CFLAGS = -Wall -g
LDFLAGS = -lrt -lpthread

# Ejecutables
EXEC_DATASET = datasetToBin
EXEC_HASH = createBinHashTable
EXEC_SERVER = motor_busqueda
EXEC_CLIENT = p1-dataProgram

.PHONY: all clean

# Compilar, preparar base de datos, ejecutar y limpiar
all: compilar preparar ejecutar limpiar

compilar:
	@echo "Compilando programas..."
	@$(CC) $(CFLAGS) datasetToBin.c -o $(EXEC_DATASET)
	@$(CC) $(CFLAGS) createBinHashTable.c -o $(EXEC_HASH)
	@$(CC) $(CFLAGS) motor_busqueda.c -o $(EXEC_SERVER) $(LDFLAGS)
	@$(CC) $(CFLAGS) p1-dataProgram.c -o $(EXEC_CLIENT) $(LDFLAGS)
	@echo ""

preparar:
	@if [ ! -f songs.bin ]; then \
		echo "Convirtiendo CSV a binario..."; \
		./$(EXEC_DATASET); \
		echo ""; \
	fi
	@if [ ! -f hashTableSongs.bin ]; then \
		echo "Creando tabla hash..."; \
		./$(EXEC_HASH); \
		echo ""; \
	fi

ejecutar:
	@echo "Iniciando servidor..."
	@./$(EXEC_SERVER) &
	@sleep 2
	@echo "Iniciando cliente..."
	@echo ""
	@./$(EXEC_CLIENT)

limpiar:
	@echo ""
	@echo "Limpiando..."
	@rm -f $(EXEC_DATASET) $(EXEC_HASH) $(EXEC_SERVER) $(EXEC_CLIENT)
	@rm -f /dev/shm/song_search_shm /dev/shm/sem.sem_*
	@echo "Listo"

clean:
	@rm -f $(EXEC_DATASET) $(EXEC_HASH) $(EXEC_SERVER) $(EXEC_CLIENT)
	@rm -f songs.bin hashTableSongs.bin
	@rm -f /dev/shm/song_search_shm /dev/shm/sem.sem_*
	@echo "Todo limpio"
