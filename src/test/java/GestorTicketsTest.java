import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class GestorTicketsTest {

    @Test
    public void testColeccionInicialmenteVaciaYEstadisticasVacias() {
        GestorTickets gestor = new GestorTickets();
        // Colección inicialmente vacía
        assertTrue(gestor.obtenerTickets().isEmpty());

        // Gestor vacío (Estadísticas)
        assertEquals(0, gestor.getTotalIncidencias());
        assertEquals(0, gestor.getAbiertas());
        assertEquals(0, gestor.getCerradas());
    }

    @Test
    public void testIdentificadoresConsecutivosYBusqueda() {
        GestorTickets gestor = new GestorTickets();

        Ticket t1 = gestor.crearTicket("Primer ticket");
        Ticket t2 = gestor.crearTicket("Segundo ticket");

        // Identificadores consecutivos
        assertEquals(1, t1.getId());
        assertEquals(2, t2.getId());

        // Búsqueda del mismo objeto
        Ticket encontrado = gestor.buscarTicket(1);
        assertNotNull(encontrado);
        assertEquals(t1, encontrado);

        // Búsqueda inexistente
        Ticket noEncontrado = gestor.buscarTicket(99);
        assertNull(noEncontrado);
    }

    @Test
    public void testCreacionInvalidaNoAlteraColeccionNiContador() {
        GestorTickets gestor = new GestorTickets();
        gestor.crearTicket("Ticket válido"); // ID 1

        // Creación inválida sin alterar colección ni contador
        assertThrows(IllegalArgumentException.class, () -> {
            gestor.crearTicket(""); // Descripción en blanco
        });

        // Comprobamos que el tamaño sigue siendo 1 y el siguiente ID será el 2
        assertEquals(1, gestor.getTotalIncidencias());
        Ticket siguiente = gestor.crearTicket("Otro válido");
        assertEquals(2, siguiente.getId());
    }

    @Test
    public void testProteccionDeListaInterna() {
        GestorTickets gestor = new GestorTickets();
        gestor.crearTicket("Test");

        ArrayList<Ticket> lista1 = gestor.obtenerTickets();
        lista1.clear(); // Intentamos modificar la lista obtenida externamente

        // Protección de la lista interna
        assertEquals(1, gestor.getTotalIncidencias());
    }

    @Test
    public void testEstadisticasConIncidenciasAbiertasYCerradas() {
        GestorTickets gestor = new GestorTickets();

        gestor.crearTicket("Ticket A");
        gestor.crearTicket("Ticket B");

        // Dos incidencias abiertas
        assertEquals(2, gestor.getTotalIncidencias());
        assertEquals(2, gestor.getAbiertas());
        assertEquals(0, gestor.getCerradas());

        // Cerramos una incidencia
        Ticket t = gestor.buscarTicket(1);
        t.cerrar();

        // Dos incidencias con una cerrada
        assertEquals(2, gestor.getTotalIncidencias());
        assertEquals(1, gestor.getAbiertas());
        assertEquals(1, gestor.getCerradas());
    }
}