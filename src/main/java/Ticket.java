public class Ticket {

    // Atributos privados para cumplir con la encapsulación.
    private int id;
    private String descripcion;
    private boolean cerrado;

    // Constructor con las validaciones obligatorias
    public Ticket(int id, String descripcion) {

        // Rechazar identificadores no positivos lanzando una excepción.
        if (id <= 0) {
            throw new IllegalArgumentException("El identificador debe ser positivo.");
        }

        // Rechazar descripciones nulas, vacías o formadas únicamente por espacios.
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede estar en blanco.");
        }

        // Si los datos son válidos, los asignamos.
        this.id = id;
        this.descripcion = descripcion.strip(); // Texto limpio de espacios en los extremos[cite: 175]
        this.cerrado = false; // Todo ticket comienza abierto[cite: 188]
    }

    // Métodos para consultar los datos.
    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean estaCerrado() {
        return cerrado;
    }

    // Método de instancia obligatorio para cerrar la incidencia
    public void cerrar() {
        this.cerrado = true;
    }
}