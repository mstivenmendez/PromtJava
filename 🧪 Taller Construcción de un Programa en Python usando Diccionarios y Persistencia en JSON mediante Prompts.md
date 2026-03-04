# 🧪 Taller: Construcción de un Programa en Java 17 usando Map y Persistencia en JSON mediante Prompts

## 📌 Contexto del taller

En el desarrollo de software moderno, saber **formular correctamente un problema** y **guiar a una IA mediante prompts claros** es tan importante como programar. En este taller, el estudiante no solo programará en Java 17, sino que **aprenderá a diseñar prompts efectivos** para obtener soluciones correctas, mantenibles y bien estructuradas.

---

## 🎯 Objetivo general

Que el estudiante sea capaz de:

- Diseñar **prompts claros y precisos**
- Construir un programa en **Java 17**
- Usar **Map / HashMap** como estructura principal de datos
- Implementar **persistencia en archivos JSON**
- Aplicar **manejo básico de errores**
- Comprender la relación entre _prompt → solución generada_

---

## 👥 Público objetivo

- Estudiantes de programación (nivel básico – intermedio)
- Cursos introductorios de Java
- Talleres de Prompt Engineering aplicado a código

---

## 🛠️ Requisitos técnicos

- JDK 17 instalado y configurado
- Maven o Gradle para gestionar dependencias
- Editor de texto o IDE (VS Code recomendado)
- Conocimientos previos:
  - Uso de `Map` / `HashMap`
  - Clases o `record`
  - Lectura/escritura de archivos
  - Bloques `try-catch`

---

## 📦 Problema a resolver

Se debe construir un **programa de consola en Java 17** que permita gestionar información usando **Map / HashMap**, con las siguientes características:

### 📂 Dominio del problema (ejemplo)

Sistema de gestión de **contactos** / **productos** / **estudiantes**
_(el docente puede elegir uno)_

Cada registro debe contener al menos:

- Identificador único
- Nombre
- Un atributo adicional (correo, precio, edad, etc.)

La información debe:

- Guardarse en un **Map<String, Registro>**
- Persistirse en un **archivo JSON**
- Recuperarse al reiniciar el programa

---

## 🧩 Estructura del taller (por retos)

---

## 🧠 Reto 1: Comprensión del problema mediante prompt

### 📌 Instrucción al estudiante

Redacta un prompt que le pida a una IA **explicar el problema** y **proponer una solución general en Java 17**.

### 🧾 Prompt esperado (ejemplo)

```



Actúa como un desarrollador senior en Java 17 y explica cómo diseñar un programa de consola para gestionar registros (contactos, productos o estudiantes) utilizando HashMap como estructura principal.

Explica:
1. Por qué usar HashMap y cómo funciona.
2. Cómo estructurar el flujo general del programa.
3. Cómo implementar lectura y escritura de archivos usando FileReader y FileWriter.
4. Cómo usar la librería org.json para convertir objetos Java a JSON y viceversa.
5. Qué excepciones deben manejarse.

No escribas aún el código completo, solo explica la arquitectura y la solución general.



```

### ✅ Resultado esperado

- Explicación del uso de Map / HashMap
- Explicación de lectura/escritura de JSON
- Flujo general del programa

```

Uso de HashMap: Conviene emplear HashMap<String, Registro> porque ofrece inserciones, búsquedas y eliminaciones en tiempo promedio O(1). La clave será el id único de cada registro; el valor encapsula los campos (nombre, atributo adicional). Este enfoque permite mantener todos los registros en memoria mientras la aplicación está activa, sin estructuras intermedias innecesarias.
Flujo general:
Inicio: cargar el archivo JSON si existe y poblar el HashMap.
Menú principal: bucle while o do-while con opciones para crear, listar, buscar, actualizar/eliminar, guardar y salir.
Operaciones CRUD: cada opción invoca métodos del servicio que reciben el HashMap y el Scanner.
Persistencia: tras operaciones de escritura (crear/actualizar/eliminar) se puede guardar inmediatamente o al elegir la opción “Guardar”.
Salida segura: antes de terminar, confirmar si se desea persistir los cambios pendientes.
Lectura/escritura JSON con FileReader/FileWriter:
FileReader abre el archivo y entrega su contenido a un StringBuilder (o se usa Files.readString). Ese texto se convierte a JSONObject/JSONArray con org.json. Cada elemento se transforma en un objeto Registro que se inserta en el HashMap.
FileWriter recibe el JSON generado a partir del HashMap (construido usando JSONObject y JSONArray) y lo guarda con formato legible.
Uso de org.json:
Para guardar: crear un JSONArray, iterar el HashMap y por cada entrada generar un JSONObject con los campos (id, nombre, atributo extra).
Para cargar: leer el JSONArray, reconstruir el Registro y añadirlo al HashMap.
Excepciones a manejar:
FileNotFoundException al intentar leer el JSON por primera vez (se interpreta como “sin datos previos”).
IOException tanto en lectura como escritura (mostrar mensaje y, si es crítico, abortar la operación).
JSONException (de org.json) cuando el archivo está corrupto o con formato inesperado; conviene loguearlo y permitir que el usuario decida si reintentar o iniciar con un HashMap vacío.
Validaciones de entrada (NumberFormatException, etc.) cuando se parsean datos provenientes del usuario.


```

