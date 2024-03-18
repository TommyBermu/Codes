while True:
    print("""\n1. Decimal a Binario \n2. Binario a Decimal \n3. Salir""")
    opcion = int(input('\nIngrese el número de la opcion: '))
    list_bin = []
    list_dec = []
    decimal = '\nEl número en decimal es '
    dec = 0
    if opcion == 1:
        num = int(input('\nIngrese un número decimal para convertir a binario: '))

        while num > 0:
            list_bin.insert(0,str(num%2)) # se agrega un el resulatdo del modulo a la posicion 0
            num //= 2
        print('\nEl número en binario es '+''.join(list_bin))# aca se pasa de lista a una cadena de caracteres 

    elif opcion == 2:
        num = int(input('\nIngrese un número binario para convertir a decimal: '))

        while num != 0: # hasta que el numero se acabe y se rompe el ciclo
            list_dec.append(num%10) # se calcula el resto de la division entre 10 para "sacar" el ultimo digito y se agrega a la lista 
            num = int(num/10) # se divide entre 10, lo que deja ej: 10.1, pero se omite el .1 al tomar el valor entero
        try:
            for i , j in zip(range(0,len(list_dec)) , list_dec): # se itera en dos listas, la primera es un rango entre 0 y la longitud de la lista para que luego sea elevaada, la segunda es la lista del binario   
                if j > 1: # si alguno de los dígitos es mayor a uno, que se rinicie :)
                    raise ValueError('\nSolo se puede dígitar números binarios') # que haga un error 
                else:
                    dec += (2**i) * j # se eleva un dos al iterador para que quede una lista de 1,4,8,16... y se multiplica por el uno o el cero del binario para ir sumandolo y dar el numero decimal
        except ValueError as NonBinary: # que capture ese error y que lo nombre como nonbinary
            print(NonBinary)
        else:
            decimal += str(dec) # luego se agrega el decimal resultante a la cadena para imprimirla
            print(decimal)
    elif opcion == 3:
        break
    else:
        print('\nINGRESE UN NÚMERO DE OPCIÓN VÁLIDO')
