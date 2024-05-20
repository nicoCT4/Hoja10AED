package uvg.edu.gt;

import org.junit.Test;
import static org.junit.Assert.*;

public class GrafoTest {
    @Test
    public void testAgregarArco() {
        Grafo grafo = new Grafo(3);
        grafo.agregarArco(0, 1, 5);
        assertEquals(5, grafo.getAdyacencia()[0][1]);
    }

    @Test
    public void testEliminarArco() {
        Grafo grafo = new Grafo(3);
        grafo.agregarArco(0, 1, 5);
        grafo.eliminarArco(0, 1);
        assertEquals(Integer.MAX_VALUE / 2, grafo.getAdyacencia()[0][1]);
    }
}