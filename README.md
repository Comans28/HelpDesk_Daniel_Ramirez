# Proyecto HelpDesk - Gestión de Incidencias

Aplicación de consola desarrollada en Java para la gestión y seguimiento de tickets de soporte técnico, siguiendo los principios de programación orientada a objetos, pruebas unitarias y persistencia en ficheros.

---

## 1. Introducción
* **Qué es:** Una aplicación de consola desarrollada en Java orientada a la gestión y seguimiento automatizado de tickets de soporte técnico e incidencias.
* **Para quién es:** Diseñada para departamentos de soporte técnico, administradores de sistemas y equipos de atención al cliente que requieran un control rápido y local de incidencias.
* **Qué problema resuelve:** Evita la desorganización en la resolución de problemas técnicos mediante un flujo estructurado que permite registrar fallos, consultar su estado (abierto/cerrado), buscar por identificador único y mantener un registro persistente en disco sin pérdida de datos entre ejecuciones.

---

## 2. Objetivos del proyecto
* **Objetivo general:** Desarrollar un sistema de software robusto, modular y testeable para la gestión de incidencias técnicas en Java, aplicando buenas prácticas de programación orientada a objetos (POO) y control de versiones.
* **Objetivos específicos:**
  1. Implementar validaciones estrictas en el modelo de datos (`Ticket`) mediante control de excepciones (`IllegalArgumentException`).
  2. Desarrollar una lógica de negocio eficiente (`GestorTickets`) para la administración de colecciones y generación automática de identificadores consecutivos.
  3. Integrar un sistema de persistencia en ficheros planos utilizando la API moderna `java.nio.file.Files`.
  4. Garantizar la calidad del código mediante pruebas unitarias exhaustivas con JUnit 5 cubriendo los casos obligatorios de la rúbrica.

---

## 3. Requisitos funcionales y no funcionales
### Requisitos del MVP (Producto Mínimo Viable)
* **RF1:** Creación de nuevos tickets requiriendo obligatoriamente una descripción válida.
* **RF2:** Listado de todas las incidencias registradas mostrando su estado actual y descripción.
* **RF3:** Búsqueda individual de tickets mediante su identificador numérico único.
* **RF4:** Cierre de incidencias abiertas con control de estados repetidos.
* **RF5:** Visualización de estadísticas del sistema (total de incidencias, abiertas y cerradas).
* **RF6:** Persistencia automática de los datos al salir de la aplicación y carga al iniciar.
* **RNF1:** Desarrollado en Java 17 o superior, utilizando Maven como gestor de dependencias.
* **RNF2:** Inclusión de tests unitarios obligatorios con JUnit 5.

### Mejoras futuras
* Implementación de una interfaz gráfica de usuario (GUI) en JavaFX o una interfaz web.
* Migración del sistema de ficheros planos (`tickets.txt`) a una base de datos relacional (MySQL / H2).
* Control de concurrencia y acceso multiusuario.

---

## 4. Análisis y diseño de la arquitectura (Responsabilidades de las clases)
* **`Ticket` (Modelo):** Representa la incidencia individual. Sus responsabilidades incluyen almacenar los atributos principales (`id`, `descripcion`, `cerrado`), gestionar el estado inicial y los cambios de estado, y aplicar validaciones estrictas en el constructor y métodos (lanzando excepciones ante identificadores inválidos o descripciones vacías).
* **`GestorTickets` (Lógica de Negocio):** Administra la colección de incidencias (`ArrayList<Ticket>`). Se encarga de la generación automática y secuencial de identificadores únicos, las operaciones de búsqueda por ID, la protección de la lista interna frente a modificaciones externas no controladas, y el cálculo de estadísticas del sistema.
* **`ArchivoTickets` (Persistencia):** Gestiona el almacenamiento externo de los datos utilizando la API `java.nio.file.Files`. Es responsable de volcar la información de los tickets en el fichero plano (`tickets.txt`) al salir de la aplicación y de recuperarla al iniciar el sistema.
* **`AplicacionHelpDesk` (Interfaz / Menú Principal):** Contiene el punto de entrada (`main`) de la aplicación. Muestra el menú interactivo por consola, captura la entrada del usuario mediante `Scanner`, valida las opciones numéricas y coordina la carga inicial y el guardado final de los datos.
* **`TicketTest` y `GestorTicketsTest` (Pruebas Unitarias):** Clases de prueba implementadas con JUnit 5 que cubren de forma exhaustiva los casos obligatorios de la rúbrica (estado inicial, cierres, colecciones vacías, IDs consecutivos, búsquedas y estadísticas).

---




## 7. Instrucciones de Ejecución

### Requisitos previos
* **Java Development Kit (JDK)** versión 17 o superior.
* **Maven** para la gestión de dependencias y pruebas.
* Un entorno de desarrollo compatible como **IntelliJ IDEA**.

### Ejecución de la Aplicación Principal
1. Abre el proyecto en tu entorno de desarrollo.
2. Localiza la clase principal en la ruta `src/main/java/AplicacionHelpDesk.java`.
3. Ejecuta el método `main` (puedes usar el botón verde de ejecución `▶` al lado de la clase en IntelliJ).
4. Sigue las instrucciones del menú interactivo en la consola para crear, buscar, cerrar tickets o ver estadísticas.

### Ejecución de las Pruebas Unitarias
1. Dirígete a la carpeta `src/test/java`.
2. Haz clic derecho sobre las clases `TicketTest` o `GestorTicketsTest` y selecciona la opción de ejecutar los tests con JUnit.
