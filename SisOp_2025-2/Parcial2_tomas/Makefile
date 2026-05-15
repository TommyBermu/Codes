CC = gcc

AGENT_CPU = agent_cpu
AGENT_MEM = agent_mem
COLLECTOR = collector
VISUALIZER = visualizer

all: client server

client: $(AGENT_CPU) $(AGENT_MEM)

server: $(COLLECTOR) $(VISUALIZER)

$(AGENT_CPU): agent_cpu.c
	$(CC) $(CFLAGS) agent_cpu.c -o $(AGENT_CPU)

$(AGENT_MEM): agent_mem.c
	$(CC) $(CFLAGS) agent_mem.c -o $(AGENT_MEM)

$(COLLECTOR): collector.c
	$(CC) $(CFLAGS) collector.c -o $(COLLECTOR)

$(VISUALIZER): visualizer.c
	$(CC) $(CFLAGS) visualizer.c -o $(VISUALIZER)

clear:
	rm -f $(AGENT_CPU) $(AGENT_MEM) $(COLLECTOR) $(VISUALIZER)
	ipcrm -a

.PHONY: all client server clean clean-ipc
