#=========================================== ciclos while ========================================#
count = 0

while count <= 10:
    print (count)
    count += 1

import math  

numero = int(input('ingrese un numero: '))

while numero <= 0:
    print('no se pueden digitar número negativos o cero')
    numero = int(input('ingrese un numero: '))
print(f'la raiz cuadrada del numero es {(math.sqrt(numero))}')
#============================================ ciclos for =========================================#

foods = ['apples','bananas','cheese','bread','milk']

#este for recorre todos los elementos de una coleccion o cadena,
#por lo que hace el mismo numero de ciclos que de elementos en la cadena o coleccion

for i in foods:
    print(i) #i es una variable iteradora que solo es llamada y usada en el bucle
    for e in i:
        print(e)

coleccion = {
    'alejandro': 22,
    'maria': 23,
    'jose': 45,
    'luis': 12
}

for i in coleccion:
    print(f'{i} --> {coleccion[i]}')

for clave, valor in coleccion.items():
    print(f'{clave} --> {valor}')

for number in range(2,8): #8-2 = 6 repeticiones del ciclo
    print('hola')

coleccion = 'alejandro'

for i in coleccion:
    print(f'{i}', end="") # el end sirve para que cada "i" se escriba en la misma linea


cantidad = 0
mail = input('ingrese su correo: ')

for i in mail:
    if i =='@':
        cantidad += 1
    
if cantidad >= 1:
    print('mail correcto')
else: 
    print('mail incorrecto, no tiene arroba')
#======================================== break y continue =======================================#

#break sirve para que en casos especificos dentro de un codigo, se deje de ejecutar ese codigo
contador = 0
while contador < 10:
    contador += 1
    if contador == 5 :
        break
    print(contador)

for i in range(8):
    if i == 5:
        break
    print(i)


#continue sirve para que siga ejecutando en resto del codigo sin incluir esa condicion
contador = 0
while contador < 10:
    contador += 1
    if contador == 5:
        continue
    print(contador)

for i in range(8):
    if i == 5:
        continue
    print(i)