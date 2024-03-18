#============================= manipulacion de cadenas de caracteres =============================#
mensaje = "hola"
mensaje += " "
mensaje += "Ernesto"
print(mensaje) 

mensaje = "hola"
espacio = " "
nombre = "Ernesto"
print(mensaje+espacio+nombre)
#la coma significa un espacio, el + significa ponerlo literalmente

numero_uno = 4
numero_dos = 2
resultado = numero_uno + numero_dos
resultado = str(resultado)
#str es para pasar de un valor numerico a texto
print("el sesultado de la suma es: " + resultado)

mensaje = "hola ernesto"
buscar_subcadena = mensaje.find("ernesto")
#muestra la posiicion del primer caracter de la parte buscada
print(buscar_subcadena)
buscar_subcadena = mensaje[5:12 ] #[inicio, fin, paso]
#se tiene que colocar un numero mas a la posicion del ultimo caracter
print(buscar_subcadena)

cadena = "Estoy \"estudiando\" muy juicioso"
#los \ sirven para colocar comillas dobles dentro de comillas dobles
print(cadena)
cadena = r"D: \nombre\trabajos"
#la r antes de la cadena sirve para que la cadena se guarde en crudo, no procesa los \n, \t, etc.
print(cadena)

mensaje_uno = "Hola"
mensaje_dos = "Holas"
print(mensaje_uno == mensaje_dos)
#resultados : true / false

nombre, edad = "tomas", 16
#se pueden nombrar variables diferentes en una lina con una coma
print(nombre, edad)

del nombre #del sirve para eliminar un elemento
#print(nombre)

#===================================== tipos de datos basicos ====================================#

numero = 15
print(numero, type(numero))#int

palabra_string = "nea"
print(palabra_string, type(palabra_string))#str

numero_flotante = 15.7
print(numero_flotante, type(numero_flotante))#float

numero_complejo = 5 + 6j
print(numero_complejo, type(numero_complejo))#complex

verdadero_falso = 5 == 5
print(verdadero_falso, type(verdadero_falso))#bool

nonetype = None
print(nonetype, type(nonetype))#NoneType 

#====================================== funciones integradas =====================================#

numero_bianrio = bin(14)#convierte el numero a binario (0b significa número bianrio)
print(numero_bianrio, type(numero_bianrio))#str

numero_hexadecimal = hex(17)#convierte el numero a hexadecimal (0x significa número hexadecimal)
print(numero_hexadecimal, type(numero_hexadecimal))#str

numero_int = int("0b1010", 2)#convierte el binario(0b, 2) o hexadecimal(0x, 16) a entero
print(numero_int, type(numero_int))#int

numero_abs = abs(-8)#distancia del número al 0
print(numero_abs, type(numero_abs))#int

numero_roun = round(5.5557,2)#redondea un numero hacia el mas cercano, y se pone el numero de decimales a mostrar
print(numero_roun, type(numero_roun))#int

caracter = chr(80) #convierte nuemros en palabras (ASCII)
print(caracter, type(caracter))#str

caracter = ord("P") #convierte palabras en numeros (ASCII)
print(caracter, type(caracter))#int

#======================================= ingreso de datos ========================================#

nombre = input("¿Cual es tu nombre?: ") 
#input es para ingresal datos desde la terminal
print("hola " + nombre + " vamos a realizar una suma: ")

num_uno = int(input("ingresa un numero entero: "))
#el int es para decirle al pc que el valor a ingresar lo tome como entero
num_dos = int(input("ahora ingresa otro numero entero: "))

resultado = num_uno + num_dos
print(nombre + ", el resultado de la suma es: ", resultado)

#=================================== concatenacion con format() ==================================#

#metodo 1
nombre = input("Ingresa tu nombre: ")
edad = int(input("\n Ingresa tu edad: "))
#\n es para hacer un salto de linea
print("Hola {} tienes {} años".format(nombre, edad))
#format reemplaza las llaves por el valor de las variables dentro los parentesis

#metodo 2
print("Hola {nombre} tienes {edad} años".format(nombre="carlos", edad=22))
#aqui se nombran las variales dentro del format y se llaman dentro de las llaves con el nombre

