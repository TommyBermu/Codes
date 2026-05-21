import math

def countingSortBits(A, r, shift):
    k = 1 << r 
    mask = k - 1 

    n = len(A)
    B = [0] * n
    C = [0] * k

    for j in range(n):
        digito = (A[j] >> shift) & mask
        C[digito] += 1

    for i in range(1, k):
        C[i] += C[i - 1]

    for j in range(n - 1, -1, -1):
        digito = (A[j] >> shift) & mask
        B[C[digito] - 1] = A[j]
        C[digito] -= 1
    
    print(B)
    return B


def radixSortBits(A, b, r):
    shift = 0
    for _ in range(0, b, r):
        A = countingSortBits(A, r, shift)
        shift += r

    return A


b = int(input())
a = [int(x) for x in input().split()]
r = b if b < math.log(len(a), 2) else math.trunc(math.log(len(a), 2))

radixSortBits(a, b, r)