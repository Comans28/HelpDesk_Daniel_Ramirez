import java.util.ArrayList;

public class GestorTickets {

    private ArrayList<Ticket> tickets;
    private int siguienteId;

    public GestorTickets() {
        this.tickets = new ArrayList<>();
        this.siguienteId = 1;
    }

    // 1. Creación de tickets controlada.
    public Ticket crearTicket(String descripcion) {
        // Si Ticket lanza una excepción por datos inválidos, el flujo se corta aquí.
        // Así no añadimos nada a la lista ni consumimos el identificador si falla
        Ticket nuevo = new Ticket(siguienteId, descripcion);

        tickets.add(nuevo);
        siguienteId++;
        return nuevo;
    }

    // 2. Búsqueda por identificador.
    public Ticket buscarTicket(int idBuscado) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == idBuscado) {
                return ticket;
            }
        }
        return null;
    }

    // 3. Devolver copia de la lista conservando los mismos objetos.
    public ArrayList<Ticket> obtenerTickets() {
        return new ArrayList<>(tickets);
    }

    // 4. Estadísticas obligatorias.
    public int getTotalIncidencias() {
        return tickets.size();
    }

    public int getAbiertas() {
        int abiertas = 0;
        for (Ticket t : tickets) {
            if (!t.estaCerrado()) {
                abiertas++;
            }
        }
        return abiertas;
    }

    public int getCerradas() {
        int cerradas = 0;
        for (Ticket t : tickets) {
            if (t.estaCerrado()) {
                cerradas++;
            }
        }
        return cerradas;
    }

    // 5. Método auxiliar para la persistencia (cuando carguemos el archivo de texto)
    public void cargarTicketsRecuperados(ArrayList<Ticket> ticketsRecuperados) {
        this.tickets = ticketsRecuperados;
        this.siguienteId = 1;
        for (Ticket t : tickets) {
            if (t.getId() >= this.siguienteId) {
                this.siguienteId = t.getId() + 1;
            }
        }
    }
}