nombre = input("Ingresa tu nombre: ")
edad = int(input("Ingresa tu edad: "))

#metodo 3 
print("Hola {0} tienes {1} años".format(nombre, edad))
#aqui se llaman dentro de las llaves con la posicion 

#=================================== concatenacion con f-Strings =================================#

nombre = input("Ingresa tu nombre: ")
edad = int(input("Ingresa tu edad: "))

print(f"hola {nombre} tienes {edad} años")
#las f-Strings evaluan lo que esta dentro de las llaves al momento, y llena lo de las llaves con el
#valor de una variable definida anteriormente

#ejemplo 1 
print(f"Usuario, la suma de 4 + 1 es {4 + 1}")

#ejemplo 2
nombre = "Carlos"
estatura = 1.82
edad = 22

print(f"Hola {nombre} tienes {edad} años y mides {estatura} metros.")

#ejemplo 3
nombre = input("Ingresa tu nombre: ")
num_uno = int(input("Ingresa un número: "))
num_dos = int(input("Ingresa otro número: "))

print(f"hola {nombre}, la suma de {num_uno} + {num_dos} es: {num_uno + num_dos}")

#========================================= dir (strings) =========================================#

myStr = "Hola Como Estas"
print(dir(myStr))
#dir nos muestra lo que podemos hacer con ese tipo de dato: '__propiedades__' , 'metodos'
print(myStr.strip("Hoas")) #quita los caracteres inicados al principio y al final de la cadena
print(myStr.rstrip("Hoas")) #quita solamente los caracteres al final de la cadena
print(myStr.lstrip("Hoas")) #quita solamente los caracteres al inicio de la cadena
print(myStr.title()) #coloca las primeras letras de cada palabra en mayucula, y las demas en minusculas
print(myStr.istitle()) #muestra si el string tiene las primeras letras de cada palabra en mayucula o si no
print(myStr.upper()) #coloca todos los caracteres en mayucula
print(myStr.isupper()) #muestra si todos los caracteres estan en mayucula o si no
print(myStr.lower()) #coloca todos los caracteres en minuscula
print(myStr.islower()) #muestra si todos los caracteres estan en minuscula o si no
print(myStr.swapcase()) #intercambia los caracteres de minuscula y mayuscula
print(myStr.capitalize()) #coloca solo la primera letra en mayuscula y las demas en minuscula
print(myStr.replace("Estas","Vas")) #reemplaza el primer string por el segundo string
print(myStr.count("o")) #muestra el numero de veces que se usa ese caracer en el string
print(myStr.startswith("Hola")) #muestra si el string empieza con esa palabra o si no
print(myStr.split("o")) #divide el string partes, separadas por lo que esta en los parentesis
print(myStr.find("a")) #muestra la primera pocision de lo que está en los parentesis, sino manda -1
print(myStr.rfind("a")) #muestra la ultima pocision de lo que está en los parentesis, sino manda -1
print(myStr.index("a")) #muestra la primera pocision de lo que está en los parentesis, sino manda error
print(myStr.isspace()) #muestra si el string tiene solamente espacios o si no
print(myStr.isdecimal()) #muestra si el string tiene digitos decimales o si no    /\  en orden de
print(myStr.isdigit()) #muestra si el string tiene digitos o si no                ||  aceptaion de 
print(myStr.isnumeric()) #muestra si el string es numerico o si no                ||  datos 
print(myStr.isalpha()) #muestra si el string tiene solamente letras en una frase o si no
print(myStr.isalnum()) #muestra si el string tiene letras y numeros en una frase o si no
print(myStr.startswith('h')) #muestra si el string comienza con la letra que esta en los parentesis
print(myStr.endswith('s')) #muestra si el string termina con la letra que esta en los parentesis
print(','.join(myStr)) #separa los elementos de la cadena con el elemento que se le indique

#============================================ end y sep ==========================================#

print("Esto es un", end="-") 
print(" ejemplo")
#end evita el salto de linea y se coloca en cambio lo que se escriba entre las comillas

print("1","2","3","4","5",sep=", ") 
#sep controla las separaciones entre las cadenas de caracteres