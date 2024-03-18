#===================================== ejercicio practico #1 =====================================#
print("¿Quieres saber cuantos dias de vacaciones tienes?, completa el siguiente formulario \n")

nombre = input("escriba su nombre: ")
clave = int(input("\n Ahora digite el número de clave correspondiente a su departamento: "))
años = int(input("\n Por último, escriba la cantidad de años completos que lleva en la empresa: "))

if clave == 1:
    if años == 1:
        print("\n", nombre + ", Tienes 6 dias de vacaciones") 
    elif años >= 2 and años <= 6:
        print("\n", nombre + ", Tienes 14 dias de vacaciones")
    elif años >= 7:
        print("\n", nombre + ", Tienes 20 dias de vacaciones")
    else:
        print("\n", nombre + ", No tienes derecho a vacaciones")
elif clave == 2:
    if años == 1:
        print("\n", nombre + ", Tienes 7 dias de vacaciones") 
    elif años >= 2 and años <= 6:
        print("\n", nombre + ", Tienes 15 dias de vacaciones")
    elif años >= 7:
        print("\n", nombre + ", Tienes 22 dias de vacaciones")
    else:
        print("\n", nombre + ", No tienes derecho a vacaciones")
elif clave == 3:
    if años == 1:
        print("\n", nombre + ", Tienes 10  dias de vacaciones") 
    elif años >= 2 and años <= 6:
        print("\n", nombre + ", Tienes 20 dias de vacaciones")
    elif años >= 7:
        print("\n", nombre + ", Tienes 30 dias de vacaciones")
    else:
        print("\n", nombre + ", No tienes derecho a vacaciones")
else:
    print("\n Digite un número de clave valido")
print("\n Fin.")

#===================================== ejercicio practico #2 =====================================#

print("====================================================")
print("Programa para determinar si un número es par o impar")
print("====================================================")

num_uno = int(input("Introduce un número entero: "))

if (num_uno % 2) == 1:
    print("El número", num_uno, "es impar" )
elif (num_uno % 2) == 0:
    print("El número", num_uno, "es par" )
print("Fin.")

#===================================== ejercicio practico #3 =====================================#

print("************************************************")
print("Programa para saber cual es el número mas grande")
print("************************************************  \n")

num_uno = int(input("Introduce el primer número: "))
num_dos = int(input("\n Introduce el segundo número: "))
num_tres = int(input("\n Introduce el tercer número: "))

if num_uno > num_dos and num_uno > num_tres:
    print("El numero", num_uno, "es el mas grande de los tres")
elif num_dos > num_tres:
    print("El numero", num_dos, "es el mas grande de los tres")
else:
    print("El numero", num_tres, "es el mas grande de los tres")

#===================================== ejercicio practico #4 =====================================#

print("*********************************")
print("Calcualdora con una sola variable")
print("********************************* \n")

print("1. Suma")
print("2. Resta")
print("3. Multiplicación")
print("4. División")
print("5. División entera")
print("6. Exponente")
print("7. Modulo o resto \n")

numero = int(input("Introduce la opción deseada: "))

if numero == 1:
    print("Elegiste la suma \n")
    numero = int(input("\n Escribe el primer número: "))
    numero += int(input("\n Escribe el segundo número: "))
    print("El resultado de la suma es", numero)

elif numero == 2:
    print("Elegiste la resta \n")
    numero = int(input("\n Escribe el primer número: "))
    numero -= int(input("\n Escribe el segundo número: "))
    print("El resultado de la resta es", numero)

elif numero == 3:
    print("Elegiste la multiplicación \n")
    numero = int(input("\n Escribe el primer número: "))
    numero *= int(input("\n Escribe el segundo número: "))
    print("El resultado de la multiplicación es", numero)

elif numero == 4:
    print("Elegiste la división \n")
    numero = float(input("\n Escribe el primer número: "))
    numero /= float(input("\n Escribe el segundo número: "))
    print("El resultado de la división es", round(numero,2))
    
elif numero == 5:
    print("Elegiste la división entera \n")
    numero = int(input("\n Escribe el primer número: "))
    numero //= int(input("\n Escribe el segundo número: "))
    print("El resultado de la división entera es", numero)
    
elif numero == 6:
    print("Elegiste el exponente \n")
    numero = int(input("\n Escribe el primer número: "))
    numero **= int(input("\n Escribe el segundo número: "))
    print("El resultado del exponente es", numero)
    
elif numero == 7:
    print("Elegiste el modulo o resto \n")
    numero = int(input("\n Escribe el primer número: "))
    numero %= int(input("\n Escribe el segundo número: "))
    print("El resultado del modulo o resto es", numero)
    
else:
    print("Escriba una opcion valida")
print("Fin.")

#===================================== ejercicio practico #5 =====================================#

#hacer la sucesión de Hivonacci en 7 lineas de codigo y que se imprima en pantalla de forma horizontal :)
num_uno, num_dos = 0, 1
while num_dos <= 1597:
    print(num_uno , num_dos, end=" ")
    num_uno += num_dos
    num_dos += num_uno