#===================================== ejercicio practico #1 =====================================#

lista = [1,2,3,4,5,6,5,4,3,2,1]
lista = list(set(lista)) #para que pase de lista a conjunto y eso lo vuelva a pasar a lista xd
print(lista, type(lista))

#===================================== ejercicio practico #2 =====================================#

list_a = [1,2,3,4,5,1,3,2,2,1,5]
list_b = [4,5,6,7,8,4,5,6,7,7,8]

list_a = set(list_a)
list_b = set(list_b)

list_ab = list(list_a | list_b)
list_a_b = list(list_a - list_b)
list_b_a = list(list_b - list_a)
list_ayb = list(list_a & list_b)
print(list_ab, list_a_b, list_b_a, list_ayb, sep="\n")

#===================================== ejercicio practico #3 =====================================#

personaje1 = {
    "nombre": "Aragorn",
    "clase": "Guerrero",
    "raza": "Dúnadan del norte"
}
personaje2 = {
    "nombre": "Legolas",
    "clase": "Arquero",
    "raza": "Elfo Sindar"
}
personaje3 = {
    "nombre": "Gandalf",
    "clase": "Mago",
    "raza": "Istar"
}

lista = [personaje1, personaje2, personaje3]
print(lista)