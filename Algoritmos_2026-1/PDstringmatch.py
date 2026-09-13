def distancia(M, S, T, i, j):
    if i == 0:
        return (j, None)
    elif j == 0:
        return (i, None)
    elif S[0:i] == T[0:j]:
        return (distancia(M, S, T, i-1, j-1) if M[i][j] == -1 else M[i][j], "MATCH") 
    else:
        (uno, f) = distancia(M, S, T, i-1, j) if M[i][j] == -1 else (M[i][j], "DELETE")
        (dos, f) = distancia(M, S, T, i, j-1) if M[i][j] == -1 else (M[i][j], "INSERT")
        (tres, f) = distancia(M, S, T, i-1, j-1) if M[i][j] == -1 else (M[i][j], "BOTH")
        
        if tres <= uno and tres <= dos:
            return (1+tres, "BOTH")
        elif uno <= dos:
            return (1+uno, "DELETE")
        else:
            return (1+dos, "INSERT")
            

def distancia_edicion(S, T):
    n = len(S)
    m = len(T)
    M = [[-1 for i in range(m+1)] for i in range(n+1)]
    sec = []
    
    for i in range(n+1):
        for j in range(m+1):
            (valor, op) = distancia(M, S, T, i, j)
            M[i][j] = valor
            sec.append(op)
    
    return M, M[n][m], sec

S = input()
T = input()

(M, dist, sec) = distancia_edicion(S, T)

for i in M:
    print(*i)
print()
print("Distance = ", dist)
print()
"""
for i in sec:
    print(i)"""