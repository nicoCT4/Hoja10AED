package uvg.edu.gt;

import java.util.HashMap;
import java.util.Map;

public class Grafo {
    private Map<String, Map<String, Integer>> adyacencias;

    public Grafo() {
        adyacencias = new HashMap<>();
    }

    public void agregarArco(String ciudad1, String ciudad2, int distancia) {
        adyacencias.putIfAbsent(ciudad1, new HashMap<>());
        adyacencias.get(ciudad1).put(ciudad2, distancia);
    }

    public Map<String, Map<String, Integer>> getAdyacencias() {
        return adyacencias;
    }
}

