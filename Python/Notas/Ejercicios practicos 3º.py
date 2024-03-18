#===================================== ejercicio practico #1 =====================================#
num_uno = int(input("Introduce un número entero: "))
num_dos = int(input("Introduce otro número entero: "))

if num_uno % 2 == 1:
    if num_dos % 2 == 1:
        print("Ambos números son impares" )
    else:
        print(f"El número {num_uno} es impar y el número {num_dos} es par")
else:
    if num_dos % 2 == 1:
        print(f"El número {num_uno} es par y el número {num_dos} es impar" )
    else:
        print(f"Ambos números son pares")

if num_uno % 2 == 1 and num_dos % 2 == 1:
    print("Ambos números son impares" )
elif num_uno % 2 == 1 and num_dos % 2 == 0:
    print(f"El número {num_uno} es impar y el número {num_dos} es par")
elif num_uno % 2 == 0 and num_dos % 2 == 1:
    print(f"El número {num_uno} es par y el número {num_dos} es impar" )
else:
    print(f"Ambos números son pares")

#===================================== ejercicio practico #2 =====================================#
#es el mismo que el 1,3 xd
#===================================== ejercicio practico #3 =====================================#

letra = input("Introduce una letra: ").lower()

if letra == "a" or letra == "e" or letra == "i" or letra == "o" or letra == "u":
    print('la letra es una vocal')
else:
    print('la letra no es una vocal')

#===================================== ejercicio practico #4 =====================================#

print("Suma (s)")
print("Resta (r)")
print("Multiplicación (p , m)")
print("División (d)")

operacion = input("Introduce el tipo de operacion a realizar segun el menú anterior: ").lower()
num1 = float(input("Introduce un número: "))
num2 = float(input("Introduce el otro número: "))

if operacion == "s":
    numero = num1 + num2
    print(f'el resultado de la suma es {numero}')
elif operacion == "r":
    numero = num1 - num2
    print(f'el resultado de la resta es {numero}')
elif operacion == "p" or operacion == "m":
    numero = num1 * num2
    print(f'el resultado de la multiplicación es {numero}')
elif operacion == "d":
    numero = num1 / num2
    print(f'el resultado de la divición es {numero}')
else:
    print('introduce una opción valida')

#===================================== ejercicio practico #5 =====================================#
saldo = 1000

print("1. Ingresar dinero en la cuenta")
print("2. Retirar dinero de la cuenta")
print("3. Mostrar saldo disponible)")
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
else:
    print('digite una ocpión valida')