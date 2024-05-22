package uvg.edu.gt;

import java.util.Map;

public class CentroGrafo {
    public String calcularCentro(Map<String, Map<String, Integer>> dist) {
        String centro = null;
        int minMaxDistancia = Integer.MAX_VALUE;

        for (String u : dist.keySet()) {
            int maxDistancia = Integer.MIN_VALUE;
            boolean alcanzable = true;
            for (Integer distancia : dist.get(u).values()) {
                if (distancia == null) {
                    alcanzable = false;
                    break;
                }
                if (distancia > maxDistancia) {
                    maxDistancia = distancia;
                }
            }
            if (alcanzable && maxDistancia < minMaxDistancia) {
                minMaxDistancia = maxDistancia;
                centro = u;
            }
        }
        return centro;
    }
}

