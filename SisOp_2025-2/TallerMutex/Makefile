TARGET = mutex

SRC = mutex.c

CC = gcc

CFLAGS = -lpthread


$(TARGET): $(SRC)
	$(CC) $(CFLAGS) -o $(TARGET) $(SRC)

run: $(TARGET)
	./$(TARGET)
	rm -f $(TARGET) *.o
