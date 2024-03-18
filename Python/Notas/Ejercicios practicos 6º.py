#===================================== ejercicio practico #1 =====================================#

pal1 = input('escriba una palabra: ')
pal2 = input('\nescriba otra palabra: ')

if len(pal1) > len(pal2):
    print(f'la palabra mas larga es: {pal1}')
elif len(pal1) < len(pal2):
    print(f'la palabra mas larga es: {pal2}')
else:
    print('Ambas palabras tienen la misma longitud')

#===================================== ejercicio practico #2 =====================================#

pal = input('escriba una palabra: ')

if pal.endswith('.'):
    print('Termina con punto')
else:
    print('No termina con punto')

#===================================== ejercicio practico #3 =====================================#

pal = input('escriba una frase: ')
pal = pal.replace(" ","")

pala = pal[::-1] #sin inicio, sin fin, pero el paso es -1, osea se recorre hacia atras

if pal == pala:
    print('La frase es palíndroma')
else:
    print('La frase no es palíndroma')

#===================================== ejercicio practico #4 =====================================#

pal = input('escriba una frase: ')
pal = pal.title()
pal = pal.replace(" ","*")
print(pal)

#===================================== ejercicio practico #5 =====================================#

pal = input('escriba una frase: ').lower()

print(f"""
hay {pal.count("a")} a,
hay {pal.count("e")} e,
hay {pal.count("i")} i,
hay {pal.count("o")} o,
hay {pal.count("u")} u
""")