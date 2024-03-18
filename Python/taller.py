import numpy as np
from PIL import Image


def redimensionar(imagen_una_fila, num_columnas):
  '''
    Recibe como entrada la lista imagen_una_fila de una dimensión
    y retorna una lista de listas, cada una de longitud num_columnas,
    conteniendo los valores de imagen_una_fila.
    '''
  lista_vacia = []
  for i in range(0, len(imagen_una_fila), num_columnas):
    lista_vacia.append(imagen_una_fila[i:i + num_columnas])
  return lista_vacia


def cargar_imagen(archivo):
  '''
  Carga la imagen de archivo y la retorna como una lista de listas.
  '''
  imagen = Image.open(archivo)
  return redimensionar(list(imagen.getdata()), imagen.size[0])


def mostrar_imagen(matriz):
  '''
  Recibe la lista de listas matriz y muestra la imagen
  correspondiente en pantalla.
  '''
  Image.fromarray(np.asarray(matriz, dtype=np.uint8)).show()


def reflejar_vertical(matriz):
  '''
    Retorna una copia de matriz invirtiendo el orden de sus filas.
    '''
  matriz = matriz[::-1]

  return matriz


def reflejar_horizontal(matriz):
  '''
    Retorna una copia de matriz invirtiendo el orden de sus columnas.
    '''
  for i in range(len(matriz)):
    matriz[i] = matriz[i][::-1]
  return matriz


def crear_collage(matriz):
  '''
    Sean F y C la cantidad de filas y columnas de matriz, respectivamente.
    Esta función retorna una matriz collage de tamaño 2F x 2C.
    Las primeras F filas y C columnas de collage contienen una copia de matriz.
    Las filas 0 a F-1 y las columnas C a 2C-1 de collage contienen una copia
    de matriz reflejada horizontalmente. Las filas F a 2F-1 y las columnas
    0 a C-1 de collage contienen una copia de matriz reflejada verticalmente.
    Finalmente, las filas F a 2F-1 y las columnas C a 2C-1 de collage contienen
    una copia de matriz reflejada tanto horizontal como verticalmente.
    '''
  lista = []
  for i in range(len(matriz)):
    lista.append(matriz[i] + matriz[i][::-1])
  lista += lista[::-1]
  return lista


# Debe sustituir el nombre del archivo.
archivo = 'Python\\imagen.jpg' # C:\\Users\\loque\\Documents\\Backup\\Codes\\Python\\imagen.jpg

## Código de prueba 1 - Prueba de redimensionar
imagen_una_fila = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
num_columnas = 4
matriz = redimensionar(imagen_una_fila, num_columnas)
resultado_esperado = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]]
if matriz == resultado_esperado:
  print('Prueba de redimensionar: OK')
else:
  print('Prueba de redimensionar: FALLO')
matriz = cargar_imagen(archivo)
mostrar_imagen(matriz)
## Fin de código de prueba 1'''

## Código de prueba 2 - Prueba de reflejar_vertical
matriz = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]]
reflejo = reflejar_vertical(matriz)
resultado_esperado = [[9, 10, 11, 12], [5, 6, 7, 8], [1, 2, 3, 4]]
if reflejo == resultado_esperado:
  print('Prueba de reflejar_vertical: OK')
else:
  print('Prueba de reflejar_vertical: FALLO')
matriz = cargar_imagen(archivo)
reflejo = reflejar_vertical(matriz)
mostrar_imagen(reflejo)
## Fin de código de prueba 2'''

## Código de prueba 3 - Prueba de reflejar_horizontal
matriz = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]]
reflejo = reflejar_horizontal(matriz)
resultado_esperado = [[4, 3, 2, 1], [8, 7, 6, 5], [12, 11, 10, 9]]
if reflejo == resultado_esperado:
  print('Prueba de reflejar_horizontal: OK')
else:
  print('Prueba de reflejar_horizontal: FALLO')
matriz = cargar_imagen(archivo)
reflejo = reflejar_horizontal(matriz)
mostrar_imagen(reflejo)
## Fin de código de prueba 3'''

## Código de prueba 4 - Prueba de crear_collage
matriz = [[1, 2], [3, 4]]
collage = crear_collage(matriz)
resultado_esperado = [[1, 2, 2, 1], [3, 4, 4, 3], [3, 4, 4, 3], [1, 2, 2, 1]]
if collage == resultado_esperado:
  print('Prueba de crear_collage: OK')
else:
  print('Prueba de crear_collage: FALLO')
matriz = cargar_imagen(archivo)
collage = crear_collage(matriz)
mostrar_imagen(collage)
## Fin de código de prueba 4'''