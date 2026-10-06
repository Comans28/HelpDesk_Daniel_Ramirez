import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {

    @Test
    public void testEstadoInicialYAbiertoCierre() {
        Ticket t = new Ticket(1, "Fallo de red");
        // Comprobar estado inicial abierto
        assertFalse(t.estaCerrado());

        // Comprobar cierre
        t.cerrar();
        assertTrue(t.estaCerrado());

        // Comprobar cierre repetido (no debe lanzar error, solo mantenerse cerrado)
        t.cerrar();
        assertTrue(t.estaCerrado());
    }

    @Test
    public void testIdCeroOInvalidoLanzaExcepcion() {
        // Rechazo de identificador cero o negativo
        assertThrows(IllegalArgumentException.class, () -> new Ticket(0, "Prueba"));
        assertThrows(IllegalArgumentException.class, () -> new Ticket(-5, "Prueba"));
    }

    @Test
    public void testDescripcionNulaOBlnacoLanzaExcepcion() {
        // Rechazo de descripción nula o en blanco
        assertThrows(IllegalArgumentException.class, () -> new Ticket(1, null));
        assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(1, "");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(1, "   ");
        });
    }
}