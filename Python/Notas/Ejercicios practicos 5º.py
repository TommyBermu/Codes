#===================================== ejercicio practico #1 =====================================#

lista1 = list(range(1,51))

for i in lista1:
    print(i, end="-")

lista2 = []
count = 1
while count <= 50:
    lista2.append(count)
    print(count, end="-")
    count += 1

#===================================== ejercicio practico #2 =====================================#

lista = list(range(1,11))
print(lista)
valor = int(input('ingresa un miltiplicador: '))

for indice, i in enumerate(lista):
    lista[indice] *= valor

print(lista)

#===================================== ejercicio practico #3 =====================================#

lista = []

while True:
    numero = int(input('ingrese un numero: '))
    if numero == 0:
        break
    else:
        lista.append(numero)
lista.sort()
print(lista)

salir = False
while not salir:
    numero = int(input('ingrese un numero: '))
    if numero == 0:
        salir = True
    else:
        lista.append(numero)
lista.sort()
print(lista)

#===================================== ejercicio practico #4 =====================================#

a = int(input('de donde empezar a sumar: '))
b = int(input('hasta donde terminar de sumar:: '))
suma = 0

for i in range(a,b+1):
    if i % 2 == 0:
        suma += i
print(suma)

#===================================== ejercicio practico #5 =====================================#

num = int(input('ingresa un numero para sacarle el factorial: '))

while num < 0:
    num = int(input('ingresa un numero positvo: '))

fact = 1

for i in range(1, num+1):
    fact *= i
print(fact)

#===================================== ejercicio practico #6 =====================================#

lista = []
num = int(input('ingresa un numero para consegir su tabla de multiplicar hasta el 10: '))

for i in range(1,11):
    lista.append(i*num)
print(lista)

#===================================== ejercicio practico #7 =====================================#
import random

val = random.randint(0, 100)
num = int(input('ingresa un numero ente 0 y 100: '))
cont = 1

while num != val:
    if num > val:
        num = int(input('el numero es menor, ingresa otro numero: '))
        cont += 1
    else: 
        num = int(input('el numero es mayor, ingresa otro numero: '))
        cont += 1
print(f'has ganado, el numero es {val} y has acertado en {cont} intentos')

#===================================== ejercicio practico #8 =====================================#
# continuidad del ejercicio practico 3.5

saldo = 1000
while True:
    print("1. Ingresar dinero en la cuenta")
    print("2. Retirar dinero de la cuenta")
    print("3. Mostrar saldo disponible")
    print("4. Salir")
    opcion = int(input('digite el número de la opción que desea: '))
    if opcion == 1:
        saldo += float(input('Digite la cantidad de dinero que desea ingresar a la cuenta: '))
        print(f'el saldo total de la cuenta ahora es de ${saldo}')
    elif opcion == 2:
        saldo -= float(input('Digite la cantidad de dinero que desea retirar a la cuenta: '))
        if saldo < 0:
            print('no tiene suficiente dinero en la cuenta')
        else:
            print(f'el saldo total de la cuenta ahora es de ${saldo}')
    elif opcion == 3:
        print(f'el saldo total de la cuenta es de ${saldo}')
    elif opcion == 4:
        print('Saliendo de la cuenta')
        break
    else:
        print('\t digite una ocpión valida\n ')

#===================================== ejercicio practico #9 =====================================#

frase = input('escribe una frase: ')
frase2 = ""
for i in frase:
    if i != " ":
        frase2 += i
print(frase2)
print(f'{frase} tiene {len(frase2)} caracteres')

#===================================== ejercicio practico #10 ====================================#

frase = input('escribe una frase: ')
lista = []
for i in frase:
    if i not in lista:
        lista.append(i)
print(lista)

#===================================== ejercicio practico #11 ====================================# 

contactos = {
    'Manuel': 3154589667,
    'Sara': 3145826694
}

while True:
    print('\n \t Menú \n')
    print('1. Nuevo contacto')
    print('2. Borrar contacto')
    print('3. Ver contactos existentes')
    print('4. Salir \n')

    opcion = int(input('Digite el número de la opcion que desea: '))

    if opcion == 1:
        print('\n \t Nuevo contacto \n')
        ncont = input('Ingrese el nombre del nuevo contacto: ').capitalize()
        while ncont in contactos:
            print(f'\nEl nombre {ncont} ya se encuentra agendado, elige otro')
            ncont = input('\nIngrese el nombre del nuevo contacto: ').capitalize()
        contactos[ncont] = int(input(f'\nIngrese el número de {ncont}: '))
    elif opcion == 2:
        print('\n \t Eliminar un contacto \n')
        mcont = input('Ingrese el nombre del contacto a eliminar: ').capitalize()
        if mcont in contactos:
            del(contactos[mcont])
            print(f'\nEliminando a {mcont}')
        else:
            print('\nEl contacto no se encuentra')
    elif opcion == 3:
        print('\n \t Contactos agendados \n')
        for i in contactos:
            print(i + ':',contactos[i])
    elif opcion == 4:
        print('\nSaliendo de la agenda')
        break
    else:
        print('\nOpción no valida')