class hashTable:
    def __init__(self, m=101):
        self.m = m
        self.table = [[] for _ in range(m)]

    def hash_function(self, key): # método de división
        return key % self.m

    # def hash_function(self, key, A=0.6180339887): # método de multiplicación
    #     return math.floor(self.m * ((key * A) % 1))

    def insert(self, key, value):
        slot = self.hash_function(key)
        for i, (k, v) in enumerate(self.table[slot]):
            if k == key:
                self.table[slot][i] = (key, value)
                return
        self.table[slot].append((key, value))

    def search(self, key):
        slot = self.hash_function(key)
        for k, v in self.table[slot]:
            if k == key:
                return v
        return None

    def delete(self, key):
        slot = self.hash_function(key)
        self.table[slot] = [(k, v) for k, v in self.table[slot] if k != key]

ht = hashTable()
ht.insert(123, "Julian")
ht.insert(456, "Mario")

print(ht.search(123))
ht.delete(123)
print(ht.search(123))