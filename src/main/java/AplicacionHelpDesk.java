import java.util.ArrayList;
import java.util.Scanner;

public class AplicacionHelpDesk {

    public static void main(String[] args) {
        GestorTickets gestor = new GestorTickets();
        Scanner scanner = new Scanner(System.in);

        // Al iniciar, cargamos los tickets guardados previamente en el archivo de texto
        ArrayList<Ticket> ticketsCargados = ArchivoTickets.cargar();
        gestor.cargarTicketsRecuperados(ticketsCargados);

        int opcion = 0;

        do {
            System.out.println("\n--- HELPDESK - GESTIÓN DE INCIDENCIAS ---");
            System.out.println("1. Crear nuevo ticket");
            System.out.println("2. Listar todos los tickets");
            System.out.println("3. Buscar ticket por ID");
            System.out.println("4. Cerrar ticket");
            System.out.println("5. Ver estadísticas");
            System.out.println("6. Salir y guardar");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.print("Introduce la descripción de la incidencia: ");
                        String descripcion = scanner.nextLine();
                        try {
                            Ticket nuevo = gestor.crearTicket(descripcion);
                            System.out.println("¡Ticket creado con éxito! ID asignado: " + nuevo.getId());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error de validación: " + e.getMessage());
                        }
                        break;

                    case 2:
                        ArrayList<Ticket> lista = gestor.obtenerTickets();
                        if (lista.isEmpty()) {
                            System.out.println("No hay incidencias registradas.");
                        } else {
                            System.out.println("\n--- LISTADO DE TICKETS ---");
                            for (Ticket t : lista) {
                                String estado = t.estaCerrado() ? "[CERRADO]" : "[ABIERTO]";
                                System.out.println("ID: " + t.getId() + " | Estado: " + estado + " | Descripción: " + t.getDescripcion());
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Introduce el ID del ticket a buscar: ");
                        int idBuscar = Integer.parseInt(scanner.nextLine());
                        Ticket encontrado = gestor.buscarTicket(idBuscar);
                        if (encontrado != null) {
                            String estado = encontrado.estaCerrado() ? "CERRADO" : "ABIERTO";
                            System.out.println("Encontrado -> ID: " + encontrado.getId() + " | Estado: " + estado + " | Descripción: " + encontrado.getDescripcion());
                        } else {
                            System.out.println("No se ha encontrado ningún ticket con ese ID.");
                        }
                        break;

                    case 4:
                        System.out.print("Introduce el ID del ticket que deseas cerrar: ");
                        int idCerrar = Integer.parseInt(scanner.nextLine());
                        Ticket aCerrar = gestor.buscarTicket(idCerrar);
                        if (aCerrar != null) {
                            if (aCerrar.estaCerrado()) {
                                System.out.println("Ese ticket ya estaba cerrado.");
                            } else {
                                aCerrar.cerrar();
                                System.out.println("¡Ticket con ID " + idCerrar + " cerrado correctamente!");
                            }
                        } else {
                            System.out.println("No se ha encontrado ningún ticket con ese ID.");
                        }
                        break;

                    case 5:
                        System.out.println("\n--- ESTADÍSTICAS ---");
                        System.out.println("Total de incidencias: " + gestor.getTotalIncidencias());
                        System.out.println("Incidencias abiertas: " + gestor.getAbiertas());
                        System.out.println("Incidencias cerradas: " + gestor.getCerradas());
                        break;

                    case 6:
                        // Guardamos los datos en el archivo de texto antes de salir
                        ArchivoTickets.guardar(gestor.obtenerTickets());
                        System.out.println("Datos guardados correctamente. Saliendo de la aplicación");
                        break;

                    default:
                        System.out.println("Opción no válida. Introduce un número entre 1 y 6.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido.");
            }

        } while (opcion != 6);

        scanner.close();
    }
}