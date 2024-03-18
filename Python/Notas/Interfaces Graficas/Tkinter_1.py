from tkinter import *
import sys

ventana = Tk() # es un objeto de la clase tk()

ventana.geometry('500x300') # tamaño en ancho x alto, pero se puede redimensionar manualmente
ventana.config(bg= 'lightblue')  # color de fondo
ventana.title('Ventana Principal') # el nombre de la ventana
ventana.resizable(width= False, height= False) # se bloquea la redimension manual
ventana.iconbitmap('Python\\Notas\\Interfaces Graficas\\python.ico') # se le pasa la ruta del icono de la ventana
 
ventana.mainloop() # esto es un metodo de la clase que hace que la ventana esté todo el tiempo activa

#============================================ Labels =============================================#

ventana = Tk()
ventana.geometry('512x712')

etiqueta = Label(ventana, text= 'Hola Mundo', fg= 'blue', font= ('Cascadia code', 18)) # texto que aparece en la ventana y se colora de azul, la fuente es Cascadia code y el tamaño es 18
etiqueta.place(x= 200, y= 100) # posicion del texto en la ventana, se separa de izquiera a derecha y de arriba a abajo
image = PhotoImage(file= 'Python\\Notas\\Interfaces Graficas\\holamundo.png') # se crea un objeto de la clase PhotoImage y se le pasa la ruta de la imagen
etiqueta2 = Label(ventana, image= image).place(y= 200) # se crea una etiqueta para mostrar la imgaen y se le pasa la imagen

ventana.mainloop()

#============================================= Entry =============================================#

ventana = Tk()
ventana.geometry('300x500')
nombre_label = Label(ventana, text= 'Ingrese Nombre', font= ('Cascadia code',16)).place(x= 10, y= 50)
campo_nombre = Entry(ventana ,bg= 'lightblue', bd= 4) # se crea un campo de texto y se le asigna un color de fondo y un tamaño de borde
campo_nombre.place(x= 10, y= 90, width= 100, height= 30) # lugar y tamaño del campo de texto

clave_label = Label(ventana, text= 'Ingrese Clave', font= ('Cascadia code',16)).place(x= 10, y= 130)
campo_clave = Entry(ventana, show= '*', bg= 'lightblue', bd= 4) # show es para que no se vea lo que se escribe en el campo de texto
campo_clave.place(x= 10, y= 170, width= 100, height= 30) # 

ventana.mainloop()

#============================================ Botones ============================================#

ventana = Tk()
ventana.geometry('500x500')
label_mensaje = Label(ventana , text= 'Desea mostrar saludo?', font= ('cascadia code' , 18))
label_mensaje.place(x =10 , y =20)

def saludo():
    label_mensaje.config(text= 'Hola a todos')
boton_aceptar = Button(ventana , text= 'ACEPTAR', command= saludo) # que el texto del boton sea aceptar y que el comando sea una funcion
boton_aceptar.config(width= 10, height= 3) # se le da las dimensiones al boton
boton_aceptar.place(x= 10 , y= 70)

def cancelar():
    sys.exit()
boton_cancelar = Button(ventana , text= 'CANCELAR', command= cancelar)
boton_cancelar.config(width= 10, height= 3)
boton_cancelar.place(x= 100 , y= 70)

ventana.mainloop()

#========================================== Radiobutton ==========================================#

ventana = Tk()
ventana.geometry('500x700')
label_1 = Label(ventana, text= 'Indique su lenguaje favorito', font= ('Arial', 18))
label_1.place(x= 10, y= 20)
label_2 = Label(ventana, font= ('Arial', 16))
label_2.place(x= 10, y= 350)

def lenguajes():
    if seleccion.get() == 1:
        label_2.config(text= 'Seleccionaste Python')
    elif seleccion.get() == 2:
        label_2.config(text= 'Seleccionaste Java')
    elif seleccion.get() == 3:
        label_2.config(text= 'Seleccionaste C++')

seleccion = IntVar() # se crea un objeto que es una variable de tipo entero
seleccion.set(3)     # se dice que son 3 radiobuttons

img1 = PhotoImage(file= 'Python\\Notas\\Interfaces Graficas\\python.png')
img2 = PhotoImage(file= 'Python\\Notas\\Interfaces Graficas\\java.png')
img3 = PhotoImage(file= 'Python\\Notas\\Interfaces Graficas\\cmasmas.png')

radio_1 = Radiobutton(ventana, image= img1 , variable= seleccion, value= 1, font= ('Arial',16) , command= lenguajes) # la variable es la variable antes creada con valor 3 
radio_1.place(x= 10, y= 50)

radio_1 = Radiobutton(ventana, image= img2, variable= seleccion, value= 2, font= ('Arial',16), command= lenguajes) # si el valor es el mismo para dos radiobuttons, se seleccionan ambos
radio_1.place(x= 10, y= 150)

radio_1 = Radiobutton(ventana, image= img3, variable= seleccion, value= 3, font= ('Arial',16), command= lenguajes) # command es para que se ejecute la funcion lenguajes
radio_1.place(x= 10, y= 250)

ventana.mainloop()

