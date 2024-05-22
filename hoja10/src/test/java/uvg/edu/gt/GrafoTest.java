package uvg.edu.gt;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GrafoTest {
    private Grafo grafo;

    @Before
    public void setUp() {
        grafo = new Grafo();
    }

    @Test
    public void testAgregarArco() {
        grafo.agregarArco("Mixco", "Antigua", 30);
        grafo.agregarArco("Antigua", "Escuintla", 25);

        assertTrue(grafo.getAdyacencias().containsKey("Mixco"));
        assertTrue(grafo.getAdyacencias().get("Mixco").containsKey("Antigua"));
        assertEquals((Integer) 30, grafo.getAdyacencias().get("Mixco").get("Antigua"));

        assertTrue(grafo.getAdyacencias().containsKey("Antigua"));
        assertTrue(grafo.getAdyacencias().get("Antigua").containsKey("Escuintla"));
        assertEquals((Integer) 25, grafo.getAdyacencias().get("Antigua").get("Escuintla"));
    }

    @Test
    public void testEliminarArco() {
        grafo.agregarArco("Mixco", "Antigua", 30);
        grafo.agregarArco("Antigua", "Escuintla", 25);

        grafo.getAdyacencias().get("Mixco").remove("Antigua");

        assertFalse(grafo.getAdyacencias().get("Mixco").containsKey("Antigua"));
    }
}

