import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArchivoTickets {

    // Nombre del archivo donde se guardarán los datos.
    private static final String NOMBRE_ARCHIVO = "tickets.txt";

    // 1. Guardar la lista de tickets en el archivo de texto.
    public static void guardar(ArrayList<Ticket> tickets) {
        List<String> lineas = new ArrayList<>();

        for (Ticket t : tickets) {
            // Formato de línea: ID;Descripción;EstadoCerrado.
            String linea = t.getId() + ";" + t.getDescripcion() + ";" + t.estaCerrado();
            lineas.add(linea);
        }

        Path ruta = Paths.get(NOMBRE_ARCHIVO);
        try {
            // Escribe todas las líneas en el fichero de texto usando java.nio.file.Files
            Files.write(ruta, lineas);
        } catch (IOException e) {
            System.err.println("Error al guardar los tickets en el archivo: " + e.getMessage());
        }
    }

    // 2. Cargar los tickets desde el archivo de texto al iniciar la aplicación
    public static ArrayList<Ticket> cargar() {
        ArrayList<Ticket> ticketsRecuperados = new ArrayList<>();
        Path ruta = Paths.get(NOMBRE_ARCHIVO);

        // Si el archivo todavía no existe, devolvemos la lista vacía sin errores.
        if (!Files.exists(ruta)) {
            return ticketsRecuperados;
        }

        try {
            List<String> lineas = Files.readAllLines(ruta);

            for (String linea : lineas) {
                if (linea.isBlank()) continue;


                String[] partes = linea.split(";", 3);
                if (partes.length == 3) {
                    try {
                        int id = Integer.parseInt(partes[0].trim());
                        String descripcion = partes[1].trim();
                        boolean cerrado = Boolean.parseBoolean(partes[2].trim());

                        // Reconstruimos el ticket con sus datos originales
                        Ticket t = new Ticket(id, descripcion);
                        if (cerrado) {
                            t.cerrar();
                        }
                        ticketsRecuperados.add(t);
                    } catch (Exception e) {
                        System.err.println("Aviso: No se pudo interpretar una línea del archivo: " + linea);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de tickets: " + e.getMessage());
        }

        return ticketsRecuperados;
    }
}