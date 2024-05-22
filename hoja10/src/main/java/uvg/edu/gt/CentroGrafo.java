package uvg.edu.gt;

import java.util.Map;

public class CentroGrafo {
    public String calcularCentro(Map<String, Map<String, Integer>> dist) {
        String centro = null;
        int minMaxDistancia = Integer.MAX_VALUE;

        for (String u : dist.keySet()) {
            int maxDistancia = dist.get(u).values().stream().max(Integer::compareTo).orElse(Integer.MAX_VALUE);
            if (maxDistancia < minMaxDistancia) {
                minMaxDistancia = maxDistancia;
                centro = u;
            }
        }
        return centro;
    }
}