---

## 🧠 Reto 2: Prompt para estructura de datos

### 📌 Instrucción

Crea un prompt que solicite a la IA **definir la estructura del `Map` principal**.

### 🧾 Prompt esperado

```




Actúa como arquitecto de software en Java 17.

Diseña la estructura de datos para un sistema de gestión en consola que utilice HashMap como almacenamiento principal.

Incluye:
1. La definición de una clase modelo (POJO) con atributos: id, nombre y un atributo adicional.
2. Getters, setters y constructor.
3. Definición del HashMap como:
   HashMap<String, ClaseModelo>
4. Explicación breve de por qué el id debe ser la clave.
5. Buenas prácticas básicas de encapsulamiento.

Genera el código Java correspondiente.




```

### ✅ Resultado esperado

- Map principal con claves bien definidas y posibilidad de estructura anidada
- Claves bien definidas
- Identificación del `id` como clave principal

```




Clase modelo (Registro):

Campos privados: String id, String nombre, String atributoExtra (puede representarse según el dominio: correo, precio, edad, etc.).
Constructor que reciba los tres atributos y valide que id y nombre no son nulos/vacíos.
Getters y setters para cada campo, permitiendo mutaciones controladas si se requiere edición posterior.
Sobrescribir toString() para facilitar la impresión de registros en consola.
Si se desea igualdad basada en id, sobrescribir equals()/hashCode(); no es obligatorio, pero ayuda si en el futuro se comparan registros.
Estructura del Map:

Declarar como private final Map<String, Registro> registros = new HashMap<>();.
La clave (String) es el id. Esto garantiza unicidad, permite acceso directo al registro y evita recorrer la colección completa para búsquedas.
El valor es un objeto Registro que encapsula la información restante.
Se pueden exponer métodos como agregarRegistro(Registro registro), obtenerRegistro(String id), listarRegistros(), eliminarRegistro(String id) sobre una clase Repositorio o Servicio.
Buenas prácticas:

Mantener el HashMap privado y exponer métodos controlados para manipularlo (encapsulamiento).
Validar que no se inserten ids duplicados y que los getters/setters apliquen comprobaciones básicas.
Si el dominio requiere más atributos, es preferible ampliar la clase Registro antes que agregar mapas anidados complejos.
Documentar con comentarios breves el propósito de cada método para que el programa sea didáctico.






```

---

## 🧠 Reto 3: Prompt para persistencia en JSON

### 📌 Instrucción

Diseña un prompt que le pida a la IA **crear métodos o clases para guardar y cargar datos desde un archivo JSON usando Java**.

### 🧾 Prompt esperado

```




Actúa como desarrollador experto en Java 17.

Crea los métodos necesarios para:

1. Guardar un HashMap<String, ObjetoModelo> en un archivo JSON usando FileWriter y la librería org.json.
2. Cargar los datos desde un archivo JSON usando FileReader.
3. Convertir objetos Java a JSONObject.
4. Manejar correctamente FileNotFoundException e IOException.

El código debe:
- Ser simple y didáctico.
- Estar separado en métodos.
- Incluir comentarios explicativos.




```

### ✅ Resultado esperado

- Uso de `FileWriter` para escribir datos JSON
- Uso de `FileReader` para leer y reconstruir el `Map`
- Manejo de `FileNotFoundException`

