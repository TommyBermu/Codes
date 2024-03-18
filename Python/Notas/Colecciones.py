#============================================= Listas ============================================#

lista = [10, "hola", True, 47.2 , 22j] # se define con corchetes
print(lista, type(lista), type(lista[1]))#lsit
print(type(lista[-2]))#muestra el elemento de derecha a izquiera, comenzando por el -1
print(lista[1:4])#si no tiene número a algún lado, se va a tomar como desde el inicio o hasta el fin

print(list(range(1, 10))) #range genera numeros de donde a donde, en este caso se guarda como lista
# range(inicio, fin, paso) = (1,8,2) = 1 3 5 7
numbers_list = ['numeros',1, 2, 4, 5]  
print(4 in numbers_list) #muestra si el elemento existe en la lista o si no
print(numbers_list)
numbers_list.append('seis') #es recomendable usarlo para gregar un solo elemento al final de la lista
print(numbers_list)
numbers_list.extend([7, 8, 9]) #en este se pueden agregar más de un elemento
print(numbers_list)
numbers_list.insert(2, 3) #aca se puede agregar un elemento en una posición especifica (pos, elem)
print(numbers_list)
numbers_list.pop(6) #aca se puede eliminar un elemento de una posición especifica
print(numbers_list)
numbers_list.remove('numeros') #es como un pop pero en vez de usar la posición, usa el nombre
print(numbers_list)
numbers_list.sort(reverse=True) #aca se organizan los elementos (se deja en blanco para que queden bien)
print(numbers_list)
print(numbers_list.index(3)) #muestra la posición de ese elemento en la lista
numbers_list.clear() #acá se eliminan todos los elementos de la lista
print(numbers_list)

#Pilas

pila = [1, 2, 3] #es una lista pero con la lógica de "último en entrar, primero en salir"

print(pila)
pila.append(4) #para agregar un elemeto al final de la lista
pila.append(5) 
print(pila)
n = pila.pop() #elimina el último elemento
print(f'sacando {n}')
print(pila)
n = pila.pop()
print(f'sacando {n}')
print(pila)

#Colas

cola = ['maria','jose','alejandro','mario'] #es una lista pero con la logica de "primero en entrar, primero en salir"
print(cola)
cola.append('carla')
cola.append('flor')
print(cola)
n = cola.pop(0) #elimina el primer elemento
print(f'ahora estan atendiendo a {n}')
print(cola)
n = cola.pop(0) #elimina el primer elemento
print(f'ahora estan atendiendo a {n}')
print(cola)

#============================================= Tuplas ============================================#

tupla = (10, "adios", False) # se define con parentesis o sin nada, tiene que tener mas de un dato para
#que se considere como una tupla o poner una coma despues del dato (1,)
print(tupla, type(tupla), type(tupla[2]))#tuple
tupla = list(tupla)#list sirve para transformar de tupla a lista
print(tupla, type(tupla))#tuple es para convertir de listas a tuplas 

#========================================= Conjuntos / Set =======================================#

mySet = set((1, "Red", 3, "Green", 5, "BLue"))#para un conjunto vacio es mejor escribirlo de esta 
#forma porque python lo confunde con un diccionario, aunque de ambas formas está bien
mySet = {1, "Red", 3, "Green", 5, "BLue"} #set es una "lista" pero sus datos no tienen indice
print(type(mySet)) #no sirve para acceder con indices (es como un cajón desordenado)
print(4 not in mySet)#True
print(mySet)# no pueden haber valores duplicados ni otras colecciones
mySet.add("Violet")
print(mySet)
mySet.remove("Red")#cunado no existe el elemento manda error
mySet.discard("Red")#cunado no existe el elemento NO manda error
print(mySet)
mySet.clear()
print(mySet)
del mySet
print(mySet) 

a = {1,2,3}
b = {3,4,5}
print(a,b)
c = a | b #union de conjuntos (alt 124)
print(c)
c = a & b #interseccion de conjuntos, muestra los valores comunes de ambos conjuntos
print(c)
c = a - b #diferencia de conjuntos, mustra los valores que estan en a que no están en b
print(c)
c = a ^ b #diferencia simetrica de conjuntos(alt 94), mustra los valores que no estan en ambos 
print(c)
c = {1,2,3,4,5}
print(a.issubset(c))#muestra si el conjunto a es un subconjunto del conjunto c
print(b.issubset(c))#muestra si el conjunto b es un subconjunto del conjunto c
c = {1,2,3,5}
print(c.issuperset(a))#muestra si el conjunto c es un superconjunto del conjunto a
print(c.issuperset(b))#muestra si el conjunto c es un superconjunto del conjunto b
b = {4,5}
print(a.isdisjoint(b))#muestra si el conjunto a NO tiene algún valor igual a un valor en b
a = frozenset(a)#ahora es un conjunto inmutable

#========================================== Diccionarios =========================================#

dicccionario = {
    #clave : valor
    "nombre":'Tomas',
    "edad":16,
    "estatrura":1.73,
    "vida":True
} # se pueden agragar tuplas y listas como valores
print(dicccionario, type(dicccionario),type(dicccionario["edad"]))#dict
dicccionario["estrato"] = 3 #asi se agregan elementos a un diccionario
print(dicccionario.keys()) #muestra las claves
print(dicccionario.values()) #muestra los valores
print(dicccionario.items()) #muestra las claves y valores en tuplas separadas
print(dicccionario.get("peso", 'no existe ese elemento')) #si no exite el elemento NO manda error y en 
#cambio muestra el mensaje
del(dicccionario["estatrura"]) #elimina una clave y su valor
print(dicccionario)
dicccionario.clear()#se pueden colocar diccionarios en listas y tuplas
print(dicccionario)