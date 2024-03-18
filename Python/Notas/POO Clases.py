class ClasePrueba:
    variable = "blah"

    def function (self): # self es un parametro que debe estar dentro de una funcion dentro de una clase
        print("esto es un mensaje dentro de una clase")
    pass # pass, es para crear una funcion, ciclo o clase vacia

#metodo incorrecto
# ClasePrueba().function()

miobjetox = ClasePrueba() # instanciacion, crear una instancia de la clase en una valiable, lo que la convierte en un objeto

miobjetox.function()
print(miobjetox.variable)

class Sapo:
    croa = False
    def __init__(self): # __init__ hace que se inicie sin llamar la clase y/o funcion
        print("Se creó un sapo")

    def croar(self):
        self.croa = True # self se pone porque sin el, la variable es local de la funcion, no de la clase

sapo1 = Sapo() # para que se inicie la clase con el __init__ se debe nombrar un objeto
print(sapo1.croa)
sapo1.croar()
print(sapo1.croa) # se "actica" la croacion del sapo al llamar la funcion

#======================================== ejemplo winston ========================================#
class Winston:
    def __init__(self, nombre = input('nombre: '), edad = input('edad: '), posx = input('posx: '), posy = input('posy: ')):
        print(f'creaste un nuevo winston llamado {nombre}, tiene {edad} años, y esta en las coordenadas ({posx},{posy})') 

winsteen = Winston()

#======================================== ejemplo perro ==========================================#

class Perro:
    color = "Blanco" # las variables dentro de una clase se les llama atributo
    def ladrar(self, volumen = "ruidoso"): # las funciones dento de una clase se les llama metodos
        print(f"Este perro ladra: {volumen}")

p = Perro()
print(p.color)
p.tamaño = 37 # para crear un atributo dentro de un objeto es como nombrar una variale normal, pero antes se pone el nombre del objeto y un punto

print(p.tamaño)
p2 = Perro()
try:
    print(p2.tamaño) # pero este cambio solo es para el objeto, no para la clase
except:
    print("p2 no tiene tamaño asignado")
    
p2.color = "Café" # para cambiar el valor de un atributo es como cambiar el valor de una variable, pero antes se pone el nombre del objeto y un punto
print(p2.color)

p.ladrar()
p.ladrar("silencioso")

#======================================== ejemplo carrito ========================================#

class auto:
    def __init__(self):
        self.marca = 'BMW'
        self.puertas = 4
        self.color = 'rojo'
        self.peso = 800
        self.largo = 4.40
        self.ancho = 1.70

    def funcinones(self):
        self.enciende = True
        self.arranca = True
        self.acelera = True
        self.frena = True

    def datos(self):
        print(f'marca del auto: {self.marca}\nPuertas: {self.puertas}\nColor: {self.color}\nPeso: {self.peso}Kg')

mi_auto = auto()
mi_auto.datos()

#=================================== colaboracion entre clases ===================================#
class auto:
    def __init__(self, marca, color, ruedas):
        self.marca = marca
        self.color = color
        self.ruedas = ruedas

class caracteristicas:
    def __init__(self):
        self.vehiculo = auto('Toyota','azul', 4) # el hecho de que se cree un objeto dentro de una clase, significa que ya hay una colaboracion
        print('Datos del vehiculo:')
        print(f'Marca: {self.vehiculo.marca}\nColor: {self.vehiculo.color}\nRuedas: {self.vehiculo.ruedas}')
        if self.vehiculo.ruedas == 1:
            print('El vehiculo es un monociclo')
        elif self.vehiculo.ruedas == 2:
            print('El vehiculo es una motocicleta')
        elif self.vehiculo.ruedas == 3:
            print('El vehiculo es un triciclo')
        elif self.vehiculo.ruedas == 4:
            print('El vehiculo es un automovil')
        else:
            print('El vehiculo es un camión')

mi_auto = caracteristicas()

#======================================= metodos de clase ========================================#

class operaciones:
    def __init__(self):
        self.valor_1 = int(input('Ingrese primer valor: '))
        self.valor_2 = int(input('Ingrese sengundo valor: '))
        self.sumar()       # estos metodos van a operar valor_1 y valor_2
        self.resta()       # pero se definen luego porque estan el metodo __init__
        self.multiplicar() # entonces es un metodo que llama un metodo

    def sumar(self):
        suma = self.valor_1 + self.valor_2 # no se usa self para la variable porque va a ser una variable del metodo, no de la clase
        print(f'La suma es {suma}')
    def resta(self):
        resta = self.valor_1 - self.valor_2
        print(f'La diferencia es {resta}')
    def multiplicar(self):
        mult = self.valor_1 * self.valor_2
        print(f'El producto es {mult}')

resultado = operaciones()