#========================================== Checkbutton ==========================================#

ventana = Tk()
ventana.geometry('1200x900')
ventana.title('MENU')
ventana.resizable(width=False, height=False)
x , y = 10 , 100

burrito = IntVar()
cocacola = IntVar()
dona = IntVar()
hamburguesa = IntVar()
helado = IntVar()
malteada = IntVar()
papas = IntVar()
perro = IntVar()
pizza = IntVar()
pollo = IntVar()
taco = IntVar()

def combo():
    seleccion = ''
    costo = 0
    if burrito.get() == 1:
        seleccion += 'Burrito\n'
        costo += 3
    if cocacola.get() == 1:
        seleccion += 'Coca cola\n'
        costo += 1
    if dona.get() == 1:
        seleccion += 'Dona\n'
        costo += 2
    if hamburguesa.get() == 1:
        seleccion += 'Hamburguesa\n'
        costo += 5
    if helado.get() == 1:
        seleccion += 'Helado\n'
        costo += 2
    if malteada.get() == 1:
        seleccion += 'Malteada\n'
        costo += 2
    if papas.get() == 1:
        seleccion += 'Papas\n'
        costo += 2
    if perro.get() == 1:
        seleccion += 'Perro\n'
        costo += 5
    if pizza.get() == 1:
        seleccion += 'Pizza\n'
        costo += 5
    if pollo.get() == 1:
        seleccion += 'Pollo\n'
        costo += 4
    if taco.get() == 1:
        seleccion += 'Taco\n'
        costo += 4
    label_2.config(text= 'Su orden: \n'+ seleccion + f'\nTotal: {costo}$', font= ('Cascadia code', 16))

img_burrito = PhotoImage(file= 'Python\\Notas\\Interfaces Graficas\\Comida\\burrito.png')
img_cocacola = PhotoImage(file= 'Python\\Notas\\Interfaces Graficas\\Comida\\cocacola.png')
img_dona = PhotoImage(file= 'Python\\Notas\\Interfaces Graficas\\Comida\\dona.png')
img_hamburguesa = PhotoImage(file= 'Python\\Notas\\Interfaces Graficas\\Comida\\hamburguesa.png')
img_helado = PhotoImage(file= 'Python\\Notas\\Interfaces Graficas\\Comida\\helado.png')
img_malteada = PhotoImage(file= 'Python\\Notas\\Interfaces Graficas\\Comida\\malteada.png')
img_papas = PhotoImage(file= 'Python\\Notas\\Interfaces Graficas\\Comida\\papas.png')
img_perro = PhotoImage(file= 'Python\\Notas\\Interfaces Graficas\\Comida\\perro.png')
img_pizza = PhotoImage(file= 'Python\\Notas\\Interfaces Graficas\\Comida\\pizza.png')
img_pollo = PhotoImage(file= 'Python\\Notas\\Interfaces Graficas\\Comida\\pollo.png')
img_taco = PhotoImage(file= 'Python\\Notas\\Interfaces Graficas\\Comida\\taco.png')

label_1 = Label(ventana, text= 'Personaliza tu Combo', font= ('Cascadia code', 22)).place(x= 100, y= 20)

label_2 = Label(ventana)
label_2.place(x= 950, y= 100)

Checkbutton(ventana, image= img_burrito, variable= burrito, onvalue= 1, command= combo, font= ('Cascadia code', 12)).place(x= x, y= y)
Checkbutton(ventana, image= img_cocacola, variable= cocacola, onvalue= 1, command= combo, font= ('Cascadia code', 12)).place(x= x, y= y+150)
Checkbutton(ventana, image= img_dona, variable= dona, onvalue= 1, command= combo, font= ('Cascadia code', 12)).place(x= x, y= y+350)
Checkbutton(ventana, image= img_hamburguesa, variable= hamburguesa, onvalue= 1, command= combo, font= ('Cascadia code', 12)).place(x= 10, y= y+500)
Checkbutton(ventana, image= img_helado, variable= helado, onvalue= 1, command= combo, font= ('Cascadia code', 12)).place(x= x+300, y= y)
Checkbutton(ventana, image=img_malteada, variable= malteada, onvalue= 1, command= combo, font= ('Cascadia code', 12)).place(x= x+300, y= y+220)
Checkbutton(ventana, image= img_papas, variable= papas, onvalue= 1, command= combo, font= ('Cascadia code', 12)).place(x= x+300, y= y+430)
Checkbutton(ventana, image= img_perro, variable= perro, onvalue= 1, command= combo, font= ('Cascadia code', 12)).place(x= x+600, y= y)
Checkbutton(ventana, image= img_pizza, variable= pizza, onvalue= 1, command= combo, font= ('Cascadia code', 12)).place(x= x+600, y= y+150)
Checkbutton(ventana, image= img_pollo, variable= pollo, onvalue= 1, command= combo, font= ('Cascadia code', 12)).place(x= x+600, y= y+290)
Checkbutton(ventana, image= img_taco, variable= taco, onvalue= 1, command= combo, font= ('Cascadia code', 12)).place(x= x+600, y= y+450)

ventana.mainloop()