#========================================== condicionales ========================================#
num_uno = int(input("ingesa un numero: "))
num_dos = int(input("ingresa otro numero: "))

resultado = num_dos + num_uno

if resultado < 50: 
    print("la suma de tus numeros de menor que 50")
elif resultado == 50:
    print("la suma de tus numeros es igual a 50")
else: 
    print("la suma de tus numeros es mayor a 50")

print("=====================================")
print("¡¡convertidor de numeros a palabras!!")
print("=====================================")
num = int(input("¿Cuás es el numero que deseas convertir?: "))

if num == 1:
    print('El numero es "uno"')
elif num == 2:
    print('El numero es "dos"')
elif num == 3:
    print('El numero es "tres"')
elif num == 4:
    print('El numero es "cuatro"')
elif num == 5:
    print('El numero es "cinco"')
else:
    print("Este programa solo puede convertir hasta el número 5")
print("Fin.")

#===================================== condicionales anidados ====================================#

print("=========")
print("Conversor")
print("========= \n")
#\n es para hacer un salto de linea

print("Menú de opciones: \n")

print('Escribe "1" para convertir de número a palabra')
print('Escribe "2" para convertir de palabra a número \n')

opcion = int(input("¿Cuál fue la opcion elegida?: "))

if opcion == 1:
    print("Conversor de número a palabra: \n")
    num = int(input("¿Cuál es el múmero que deseas convertir a palabra?: "))

    if num == 1:
        print('La palabra es "uno"')
    elif num == 2:
        print('La palabra es "dos"')
    elif num == 3:
        print('La palabra es "tres"')
    elif num == 4:
        print('La palabra es "cuatro"')
    elif num == 5:
        print('La palabra es "cinco"')
    else:
        print("El número seleccionado no está registrado")
elif opcion == 2:
    print("Conversor de palabra a número: \n")
    pal = input("¿Cuál es la palabra que deseas convertir a número?: ")
    pal = pal.lower()
    #.lower() sirve para que python coloque en minuscula toda letra que se ingrese
    if pal == "uno":
        print('El numero es "1"')
    elif pal == "dos":
        print('El numero es "2"')
    elif pal == "tres":
        print('El numero es "3"')
    elif pal == "cuatro":
        print('El numero es "4"')
    elif pal == "cinco":
        print('El numero es "5"')
    else:
        print("La palabra seleccionada no está registrada")
else:
    print("escoja un número entre 1 o 2 \n")
print("Fin.")

edad = int(input('ingresa tu edad: '))

if 0 < edad <= 100:
    if edad >= 18:
        print('eres mayor de edad')
    else:
        print('no eres mayor de edad')
else:
    print('no es valida tu edad')