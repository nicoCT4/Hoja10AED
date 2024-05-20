package uvg.edu.gt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = leerGrafoDesdeArchivo("guategrafo.txt");
        Floyd fw = new FloydWarshall(grafo);

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("1. Ruta más corta entre ciudades");
            System.out.println("2. Centro del grafo");
            System.out.println("3. Modificar el grafo");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese ciudad origen: ");
                    int origen = scanner.nextInt();
                    System.out.println("Ingrese ciudad destino: ");
                    int destino = scanner.nextInt();
                    List<Integer> ruta = fw.getPath(origen, destino);
                    if (ruta == null) {
                        System.out.println("No hay ruta entre esas ciudades.");
                    } else {
                        System.out.println("Ruta más corta: " + ruta);
                        System.out.println("Distancia: " + fw.getDist()[origen][destino]);
                    }
                    break;
                case 2:
                    int centro = fw.getCenter();
                    System.out.println("El centro del grafo es la ciudad: " + centro);
                    break;
                case 3:
                    System.out.println("1. Interrupción de tráfico");
                    System.out.println("2. Establecer nueva conexión");
                    int subOpcion = scanner.nextInt();
                    if (subOpcion == 1) {
                        System.out.println("Ingrese ciudad origen: ");
                        origen = scanner.nextInt();
                        System.out.println("Ingrese ciudad destino: ");
                        destino = scanner.nextInt();
                        grafo.eliminarArco(origen, destino);
                    } else if (subOpcion == 2) {
                        System.out.println("Ingrese ciudad origen: ");
                        origen = scanner.nextInt();
                        System.out.println("Ingrese ciudad destino: ");
                        destino = scanner.nextInt();
                        System.out.println("Ingrese la distancia (KM): ");
                        int distancia = scanner.nextInt();
                        grafo.agregarArco(origen, destino, distancia);
                    }
                    fw = new FloydWarshall(grafo); // Recalcular Floyd-Warshall
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        scanner.close();
    }

    private static Grafo leerGrafoDesdeArchivo(String nombreArchivo) {
        try {
            Scanner scanner = new Scanner(new File(nombreArchivo));
            int numVertices = scanner.nextInt();
            Grafo grafo = new Grafo(numVertices);
            while (scanner.hasNext()) {
                String ciudad1 = scanner.next();
                String ciudad2 = scanner.next();
                int km = scanner.nextInt();
                int origen = Integer.parseInt(ciudad1);
                int destino = Integer.parseInt(ciudad2);
                grafo.agregarArco(origen, destino, km);
            }
            scanner.close();
            return grafo;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

