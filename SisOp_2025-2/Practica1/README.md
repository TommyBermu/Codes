# Práctica 1 - Sistema de Búsqueda de Canciones
## Sistemas Operativos - Universidad Nacional

---

## Descripción del Proyecto

Este proyecto implementa un sistema de búsqueda eficiente de canciones utilizando comunicación entre procesos, tabla hash y memoria compartida. El sistema permite buscar canciones por artista y nombre, mostrando información detallada de la canción encontrada.

---

## Dataset Utilizado

**Dataset:** Spotify Songs Dataset  
**Archivo fuente:** `spotify_para_c.csv`

### Campos del Dataset

El dataset contiene la siguiente información para cada canción:

| Campo | Descripción | Tamaño Máximo |
|-------|-------------|---------------|
| `artist` | Nombre del artista | 100 caracteres |
| `name` | Nombre de la canción | 300 caracteres |
| `text` | Letra completa de la canción | 10000 caracteres |
| `length` | Duración de la canción | 100 caracteres |
| `emotion` | Emoción asociada a la canción | 100 caracteres |
| `genre` | Género musical | 100 caracteres |
| `album` | Nombre del álbum | 300 caracteres |
| `date` | Fecha de lanzamiento | 300 caracteres |

---

## Criterios de Búsqueda Implementados

### Búsqueda Principal
- **Campo primario:** Artista (artist)
- **Campo secundario:** Nombre de la canción (name)

### Justificación
La búsqueda se implementa usando el nombre del artista como clave principal en la tabla hash, ya que:
1. Es el criterio más común para buscar canciones
2. Reduce significativamente el espacio de búsqueda
3. Permite implementar una tabla hash eficiente con resolución de colisiones mediante listas enlazadas

Una vez localizado el artista, se realiza una búsqueda lineal en su lista de canciones para encontrar la coincidencia exacta por nombre.

### Rangos de Valores Válidos
- **Artista:** Cadena de texto de hasta 100 caracteres (sin comillas)
- **Nombre de canción:** Cadena de texto de hasta 300 caracteres (sin comillas)

---

## Arquitectura del Sistema

### Procesos Implementados

El sistema consta de **dos procesos no emparentados**:

1. **Motor de Búsqueda** (`motor_busqueda.c`)
   - Proceso servidor que permanece en ejecución
   - Carga la tabla hash en memoria
   - Procesa solicitudes de búsqueda
   - Accede al archivo binario de canciones bajo demanda

2. **Interfaz de Usuario** (`p1-dataProgram.c`)
   - Proceso cliente con menú interactivo
   - Captura criterios de búsqueda del usuario
   - Envía solicitudes al motor de búsqueda
   - Presenta resultados formateados

### Comunicación entre Procesos

**Método:** Memoria compartida con sincronización mediante semáforos

- **Memoria compartida:** `/song_search_shm`
- **Semáforo de solicitudes:** `/sem_request`
- **Semáforo de respuestas:** `/sem_response`

**Estructuras de comunicación:**
```c
struct SearchRequest {
    char artist[ARTIST_MAX];
    char songName[NAME];
    int shouldExit;
};

struct SearchResponse {
    struct Song song;
    int found;
};
```

---

## Estructura de Datos

### Tabla Hash

- **Tamaño:** 1000 buckets
- **Función hash:** Hash polinomial con base 31 sobre el nombre del artista (case-insensitive)
- **Resolución de colisiones:** Lista enlazada (chaining)

```c
struct node {
    long offset;      // Posición en songs.bin
    long nextOffset;  // Siguiente nodo en la lista
};

struct hashTable {
    struct node buckets[1000];
};
```

### Indexación en Disco

La estructura de indexación sigue el siguiente esquema:

1. **songs.bin:** Archivo binario con todas las estructuras Song
2. **hashTableSongs.bin:** Tabla hash serializada con offsets a songs.bin
3. Cada nodo en la tabla hash apunta al primer registro de una lista enlazada
4. Las colisiones se resuelven mediante encadenamiento usando el campo `nextOffset`

---

## Archivos del Proyecto

### Archivos Fuente

| Archivo | Descripción |
|---------|-------------|
| `p1-dataProgram.c` | Programa principal con interfaz de usuario |
| `motor_busqueda.c` | Motor de búsqueda (proceso servidor) |
| `datasetToBin.c` | Conversión del CSV a formato binario |
| `createBinHashTable.c` | Creación de la tabla hash |
| `songStruct.h` | Definición de la estructura Song |
| `nodeAndHastTable.h` | Implementación de tabla hash y nodos |

### Archivos Generados

- `songs.bin` - Base de datos binaria de canciones
- `hashTableSongs.bin` - Tabla hash serializada

---

## Compilación y Ejecución

### Requisitos
- Compilador GCC
- Sistema operativo POSIX (Linux/Unix)
- Bibliotecas: pthread, rt

### Ejecución

```bash
git clone https://github.com/TommyBermu/SisOp2025-2.git
cd SisOp2025-2/Practica1
make
```

---

## Ejemplos de Uso

### Caso 1: Búsqueda exitosa

```
Bienvenido
1. Ingresar artista
2. Ingresar canción
3. Buscar
4. Salir
Opcion: 1

Nombre del artista: Ed Sheeran

Bienvenido
1. Ingresar artista
2. Ingresar canción
3. Buscar
4. Salir
Opcion: 2
Nombre de la cancion: Shape of You

Bienvenido
1. Ingresar artista
2. Ingresar canción
3. Buscar
4. Salir
Opcion: 3

Buscando...

Artista: Ed Sheeran
Cancion: Shape of You
Album: ÷ (Deluxe)
Genero: pop
Fecha: 2017-01-06
Duracion: 3:53
Emocion: happy

Letra:
[Letra completa de la canción...]
```

### Caso 2: Canción no encontrada

```
Opcion: 3

Buscando...

NA
```

---

## Limpieza de Recursos

Para limpiar recursos de IPC en caso de terminación anormal:

```bash
make clean
```

---

## Autores

- Juan David Cruz Giraldo
- Tomas Alejandro Bermudez Guaqueta
- Juan Camilo Posso Portilla
