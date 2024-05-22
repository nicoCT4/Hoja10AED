package uvg.edu.gt;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class FloydWarshallTest {
    private Grafo grafo;
    private FloydWarshall floydWarshall;

    @Before
    public void setUp() {
        grafo = new Grafo();
        floydWarshall = new FloydWarshall();
    }

    @Test
    public void testCalcularDistancias() {
        grafo.agregarArco("Mixco", "Antigua", 30);
        grafo.agregarArco("Antigua", "Escuintla", 25);
        grafo.agregarArco("Escuintla", "SantaLucia", 15);

        Map<String, Map<String, Integer>> distancias = floydWarshall.calcularDistancias(grafo);

        assertEquals((Integer) 30, distancias.get("Mixco").get("Antigua"));
        assertEquals((Integer) 55, distancias.get("Mixco").get("Escuintla"));
        assertEquals((Integer) 70, distancias.get("Mixco").get("SantaLucia"));
        assertEquals((Integer) 25, distancias.get("Antigua").get("Escuintla"));
        assertEquals((Integer) 40, distancias.get("Antigua").get("SantaLucia"));
        assertEquals((Integer) 15, distancias.get("Escuintla").get("SantaLucia"));
    }
}
