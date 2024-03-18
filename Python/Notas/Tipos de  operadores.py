#================================== operadores aritmeticos =======================================#
numero_uno = 10
numero_dos = 3

resultado = numero_uno + numero_dos
print("el resultado de la suma es: " + str(resultado))

resultado = numero_uno - numero_dos
print("el resultado de la resta es: " + str(resultado))

resultado = numero_uno * numero_dos
print("el resultado de la multiplicación es: " + str(resultado))

numero_expoente = 5
resultado = numero_uno ** numero_expoente
print("el resultado de la potencia es: " + str(resultado))

resultado = numero_uno / numero_dos
print("el resultado de la división es: " + str(resultado))
#muestra numeros con decimales

resultado = numero_uno // numero_dos
print("el resultado de la división entera es: " + str(resultado))
#solo muestra el numero entero  

resultado = numero_uno % numero_dos
print("el resultado del módulo ó resto es: " + str(resultado))
#el resultado es lo que falta para completar el dividendo

print(3**3*(13/5-(2*4)))#los parentesis sin signo no significan multiplicacion xd

#================================= operadores de asignacion ======================================#

num_dos = 5
print(num_dos)
num_dos += 7 # x = x + y suma
print(num_dos)
num_dos -= 2 # x = x - y resta 
print(num_dos)
num_dos *= 5 # x = x * y multiplicación
print(num_dos)
num_dos /= 2 # x = x / y division 
print(num_dos)
num_dos //= 3 # x = x // y division entera
print(num_dos)
num_dos **= 2 # x = x ** y potencia 
print(num_dos)
num_dos %= 5 # x = x % y modulo
print(num_dos)

#================================== operadores relacionales ======================================#

print("Introduce dos números a comparar: \n")

num_uno = int(input("Introduce el primer número: "))
num_dos = int(input("Introduce el segundo número: "))

print("Los números a comparar son:", num_uno , "y", num_dos)

if num_uno == num_dos:
    print("Es igual...")
if num_uno != num_dos:
    print("Es diferente...")
if num_uno > num_dos:
    print("Es mayor...")
if num_uno < num_dos:
    print("Es menor...")
if num_uno >= num_dos:
    print("Es mayor o igual...")
if num_uno <= num_dos:
    print("Es menor o igual...")
print("Fin.")

#===================================== operadores logicos ========================================#

#Negación

print("Negación (not)")

num_dos = int(input("Introduce un número igual a 5: "))
if not num_dos == 5:
    print("\n El número es diferente de 5 y SI se cumple la ciondición. \n")
else:
    print("\n El número es igual a 5 y NO se cumple la ciondición. \n")

print("Fin.")

#Conjunción

print("Conjunción (and)")

num_uno = int(input("ingrese un número mayor a 2 y menor a 5: "))
if num_uno > 2 and num_uno < 5:
    print("El número ", num_uno, " cumple con la concición. \n")
else:
    print("El número", num_uno, "NO cumple con la condición. \n")

#Disyunción

print("Disyunción (or)")

palabra = input("Para cumplir con la condición escribe 'si' o 'yes': ")
palabra = palabra.lower()
if palabra == "si" or palabra == "yes":
    print("La condición se ha cumplido \n")
else:
    print("La condición NO se ha cumplido \n")

#========================================== Promedios ============================================#

print("sistema para calcular el promedio de un alumno.")

nombre = input("para comenzar, ¿Cuál es tu nombre?: ")
mate = int(input(nombre + " ¿Cuál es tu calificacion en matemáticas?: "))
quim = int(input(nombre + " ¿Cuál es tu calificacion en quimica?: "))
bio = int(input(nombre + " ¿Cuál es tu calificacion en biologia?: "))

prom = (mate + quim + bio)/ 3
final = "felicidades " + nombre + ", aprobaste"

if prom <= 6:
    final = nombre + ", reprobaste"

print(final + " con un promedio de", prom)
print(final + " con un promedio de", round(prom,2))
#round(numero a redondear, numero de decimales a mostrar)