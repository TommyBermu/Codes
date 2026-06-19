class Heap:
    def __init__(self, A):
        self.array = A
        self.heap_size = len(A)
    
    def left(self, i):
        return 2 * i + 1

    def right(self, i):
        return 2 * i + 2

    def heapify(self, i):
        pass

    def buildHeap(self):
        for i in range(len(self.array) // 2 - 1, -1, -1):
            self.heapify(i)
            print(*self.array)

class MaxHeap(Heap):
    def heapify(self, i):
        l = self.left(i)
        r = self.right(i)
        largest = i

        if l < self.heap_size and self.array[l] > self.array[largest]:
            largest = l
        
        if r < self.heap_size and self.array[r] > self.array[largest]:
            largest = r

        if largest != i:
            self.array[i], self.array[largest] = self.array[largest], self.array[i]
            self.heapify(largest)
        
class MinHeap(Heap):
    def heapify(self, i):
        l = self.left(i)
        r = self.right(i)
        smallest = i

        if l < self.heap_size and self.array[l] < self.array[smallest]:
            smallest = l
        
        if r < self.heap_size and self.array[r] < self.array[smallest]:
            smallest = r

        if smallest != i:
            self.array[i], self.array[smallest] = self.array[smallest], self.array[i]
            self.heapify(smallest)

            
arreglo = [int(x) for x in input().split()]

myHeap = MaxHeap(arreglo) if arreglo[0] < arreglo[1] else MinHeap(arreglo)

myHeap.buildHeap()