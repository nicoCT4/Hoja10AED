package uvg.edu.gt;

import java.util.HashMap;
import java.util.Map;

public class FloydWarshall {
    public Map<String, Map<String, Integer>> calcularDistancias(Grafo grafo) {
        Map<String, Map<String, Integer>> dist = new HashMap<>();
        Map<String, Map<String, Integer>> adyacencias = grafo.getAdyacencias();

        // Inicializar distancias
        for (String u : adyacencias.keySet()) {
            dist.put(u, new HashMap<>());
            for (String v : adyacencias.keySet()) {
                if (u.equals(v)) {
                    dist.get(u).put(v, 0);
                } else if (adyacencias.get(u).containsKey(v)) {
                    dist.get(u).put(v, adyacencias.get(u).get(v));
                } else {
                    dist.get(u).put(v, Integer.MAX_VALUE / 2); // Usar un valor alto para representar la "infinidad"
                }
            }
        }

        // Actualizar distancias usando Floyd-Warshall
        for (String k : adyacencias.keySet()) {
            for (String i : adyacencias.keySet()) {
                for (String j : adyacencias.keySet()) {
                    if (dist.get(i).get(k) != Integer.MAX_VALUE / 2 && dist.get(k).get(j) != Integer.MAX_VALUE / 2) {
                        int nuevaDistancia = dist.get(i).get(k) + dist.get(k).get(j);
                        if (dist.get(i).get(j) > nuevaDistancia) {
                            dist.get(i).put(j, nuevaDistancia);
                        }
                    }
                }
            }
        }

        // Convertir las distancias "infinidad" de vuelta a null para la representación correcta de rutas inexistentes
        for (String u : dist.keySet()) {
            for (String v : dist.get(u).keySet()) {
                if (dist.get(u).get(v) == Integer.MAX_VALUE / 2) {
                    dist.get(u).put(v, null);
                }
            }
        }

        return dist;
    }

}

