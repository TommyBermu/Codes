class Leon:
    def tipo_animal(self):
        print('Animal de la familia de los felinos')
    
class Ballena:
    def tipo_animal(self):
        print('Animal de la familia de los cetaceos')

class Caballo:
    def tipo_animal(self):
        print('Animal de la familia de los equinos')

# metodo polimorfo 
def animales(definicion):    # es una funcion/metodo que sirve para varias clases 
    definicion.tipo_animal()

animal = Leon()      # puede ir leon, ballena o caballo
animales(animal)
animal.tipo_animal() # hace lo mismo pero solo sirve para una clase