def partition(A, p, r, pivot):
    match pivot:
        case "FIRST":
            x = A[p]
            A[p], A[r] = A[r], A[p]
        case "LAST":
            x = A[r]
        case "MIDDLE":
            x = A[(r+p)//2]
            A[(r+p)//2], A[r] = A[r], A[(r+p)//2]

    print(x)
    i = p - 1
    for j in range(p, r):
        if A[j] <= x:
            i = i + 1
            A[i], A[j] = A[j], A[i]
    A[i + 1], A[r] = A[r], A[i + 1]
    return i + 1

def quicksort(A, p, r, pivot):
    if p < r:
        q = partition(A, p, r, pivot)
        quicksort(A, p, q - 1, pivot)
        quicksort(A, q + 1, r, pivot)


A = [int(x) for x in input().split()]
quicksort(A, 0, len(A) - 1, input())
print(A)