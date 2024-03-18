while True:
    try: #intente hacer esta porcion de codigo
        numero = int(input('ingrese un numero: '))
        print(f'la suma de 10 + {numero} es {10 + numero}')
    except: #si sale error que haga esto (si no sale error no lo hace)
        print('ha ocurrido un error')
    else: #este es un caso contrario del except (si No ocurre una excepcion se ejecuta)
        print('programa finalizado correctamente')
        break
    finally: #este se ejecuta pase lo que pase
        print('iteracion finalizada')

#======================================= multiples errores =======================================#

def dividir():
    while True:
        try:
            num1 = float(input('ingrese un numero: '))
            num2 = float(input('ingrese un numero: '))
            resultado = num1 / num2
            print(f'el resultado es {resultado:.2f}')
        except ValueError:  #si se digita un str, que se haga eso
            print('Error, digite correctamente los numeros')
        except ZeroDivisionError: #si se intenta dividir entre cero, que se haga eso
            print('Error, no se puede dividir entre cero')
        else:
            print('programa finalizado correctamente')
            break
dividir()

#====================================== excepciones propias ======================================#

def verificar_edad(edad):
    if edad < 0: #como una edad no puede ser negativa
        raise ValueError('la edad no puede ser negativa') 
        #raise lanza un error de valor, aunque para el compilador esté bien el codigo
    elif edad < 18:
        print('eres muy joven')
    elif edad < 30:
        print('eres joven')
    elif edad < 50:
        print('eres maduro')
edad = int(input('ingrese su edad: '))
try:
    verificar_edad(edad)
except ValueError as EdadNegativa: #si detecta el error, lo denomina como "EdadNegativa"
    print(EdadNegativa) #si se imprime el alias del error se manda el mensaje del error escrito antes
    #se pueden lanzar y capturar nuestras propias excepciones
print('programa termidado')