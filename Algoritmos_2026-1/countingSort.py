def countingSort(A, k):
    C, B = [0] * (k + 1), [0] * len(A)

    for j in range(len(A)):
        C[A[j]] += 1

    for i in range(1, k+1):
        C[i] += C[i-1]

    for j in range(len(A) - 1, -1, -1):
        B[C[A[j]] -1 ] = A[j]
        C[A[j]] -= 1

    return B

random_list = [1, 10, 4, 5, 8, 4, 5, 7, 7, 7, 10, 3, 3, 10, 6, 7, 8, 8, 6, 6]
print(random_list)

ordered_list = countingSort(random_list, 10)

print(ordered_list)