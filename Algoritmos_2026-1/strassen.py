def strassen(A, B):
    n = len(A)
    
    # Caso base
    if n == 1:
        return [[A[0][0] * B[0][0]]]
    
    # Dividir matrices en 4 submatrices
    mid = n // 2
    
    A11 = [row[:mid] for row in A[:mid]]
    A12 = [row[mid:] for row in A[:mid]]
    A21 = [row[:mid] for row in A[mid:]]
    A22 = [row[mid:] for row in A[mid:]]
    
    B11 = [row[:mid] for row in B[:mid]]
    B12 = [row[mid:] for row in B[:mid]]
    B21 = [row[:mid] for row in B[mid:]]
    B22 = [row[mid:] for row in B[mid:]]
    
    # Funciones auxiliares
    def add(X, Y):
        return [[X[i][j] + Y[i][j] for j in range(len(X[0]))] for i in range(len(X))]
    
    def sub(X, Y):
        return [[X[i][j] - Y[i][j] for j in range(len(X[0]))] for i in range(len(X))]
    
    # Las 10 matrices S (sumas/restas)
    S1  = sub(B12, B22)
    S2  = add(A11, A12)
    S3  = add(A21, A22)
    S4  = sub(B21, B11)
    S5  = add(A11, A22)
    S6  = add(B11, B22)
    S7  = sub(A12, A22)
    S8  = add(B21, B22)
    S9  = sub(A11, A21)
    S10 = add(B11, B12)
    
    # Las 7 multiplicaciones recursivas
    P1 = strassen(A11, S1)
    P2 = strassen(S2,  B22)
    P3 = strassen(S3,  B11)
    P4 = strassen(A22, S4)
    P5 = strassen(S5,  S6)
    P6 = strassen(S7,  S8)
    P7 = strassen(S9,  S10)
    
    # Las 4 submatrices resultado
    C11 = add(sub(add(P5, P4), P2), P6)
    C12 = add(P1, P2)
    C21 = add(P3, P4)
    C22 = sub(sub(add(P5, P1), P3), P7)
    
    # Combinar en la matriz resultado
    C = []
    for i in range(mid):
        C.append(C11[i] + C12[i])
    for i in range(mid):
        C.append(C21[i] + C22[i])
    
    return C


# Prueba
A = [[1, 2], [3, 4]]
B = [[5, 6], [7, 8]]

resultado = strassen(A, B)
for fila in resultado:
    print(fila)
# [19, 22]
# [43, 50]