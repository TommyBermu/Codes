def mergeSort(arr, p, r):
    n = r - p + 1
    if n > 1:
        tamIzq = n // 3
        if tamIzq == 0:
            tamIzq = 1

        q = p + tamIzq
        
        print(*arr[p:q])
        mergeSort(arr, p ,q-1)
        
        print(*arr[q:r+1])
        mergeSort(arr, q, r)
        
        merge(arr, p, q, r)
        print(*arr[p:r+1])
    return arr
        
def merge(arr, p, q, r):
    L = arr[p:q]
    R = arr[q:r+1]
    
    i, j, k = 0, 0, p
    
    nLeft = len(L)
    nRight = len(R)
    

    
    while i < nLeft and j < nRight:
        if L[i] <= R[j]:
            arr[k] = L[i]
            i = i+1
        else:
            arr[k] = R[j]
            j = j+1
        k = k+1
    
    while i < nLeft:
        arr[k] = L[i]
        i = i+1
        k = k+1
        
    while j < nRight:
        arr[k] = R[j]
        j = j+1
        k = k+1
        
arreglo = [int(x) for x in input().split()]

arreglo = mergeSort(arreglo, 0, len(arreglo)-1)