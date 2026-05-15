# Práctica 2 - Sistema de Búsqueda de Canciones
## Sistemas Operativos

### Integrantes
- Juan David Cruz Giraldo
- Tomas Alejandro Bermudez Guaqueta
- Juan Camilo Posso Portilla

### Dataset Seleccionado
Dataset de canciones de Spotify con los siguientes campos:
- Artista
- Nombre de la canción
- Letra
- Duración
- Emoción
- Género
- Álbum
- Fecha de lanzamiento

### Descripción del Proyecto
Sistema cliente-servidor que gestiona búsquedas eficientes en un dataset de canciones mediante tabla hash y comunicación por sockets TCP.

### Estructura de Archivos
- `p2-dataProgram.c`: Programa cliente (interfaz de usuario)
- `servidor_busqueda.c`: Servidor de búsqueda
- `datasetToBin.c`: Conversión de CSV a formato binario
- `createBinHashTable.c`: Generación de tabla hash
- `songStruct.h`: Estructura de datos Song
- `nodeAndHastTable.h`: Implementación de tabla hash
- `insert_song_data_bin.h`: Funciones de inserción y lectura
- `insert_song_hash.h`: Funciones de inserción en tabla hash
- `Makefile`: Automatización de compilación y ejecución

### Requisitos
- gcc
- Sistema operativo Linux/Unix
- Dataset: `spotify_para_c.csv`
- Librerías: pthread, rt

### Compilación y Ejecución

```bash
make
```
Este comando ejecuta en orden:
1. Compilación de todos los programas
2. Preparación de base de datos (conversión CSV y creación de tabla hash)
3. Inicio del servidor y cliente
4. Limpieza de ejecutables temporales

#### Limpieza total
```bash
make clean
```
Elimina todos los ejecutables y archivos generados.

### Criterios de Búsqueda

#### Búsqueda Principal
- Por artista y nombre de canción
- Utiliza tabla hash con manejo de colisiones mediante listas enlazadas
- Tiempo de búsqueda: < 2 segundos

#### Validaciones de Entrada
- Artista: máximo 100 caracteres
- Nombre de canción: máximo 300 caracteres
- Letra: máximo 10000 caracteres
- Duración: formato MM:SS
- Fecha: formato "Día ordinal Mes Año" (ej: "1st January 2020")

### Funcionalidades del Menú

1. **Buscar**: Busca una canción por artista y nombre configurados previamente
2. **Registrar una nueva canción**: Solicita todos los campos y agrega la canción al dataset
3. **Ingresar criterios de búsqueda**: 
   - Configurar nombre del artista
   - Configurar nombre de la canción
4. **Salir**: Desconecta el cliente (el servidor continúa ejecutándose)

### Detalles Técnicos

#### Comunicación
- Protocolo: TCP/IP
- Puerto: 3535
- Dirección: 127.0.0.1 (localhost)

#### Almacenamiento
- `songs.bin`: Dataset completo en formato binario
- `hashTableSongs.bin`: Tabla hash serializada
- Tamaño de tabla hash: 1000 buckets
- Función hash: hash = (hash * 31 + tolower(char)) % 1000

#### Manejo de Colisiones
- Lista enlazada mediante campo `nextOffset` en estructura Song
- Inserción al inicio de la lista (más eficiente)

#### Uso de Memoria
- Dataset reside en disco, no en memoria
- Consumo de memoria del proceso: < 10MB
- Uso de memoria dinámica con malloc/free

### Ejemplos de Uso
```
Bienvenido
1. Buscar
2. Registrar una nueva canción
3. Ingresar criterios de búsqueda
4. Salir
Opcion: 3

Ingrese el numero del criterio de busqueda
1. Ingresar artista
2. Ingresar canción
3. Regresar
Opcion: 1

Nombre del artista: The Beatles

Opcion: 2
Nombre de la cancion: Hey Jude

Opcion: 3
Regresando...

Opcion: 1
Buscando...

Artista: The Beatles
Cancion: Hey Jude
Album: The Beatles Again
Genero: Rock
Fecha: 26th August 1968
Duracion: 7:11
Emocion: Happy

Letra:
[Letra completa de la canción]
```


### Herramientas Utilizadas
- ChatGPT/GitHub Copilot/Claude para asistencia en desarrollo
- GCC para compilación
- Make para automatización
