package uvg.edu.gt;

public class FloydWarshall {
    private int[][] dist;
    private int[][] next;

    public FloydWarshall(Grafo grafo) {
        int n = grafo.getNumVertices();
        dist = new int[n][n];
        next = new int[n][n];

        // Inicializar distancias y siguiente
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = grafo.getAdyacencia()[i][j];
                if (dist[i][j] != Integer.MAX_VALUE / 2 && i != j) {
                    next[i][j] = j;
                }
            }
        }

        // Aplicar Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
    }

    public int[][] getDist() {
        return dist;
    }

    public List<Integer> getPath(int i, int j) {
        if (dist[i][j] == Integer.MAX_VALUE / 2) return null; // No hay camino
        List<Integer> path = new ArrayList<>();
        int at = i;
        while (at != j) {
            path.add(at);
            at = next[at][j];
        }
        path.add(j);
        return path;
    }

    public int getCenter() {
        int n = dist.length;
        int[] eccentricity = new int[n];
        Arrays.fill(eccentricity, 0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    eccentricity[i] = Math.max(eccentricity[i], dist[i][j]);
                }
            }
        }

        int center = 0;
        for (int i = 1; i < n; i++) {
            if (eccentricity[i] < eccentricity[center]) {
                center = i;
            }
        }

        return center;
    }
}

