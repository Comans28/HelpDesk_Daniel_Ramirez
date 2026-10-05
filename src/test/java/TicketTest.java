import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {

    @Test
    public void testCrearTicketValido() {
        // Comprobamos que un ticket con datos correctos se crea sin problemas
        Ticket t = new Ticket(1, "Fallo en la impresora");
        assertEquals(1, t.getId());
        assertEquals("Fallo en la impresora", t.getDescripcion());
        assertFalse(t.estaCerrado());
    }

    @Test
    public void testIdInvalidoLanzaExcepcion() {
        // Comprobamos que se rechazan IDs negativos o cero lanzando la excepción esperada
        assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(0, "Descripción válida");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(-3, "Descripción válida");
        });
    }

    @Test
    public void testDescripcionVaciaLanzaExcepcion() {
        // Comprobamos que se rechazan descripciones nulas, vacías o en blanco
        assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(1, "");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(1, "   ");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(1, null);
        });
    }
}