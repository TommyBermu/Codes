def radixSort(A, d):
    k = 10  # se asume base 10
    
    for i in range(1, d + 1):
        A = countingSortByDigit(A, i, k)
    
    return A


def countingSortByDigit(A, digit, k):
    n = len(A)
    B = [0] * n
    C = [0] * k
    
    for j in range(n):
        dig = (A[j] // (10 ** (digit - 1))) % 10
        C[dig] += 1
    
    for i in range(1, k):
        C[i] += C[i - 1]
    
    for j in range(n - 1, -1, -1):
        dig = (A[j] // (10 ** (digit - 1))) % 10
        B[C[dig] - 1] = A[j]
        C[dig] -= 1
    
    return B