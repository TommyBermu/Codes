#===================================== ejercicio practico #1 =====================================#

a = float(input('introduce un número a: '))
b = float(input('introduce un número b: '))
c = float(input('introduce un número c: '))

resultado = (a**3 * (b**2 - 2*a*c)) / (2*b)
print(f'el resultado es {resultado}')

#===================================== ejercicio practico #2 =====================================#

a = float(input('introduce un número a: '))
b = float(input('introduce un número b: '))

resultado = ((3+5*8) < 3 and ((-6/3*4)+2 < 2)) or (a>b)
print(f'el resultado lógico es {resultado}')

#===================================== ejercicio practico #3 =====================================#


a = int(input('introduce un número a: '))
b = int(input('introduce un número b: '))

a , b = b , a
print(f'ahora a es {a} y b es {b}')

#===================================== ejercicio practico #4 =====================================#
import math 
radio = float(input('introduce el radio del circulo: '))

area , cir = math.pi * radio**2 , 2 * math.pi * radio
print(f'el area del circulo es de {area:.2f} y la longitud de la circunferencia es de {cir:.2f}')
#para redondear un numero se usa el float:.(numero de decimales)f

#===================================== ejercicio practico #5 =====================================#

precio = float(input('valor total: '))

descuento = precio * 0.85
print(f'el valor final de la compra es de ${descuento:.2f}')