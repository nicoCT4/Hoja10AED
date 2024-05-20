package uvg.edu.gt;

import java.util.*;

public class Grafo {
    private int numVertices;
    private int[][] adyacencia;

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        adyacencia = new int[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            Arrays.fill(adyacencia[i], Integer.MAX_VALUE / 2); // Usar infinito como representación de no conexión
            adyacencia[i][i] = 0; // Distancia a sí mismo es 0
        }
    }

    public void agregarArco(int origen, int destino, int peso) {
        adyacencia[origen][destino] = peso;
    }

    public void eliminarArco(int origen, int destino) {
        adyacencia[origen][destino] = Integer.MAX_VALUE / 2; // Volver a no conexión
    }

    public int[][] getAdyacencia() {
        return adyacencia;
    }

    public int getNumVertices() {
        return numVertices;
    }
}
