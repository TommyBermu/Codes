import random

class MaxHeap:
    def __init__(self, A):
        self.array = A
        self.heap_size = len(A)

    def left(self, i):
        return 2 * i + 1

    def right(self, i):
        return 2 * i + 2

    def max_heapify(self, i):
        l = self.left(i)
        r = self.right(i)
        largest = i

        if l < self.heap_size and self.array[l] > self.array[largest]:
            largest = l
        
        if r < self.heap_size and self.array[r] > self.array[largest]:
            largest = r

        if largest != i:
            self.array[i], self.array[largest] = self.array[largest], self.array[i]
            self.max_heapify(largest)

    def build_heap(self):
        self.heap_size = len(self.array)
        for i in range(len(self.array) // 2 - 1, -1, -1):
            self.max_heapify(i)
            print(*self.array)
        

class MinHeap:
    def __init__(self, A):
        self.array = A
        self.heap_size = len(A)

    def left(self, i):
        return 2 * i + 1

    def right(self, i):
        return 2 * i + 2

    def min_heapify(self, i):
        l = self.left(i)
        r = self.right(i)
        smallest = i

        if l < self.heap_size and self.array[l] < self.array[smallest]:
            smallest = l
        
        if r < self.heap_size and self.array[r] < self.array[smallest]:
            smallest = r

        if smallest != i:
            self.array[i], self.array[smallest] = self.array[smallest], self.array[i]
            self.min_heapify(smallest)

    def build_heap(self):
        self.heap_size = len(self.array)
        for i in range(len(self.array) // 2 - 1, -1, -1):
            self.min_heapify(i)
            print(*self.array)
            
arreglo = [int(x) for x in input().split()]

myHeap = MaxHeap(arreglo) if arreglo[0] < arreglo[1] else MinHeap(arreglo)

myHeap.build_heap()