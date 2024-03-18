#===================================== ejercicio practico #1 =====================================#

def pesos_dolares(peso):
    return peso / 3787.40

def dolares_pesos(dolar):
    return dolar * 3787.40

while True:
    print("\n\t:::MENU::: \n1. Pesos a Dolares \n2. Dolares a Pesos \n3. Salir")
    opcion = int(input('\ndigite el número de la opción: '))
    if opcion == 1:
        peso = int(input('\nIngrese la cantidad de pesos a convertir: '))
        print(f'{peso} pesos equivavlen a {pesos_dolares(peso):.2f} dolares')
    elif opcion == 2:
        dolar = float(input('\nIngrese la cantidad de dolares a convertir: '))
        print(f'{dolar} dolares equivavlen a {dolares_pesos(dolar)} pesos')
    elif opcion == 3:
        break
    else:
        print('\nDigite un número de opción valido')

#===================================== ejercicio practico #2 =====================================#

def cuadrado(ancho,alto):
    for i in range(alto):
        for j in range(ancho):
            print('*',end=' ')   
        print() #este print hace un salto de linea

alto = int(input('escriba la altura de un cuadrado: '))
ancho = int(input('escriba la anchura de un cuadrado: '))

cuadrado(ancho,alto)

#===================================== ejercicio practico #3 =====================================#
clientes = [
    {'Nombre': 'sancho', 'Apellido': 'panza', 'DNI': '99999998'},
    {'Nombre': 'tomas', 'Apellido': 'bermudez', 'DNI': '12345678'}
]

def agregar_cliente(clientes,nombre,apellido,dni_cliente):
    cliente = {}
    cliente['Nombre'] = nombre
    cliente['Apellido'] = apellido
    cliente['DNI'] = dni_cliente
    clientes.append(cliente)

def mostrar_clientes(clientes):
    for i in clientes:
        print(f"Nombre: {i['Nombre']}, Apellido: {i['Apellido']}, DNI: {i['DNI']}")

def mostrar_cliente_dni(clientes,dni):
    for i in clientes:
        if i['DNI'] == dni:
            print(f"\nNombre: {i['Nombre']}, Apellido: {i['Apellido']}, DNI: {i['DNI']}")
            return True #si el DNI esta en los clientes retorna True, si no esta retorna False
    return False

def eliminar(clientes,dni):
    for i in clientes:
        if i['DNI'] == dni:
            clientes.remove(i)
            return True #si el DNI esta en los clientes retorna True, si no esta retorna False
    return False

while True:
    print('\n \t Menú \n')
    print('1. Agregar nuevo cliente')
    print('2. Mostrar todos los clientes')
    print('3. Mostrar cliente por DNI')
    print('4. Eliminar cliente')
    print('5. Salir \n')

    opcion = int(input('Digite el número de la opcion que desea: '))

    if opcion == 1:

        print('\n \t Nuevo cliente \n')
        nombre = input('Ingrese el nombre del nuevo cliente: ').capitalize()
        apellido = input('Ingrese el apellido del nuevo cliente: ').capitalize()
        dni_cliente = input('Ingrese el DNI del nuevo cliente: ')
        while dni_cliente in clientes:
            print(f'\nEl DNI {dni_cliente} ya se encuentra registrado')
            dni_cliente = input('Ingrese el DNI del nuevo cliente: ')
        agregar_cliente(clientes,nombre,apellido,dni_cliente)
    elif opcion == 2:
        print('\n \t Clientes en la base de datos \n')
        mostrar_clientes(clientes)    
    elif opcion == 3:
        print('\n \t Buscar cliente por DNI\n')
        dni_cliente = input('Ingrese el DNI del cliente: ')
        if mostrar_cliente_dni(clientes,dni_cliente):
            print('\nCiente encontrado')
        else:
            print('\nCiente no encontrado')
    elif opcion == 4:
        print('\n \t Eliminar un cliente \n')
        dni_cliente = input('Ingrese el DNI del cliente: ')
        if eliminar(clientes,dni_cliente):
            print('\nCiente eliminado')
        else:
            print('\nCiente no encontrado')
    elif opcion == 5:
        print('\nSaliendo...')
        break
    else:
        print('\nOpción no valida')

#===================================== ejercicio practico #4 =====================================#

def factorial(num):
    if num > 0:
        resultado = num * factorial(num -1)
    else:
        resultado = 1
    return resultado

num = int(input('ingrese un número para sacarle el factorial: '))
print(f'el factorial de {num} es {factorial(num)}')

#===================================== ejercicio practico #5 =====================================#

def sumar(num):
    if num == 0:
        resultado = 0
    else: 
        resultado =  sumar(int(num/10)) + (num%10)
    return resultado
num = int(input('ingrese un número para sumarle sus digitos: '))
print(f'la suma de los digitos de {num} es {sumar(num)}')