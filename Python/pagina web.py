#cambios: agregar la opcion de ingresar dinero, agregar la opcion de eliminar cuenta, agregar la opcion de 

usuarios = {
    "julian":["manito", 12345, 5000.0],
    "tomas":["sapo", 54321, 6500.0]
}

while True:
    a = int(input("""
        1. Crear nuevo usuario 
        2. Ingresar 
        3. salir 

Ingrese la opcion deseada: """))

    if a == 1:
        while True:
            nombre = input('\nIngrese su nombre de usuario: ')
            cont = input('Ingrese su contraseña: ')
            c_cont = input('Ingrese nuevamente su contraseña: ')

            if cont == c_cont:
                print('\nCuenta creada correctamente, redirigiendo a menú de inicio...')
                usuarios[nombre] = [cont,0,0]
                break
            else:
                print('Error, las contraseñas no coinciden, intentelo nuevamente')
    elif a == 2:
        try:
            nombre = input('\nIngrese su nombre de usuario: ')
            if nombre not in usuarios:
                raise ValueError('\nEl usuario no está registrado')
            cont = input('\nIngrese su contraseña: ')
            if cont != usuarios[nombre][0]:
                raise ValueError('\nContraseña incorrecta intente nuevamente')
        except ValueError as IncorrectData:
            print(IncorrectData)
        else:
            while True:
                opcion = int(input(f"""\nBienvenido {nombre}\n
        1. Retirar dinero
        2. Enviar dinero
        3. Editar perfil
        4. Salir de mi cuenta

Tiene ${usuarios[nombre][2]} en su cuenta, ¿Que deseas hacer?: """))

                try:
                    if opcion == 1:
                        pin = int(input('\nIngrese su pin de seguridad: '))
                        if pin != usuarios[nombre][1]:
                            raise TypeError('\nPin incorrecto')
                        retiro = int(input('\n¿Cuando desea retirar?: ')) 
                        if retiro > usuarios[nombre][2]:
                            raise TypeError('\nNo tiene suficiente dinero en la cuenta')
                        usuarios[nombre][2] -= retiro
                        print(f'\nSu nuevo saldo es de ${usuarios[nombre][2]}')
                    elif opcion == 2:
                        receptor = input('\nIngrese el usuario al que le quiere enviar el dinero: ')
                        if receptor not in usuarios:
                            raise TypeError('\nLa persona no se encuentra registrada')
                        envio = int(input(f'\n¿Cuando desea enviar a {receptor}?: '))
                        if envio > usuarios[nombre][2]:
                            raise TypeError('\nNo tiene suficiente dinero en la cuenta')
                        pin = int(input('\nIngrese su pin de seguridad: '))
                        if pin != usuarios[nombre][1]:
                            raise TypeError('\nPin incorrecto')
                        usuarios[nombre][2] -= envio
                        usuarios[receptor][2] += envio
                        print(f'\nSe le envió ${envio} a {receptor} y ahora tienes ${usuarios[nombre][2]}')
                    elif opcion == 3:
                        op = int(input("""
        1. Cambiar Nombre 
        2. Cambiar Contraseña 
        3. Cambiar pin de seguridad
                                       
¿Que desea Cambiar?: """))
                        if op == 1:
                            nom = input('\nIngrese su nuevo nombre: ')
                            con = input('\nIngrese su contraseña: ')
                            if con != usuarios[nombre][0]:
                                raise TypeError('\nContraseña incorrecta')
                            usuarios[nom] = usuarios.pop(nombre)
                            print('\nNombre cambiado con exito, redirigiendo a inicio de sesión')
                            break
                        elif op == 2:
                            con = input('\nIngrese antigua contraseña: ')
                            ncon = input('\nIngrese su nueva contraseña: ')
                            if con != usuarios[nombre][0]:
                                raise TypeError('\nContraseña incorrecta')
                            usuarios[nombre][0] = ncon
                            print('\nContraseña cambiada con exito, redirigiendo a inicio de sesión')
                            break
                        elif op == 3:
                            pin = int(input('\nIngrese su nuevo pin (solo pueden ser 5 números): '))
                            con = input('\nIngrese su contraseña: ')
                            if len(str(pin)) != 5:
                                raise TypeError('\nEl pin debe tener 5 dígitos')
                            if con != usuarios[nombre][0]:
                                raise TypeError('\nContraseña incorrecta')
                            usuarios[nombre][1] = pin
                            print('\nPin cambiado con exito, redirigiendo a inicio de sesión')
                            break
                    elif opcion == 4:
                        print('Saliendo...')
                        break
                except TypeError as IncorrectData:
                    print(IncorrectData)
                except ValueError:
                    print('El pin solo debe tener números')
    elif a == 3:
        break
    elif a == 4:
        print(usuarios)