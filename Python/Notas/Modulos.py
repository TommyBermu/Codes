#para que funcione deben estar en la misma carpeta
#===================================== modulos de python =========================================#
import datetime #ejemplo

print(datetime.date.today())
print(datetime.timedelta(minutes=100))

from datetime import timedelta # importar funciones especificas de un modulo

print(timedelta(minutes=100))

#====================================== modulos propios ==========================================#

import myMath
 
myMath.add(7,4)
myMath.sub(7,4)
 
from myMath import add, sub # importar funciones especificas de un modulo

add(8,4)
sub(8,4)

#================================== modulos de otras personas ====================================#
#estos se descargan directamente en el interprete de python

from math import sqrt, sin, cos,pi # está en radianes
print(cos(pi),sin(0))
print(sqrt(25),sqrt(4112465)) # los valores los da en float

from colorama import Fore, Style, init
init(convert=True)# init es para que en el cmd de windows de vean los colores

print(Fore.RED + 'hello world')