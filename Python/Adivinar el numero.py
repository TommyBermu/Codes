from random import randint

rand = randint(1,100)

while True:
    num = int(input('\ningrese un número del uno al cien: '))
    if num > rand:
        print('el número es menor, ingrese otro número')
    elif num < rand:
        print('el número es mayor, ingrese otro número')
    elif num == rand:
        break
    else:
        print('ingrese un digito valido')
print(f'felicitaciones, el numero es {rand}')