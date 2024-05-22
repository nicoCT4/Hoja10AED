package uvg.edu.gt;
import java.util.HashMap;
import java.util.Map;


public class FloydWarshall {
    public Map<String, Map<String, Integer>> calcularDistancias(Grafo grafo) {
        Map<String, Map<String, Integer>> dist = new HashMap<>();
        Map<String, Map<String, Integer>> adyacencias = grafo.getAdyacencias();

        for (String u : adyacencias.keySet()) {
            dist.put(u, new HashMap<>());
            for (String v : adyacencias.keySet()) {
                if (u.equals(v)) {
                    dist.get(u).put(v, 0);
                } else if (adyacencias.get(u).containsKey(v)) {
                    dist.get(u).put(v, adyacencias.get(u).get(v));
                } else {
                    dist.get(u).put(v, null); // Usa null para representar la ausencia de ruta
                }
            }
        }

        for (String k : adyacencias.keySet()) {
            for (String i : adyacencias.keySet()) {
                for (String j : adyacencias.keySet()) {
                    if (dist.get(i).get(k) != null && dist.get(k).get(j) != null) {
                        int nuevaDistancia = dist.get(i).get(k) + dist.get(k).get(j);
                        if (dist.get(i).get(j) == null || nuevaDistancia < dist.get(i).get(j)) {
                            dist.get(i).put(j, nuevaDistancia);
                        }
                    }
                }
            }
        }
        return dist;
    }
}

