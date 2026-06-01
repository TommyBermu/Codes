import math

class tablaHash:
    def __init__(self, m, a):
        self.m = m
        self.a = a
        self.table = [[] for _ in range(m)]

    def hashFunction(self, key): # método de multiplicación
        key = math.floor(self.m * ((key * self.a) % 1))
        print(key)
        return key
    
    def insert(self, key):
        slot = self.hashFunction(key)
        self.table[slot].append(key)

K = int(input())

A = float(input())

MT = int(input())

ht = tablaHash(MT, A)

for _ in range(K):
    ht.insert(int(input()))