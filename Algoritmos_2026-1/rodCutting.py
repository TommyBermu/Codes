p = [1,5,8,9,10,17,17,20,24,30]

def cut(optimos, n):
    if optimos[n] >= 0:
        return optimos[n]

    if n == 0:
        q = 0
    else:
        q = -1
        for i in range(n):
            q = max(cut(optimos, n-i-1) + p[i], q)
    optimos[n] = q
    return q

def rod_cutting(n):
    optimos = [-1 for i in range(n+1)]

    return cut(optimos, n)

for i in range(11):
    print(rod_cutting(i))