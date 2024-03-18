class Software: # Clase Padre
        def __init__(self):
            self.sistema_operativo = input('Ingrese el nombre del OS: ')
            self.arqutectura = int(input('Ingrese arquitectura 32/64: '))
            self.info()
        
        def info(self):
            print(f'Sistema operativo: {self.sistema_operativo}')
            print(f'Arquitectura: {self.arqutectura}')

class Base(Software): # Clase Hija
    def __init__(self):
        super().__init__() # super() sirve para iniciar el metodo __init__ de la clase padre, de otro modo no se inicia
        self.procesador = input('Ingrese procesador: ')
        print('_'*50)

    def imprime(self):
        print(f'Procesador: {self.procesador}')

pc_1 = Base() # se inicia el __init__ de la clase Base, y como ahi mismo se llama el metodo __init__ de la clase padre, se inicia
pc_1.info() # se llama al metodo info que esta en la clase Software, no en Base, pero por herencia se puede hacer 
pc_1.imprime() 

#================================== Ejercicio Cajero Automatico ==================================#

class Cajero:
    monto = 50000
    print('Bienvenido a su cajero automatico')

    def operaciones(self):
        self.opcion = int(input("""
        ----------------------------------------------
        Por favor indique que operacion dese realizar:
        1. Realizar Balace
        2. Deposito a cuenta
        3. Retiro de efectivo
        4. Salir
        Ingrese la opcion: """))
        self.control = 0
        while self.control == 0:
            if self.opcion == 1:
                self.consulta_balance()
            elif self.opcion == 2:
                self.depositar()
            elif self.opcion == 3:
                self.retirar()
            elif self.opcion == 4:
                self.control = 1
                self.salir()
            else:
                print('Opcion invalida')
                self.operaciones()

    def consulta_balance(self):
        print(f'\nEl balance de su cuenta es {self.monto} Pesos')
        print('\n¿Desea realizar otra operacion?')
        self.operaciones()
    
    def depositar(self):
        ingreso = int(input('Ingrese la cantidad de dinero a ingresar: '))
        self.monto += ingreso
        self.consulta_balance()

    def retirar(self):
        retiro = int(input('Ingrese la cantidad de dinero a retirar: '))
        if retiro > self.monto:
            print('Usted no cuenta con suficiente dinero, intente nuevamente')
        else:
            self.monto -= retiro
            self.consulta_balance()

    def salir(self):
        print('*'*35 + '\nGracias por usar nuestros servicios\n'+'*'*35)

maquina = Cajero()
maquina.operaciones()