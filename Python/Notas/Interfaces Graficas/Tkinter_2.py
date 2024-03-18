from tkinter import *
from tkinter import messagebox
from tkinter import filedialog

#============================================= Menu ==============================================#
"""
ventana = Tk()
ventana.geometry('500x500')
ventana.option_add('*Font', 'Verdana 10') # cambia la letra y su tamaño
barra_menu = Menu(ventana) # crea una barra de menu en la parce superior de la ventana
ventana.config(menu= barra_menu, width= 600, height= 400) # no se le coloca el ventana.geometry porque toma automaticamente estas dimensiones 

menu_archivo = Menu(barra_menu, tearoff= 0) # tearoff es para que se quiten unas lineas en el menú
menu_archivo.add_command(label= 'Nuevo')
menu_archivo.add_command(label= 'Abrir')
menu_archivo.add_command(label= 'Guardar')
menu_archivo.add_command(label= 'Guardar Como...')
menu_archivo.add_separator() # se crea una linea separadora
menu_archivo.add_command(label= 'Cerrar')
barra_menu.add_cascade(label= 'Archivo', menu= menu_archivo)

menu_edit = Menu(barra_menu, tearoff= 0)
menu_edit.add_command(label= 'Cortar')
menu_edit.add_command(label= 'Pegar')
menu_edit.add_command(label= 'Imprimir')
barra_menu.add_cascade(label= 'Edición', menu= menu_edit)

menu_ayuda = Menu(barra_menu, tearoff= 0)
menu_ayuda.add_command(label= 'Soporte')
menu_ayuda.add_command(label= 'Actualizaciones')
menu_ayuda.add_command(label= 'Acerca de')
barra_menu.add_cascade(label= 'Ayuda', menu= menu_ayuda)

ventana.mainloop()

#============================================ TextBox ============================================#

ventana = Tk()
ventana.geometry('500x550')

label_coment = Label(ventana, text= 'Comentarios', font= ('Cascadia code', 14))
label_coment.grid(row= 0, column= 0)
text_comment = Text(ventana, font= ('Cascadia Code', 16))
text_comment.grid(row= 1, column= 0, padx= 20, pady= 15)
text_comment.config(width= 30, height= 17)
scroll_vertical = Scrollbar(ventana, command= text_comment.yview)
scroll_vertical.grid(row= 1, column= 1, sticky= 'nsew')
text_comment.config(yscrollcommand= scroll_vertical.set)

ventana.mainloop()
"""
#======================================== Eventos de Menu ========================================#

ventana = Tk()
ventana.option_add('*Font', 'Verdana 10') 
ventana.geometry('500x500+710+250') # lo que va despues de las dimensiones es la posición de la ventana en la pantalla

def acerdade ():
    messagebox.showinfo('Mi Software', 'Sistema de procesos informaticos')
def actualizaciones ():
    messagebox.showwarning('Actualización','''Actualización disponible
Debe actualizar el sistema
Visite www.misoftware.com.co''')
def salir ():
    selección = messagebox.askokcancel('Salir', '¿Desea salir de la aplicación?')
    if selección == True:
        ventana.destroy()
def cierradoc ():
    seleccion = messagebox.askokcancel('Reintentar', 'Error en cierre de archivo')
    if seleccion == False:
        ventana.destroy()

def abrir ():
    filedialog.askopenfile(initialdir= 'C:\\Backup\\Codes\\Python\\Notas\\Interfaces Graficas', filetypes=(('Ficheros de Python','.py'), ('Ficheros Excel', '*.xlsx'), ('Ficeros de Texto', '*.txt'), ('Todos los Archivos', '*.*')))
def guardar ():
    f = filedialog.asksaveasfile(mode= 'w', defaultextension= '.txt') # 'w' es de write
    f.close()

barra_menu = Menu(ventana) 
ventana.config(menu= barra_menu, width= 600, height= 400) 

menu_archivo = Menu(barra_menu, tearoff= 0) 
menu_archivo.add_command(label= 'Nuevo')
menu_archivo.add_command(label= 'Abrir', command= abrir)
menu_archivo.add_command(label= 'Guardar', command= guardar)
menu_archivo.add_command(label= 'Guardar Como...')
menu_archivo.add_separator()
menu_archivo.add_command(label= 'Cerrar', command=cierradoc)
menu_archivo.add_command(label= 'Cerrar Todo')
menu_archivo.add_command(label= 'Salir', command= salir)
barra_menu.add_cascade(label= 'Archivo', menu= menu_archivo)

menu_edit = Menu(barra_menu, tearoff= 0)
menu_edit.add_command(label= 'Cortar')
menu_edit.add_command(label= 'Pegar')
menu_edit.add_command(label= 'Imprimir')
barra_menu.add_cascade(label= 'Edición', menu= menu_edit)

menu_ayuda = Menu(barra_menu, tearoff= 0)
menu_ayuda.add_command(label= 'Soporte')
menu_ayuda.add_command(label= 'Actualizaciones', command= actualizaciones)
menu_ayuda.add_command(label= 'Acerca de', command= acerdade)
barra_menu.add_cascade(label= 'Ayuda', menu= menu_ayuda)

ventana.mainloop()