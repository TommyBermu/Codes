#funciones predeterminadas

print('hello')
len('hello')
x = 'hello'
dir(x)
type(10)

#Argumentos por valor (para tipos de datos basicos)
def doblar_valor(num):
    num *=2
n = 5
doblar_valor(n) #en los argumentos por valor se manda una copia de la variable para no modificarla
n = doblar_valor(n) #para modificar el valor de la variable por el retorno de la funcion es así

#Argumentos por referencia (para colecciones)

def doblar_valores(num):
    for i,n in enumerate(num):
        num[i] *= 2
n = [5, 10, 15, 20]
doblar_valores(n) #en los argumentos por referencia se usan los valores de la variable, modificandola
doblar_valores(n[:]) #para no modificar el valor de la variable es así, asi se manda una copia
print(n)

# las funciones sin retorno solo hacen lo que esta dentro de las funcion y ya, pero las funciones 
# con retorno 'adquieren' un valor, asi que cuando se imprimen las sin retorno muestran none y las con 
# retorno muestran el resultado del codigo dentro de la funcion

#===================================== funciones sin retorno =====================================#

def saludar(name = "jhon doe"): #el igual sirve para que, cuando no se ingrese un valor, se imprima
    print('hola '+ str(name))   #lo que esta despues del igual

saludar('tomas')
saludar()

def tabla_multiplicar(num): #Parametro: valores que se le asignan cuando se define la funcion
    for i in range(1,11):
        print(f'{num}*{i} = {num * i}')

tabla_multiplicar(int(input('número: '))) #Argumento: valores que se envian cuando se llama la funcion
 
#===================================== funciones con retorno =====================================#

def restar(n1,n2): #parametros
    mult = n1 - n2
    return mult #sirve para retornar el valor

print(restar(4,2)) #los argumentos pueden paserse por posicion o por nombre
print(restar(n2 = 4, n1 = 2))

def prueba():
    return "hola",45,[1,2,3] 
c,n,l = prueba() #como retorna 3 valores, cada variable toma un valor
print(c,"\n",n,"\n",l,)
print(prueba()) #muestra todo como una tupla

#========================================= funcion lambda ========================================#

#estas funciones sirven para porciones de codigo pequeñas o con solo una expresión
#no pueden retornar valores
square = lambda x: x ** 2 #(nombre) = lambda (valores):(expresion)
print(square(3)) 

add = lambda n1, n2: n1 + n2
print(add(2,7))

#====================================== funciones recursivas =====================================#
#funciones que se llaman a si misma, como un ciclo

def cuenta_regresiva(num):
    if num > 0:
        print(num)
        cuenta_regresiva(num - 1)
    else:
        print('Boooooom!!!')

cuenta_regresiva(5)

#====================================== funciones generadoras ====================================#

def genera_pares(limit): # es como una funcion normal, pero devuelve valores iterables de uno en uno
    num = 1              # lo que lo vuelve mucho mas rapido y eficiente
    while num < limit:
        yield num * 2 # yield seria su return
        num += 1
devulve_pares = genera_pares(10) # se tiene que guardar en un objeto para que se vuelva iterable

print(next(devulve_pares)) # 2
print('Mas codigo...')     # se demuestra que va soltando valores de uno en uno
print(next(devulve_pares)) # 4
print('Mas codigo...')     # entre llamada y llamada entra en estado de suspencion
for i in devulve_pares:    # 6 - 18
    print(i)