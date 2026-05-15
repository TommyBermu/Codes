def insertionSort(arr):
    for j in range(1, len(arr)):
        key = arr[j]
        
        i = j-1
        while i >= 0 and arr[i] > key:
            arr[i + 1] = arr[i]
            i = i -1
        arr[i+1] = key
    
    return arr


orden = input().split("<")
numDic = dict()
letDic = dict()
for i in range(len(orden)):
    numDic[orden[i]] = i # mapea numero = letra
    letDic[i] = orden[i] # mapea letra = numero

arreglo = input().split()
arreglo.append(input())

arrNum = [numDic[x] for x in arreglo]
arrNum = insertionSort(arrNum)

arreglo = [letDic[x] for x in arrNum]
print(arreglo)