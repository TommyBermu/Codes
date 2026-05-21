def bucketSort(A):
    n = len(A)
    B = [[] for _ in range(n)]
    

    for i in range(n):
        bucket_idx = int(n * A[i])
        if bucket_idx == n:
            bucket_idx = n - 1
        B[bucket_idx].append(A[i])

    for i in range(n):
        insertionSort(B[i])
    
    result = []
    for bucket in B:
        result.extend(bucket)
    
    return result


def insertionSort(arr):
    for i in range(1, len(arr)):
        key = arr[i]
        j = i - 1
        while j >= 0 and arr[j] > key:
            arr[j + 1] = arr[j]
            j -= 1
        arr[j + 1] = key