```




Guardar (Map → archivo JSON)

Recibir el Map<String, Registro> y la ruta del archivo.
Crear un JSONArray. Iterar cada entrada del Map, construir un JSONObject con id, nombre, atributoExtra y añadirlo al arreglo.
Abrir un FileWriter dentro de un bloque try-with-resources, escribir jsonArray.toString(2) (para formato legible) y cerrar automáticamente.
Capturar IOException: mostrar mensaje y, si corresponde, relanzar como excepción propia o registrar el error.
Cargar (archivo JSON → Map)

Verificar si el archivo existe; si no, devolver un HashMap vacío.
Abrir un FileReader con try-with-resources, leer todo el contenido (usando StringBuilder o Files.readString).
Convertir la cadena a JSONArray. Para cada elemento: crear un nuevo Registro, usando los campos recuperados, y colocarlo en el Map con la clave id.
Manejar JSONException si el archivo está corrupto y permitir continuar con datos vacíos o avisar al usuario.
En todos los casos cerrar los recursos automáticamente con try-with-resources.
Conversión a JSON

Se pueden añadir métodos auxiliares en Registro como toJson() y un constructor estático fromJson(JSONObject json) para centralizar la lógica de serialización/deserialización.
Excepciones

FileNotFoundException: interpretarla como que aún no hay datos; crear el archivo en la siguiente escritura.
IOException: mostrar mensaje amigable y no detener la aplicación (a menos que el usuario decida salir).
JSONException: loguear la causa y permitir al usuario iniciar con el Map vacío para evitar bloqueos.



```

---

## 🧠 Reto 4: Prompt para menú interactivo

### 📌 Instrucción

Formula un prompt para crear un **menú de consola en Java 17** que permita interactuar con el sistema.

### 🧾 Prompt esperado

```


Actúa como instructor de programación en Java.

Genera un menú interactivo de consola usando Scanner que permita:

1. Agregar registro
2. Listar registros
3. Buscar por id
4. Eliminar registro
5. Guardar datos en JSON
6. Salir del programa

Requisitos:
- Usar un bucle while o do-while.
- Validar entradas básicas.
- Llamar a métodos previamente definidos.
- Mantener el código organizado.




```

### ✅ Resultado esperado

- Menú con `while`
- Uso de `Scanner`
- Llamado a funciones

```

Estructura del menú

Usar un Scanner compartido para leer desde System.in.
Mantener un bucle while (activo) que muestre las opciones numeradas y lea la selección del usuario (switch o if).
Opciones típicas: 1) Agregar, 2) Listar, 3) Buscar por id, 4) Eliminar, 5) Guardar, 6) Salir.
Para entradas no válidas, mostrar mensaje y repetir el menú sin lanzar excepciones.
Invocación de métodos

Cada caso del switch delega a métodos del servicio/repositorio que ya manipulan el HashMap.
Ejemplo: case 1 -> servicio.agregar(scanner); donde servicio pide los campos y crea el registro.
El método “Guardar” llama al componente de persistencia (del Reto 3).
El caso “Salir” pide confirmación si hay cambios sin guardar.
Validaciones básicas

Verificar que los campos requeridos (id, nombre) no estén vacíos.
Para campos numéricos, capturar NumberFormatException y solicitar nuevamente la entrada.
Mostrar mensajes claros para operaciones exitosas o fallidas (por ejemplo, “id no encontrado”).








```

---

## 🧠 Reto 5: Integración final mediante prompt maestro

### 📌 Instrucción

Construye un **prompt maestro** que integre todo el sistema.

### 🧾 Prompt maestro esperado

```



Actúa como desarrollador senior en Java 17.

Genera un programa completo de consola que:

- Gestione registros usando HashMap<String, Modelo>.
- Tenga una clase modelo con id, nombre y un atributo adicional.
- Permita agregar, listar, buscar y eliminar registros.
- Persista los datos en un archivo JSON usando FileWriter, FileReader y la librería org.json.
- Cargue los datos automáticamente al iniciar el programa.
- Maneje excepciones básicas.
- Incluya un menú interactivo con Scanner.
- Siga buenas prácticas de organización del código.

Entrega:
- Clase modelo
- Clase principal
- Métodos de persistencia
- Comentarios explicativos



```

---

## 📋 Entregables del estudiante

1. Documento con:
   - Prompts diseñados
   - Explicación breve de cada prompt
2. Código fuente en Java 17
   - 
3. Archivo JSON generado por el programa
