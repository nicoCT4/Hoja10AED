package uvg.edu.gt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Grafo grafo;
    private static FloydWarshall floydWarshall;
    private static CentroGrafo centroGrafo;
    private static Map<String, Map<String, Integer>> distancias;

    public static void main(String[] args) {
        grafo = new Grafo();
        floydWarshall = new FloydWarshall();
        centroGrafo = new CentroGrafo();

        try {
            leerArchivo("C:/Users/nicol/OneDrive/Documents/UVG/Tercer Semestre/Algoritmos y Estructura de Datos/Hoja10AED/hoja10/src/main/java/uvg/edu/gt/guategrafo.txt", grafo);
            distancias = floydWarshall.calcularDistancias(grafo);
            mostrarMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void leerArchivo(String archivo, Grafo grafo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            String ciudad1 = partes[0].trim();
            String ciudad2 = partes[1].trim();
            int distancia = Integer.parseInt(partes[2].trim());
            grafo.agregarArco(ciudad1, ciudad2, distancia);
        }
        br.close();
    }

    private static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Encontrar la ruta más corta entre dos ciudades");
            System.out.println("2. Indicar la ciudad que queda en el centro del grafo");
            System.out.println("3. Modificar el grafo");
            System.out.println("4. Finalizar el programa");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    encontrarRutaMasCorta(scanner);
                    break;
                case 2:
                    mostrarCentroDelGrafo();
                    break;
                case 3:
                    modificarGrafo(scanner);
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    private static void encontrarRutaMasCorta(Scanner scanner) {
        System.out.print("Ingrese la ciudad origen: ");
        String origen = scanner.nextLine().trim();
        System.out.print("Ingrese la ciudad destino: ");
        String destino = scanner.nextLine().trim();
    
        if (distancias.containsKey(origen) && distancias.get(origen).containsKey(destino)) {
            Integer distancia = distancias.get(origen).get(destino);
            if (distancia != null) {
                System.out.println("La distancia más corta de " + origen + " a " + destino + " es: " + distancia + " KM");
            } else {
                System.out.println("No hay ruta a este destino");
            }
        } else {
            System.out.println("No hay ruta a este destino");
        }
    }
    

    private static void mostrarCentroDelGrafo() {
        String centro = centroGrafo.calcularCentro(distancias);
        System.out.println("La ciudad que queda en el centro del grafo es: " + centro);
    }    

    private static void modificarGrafo(Scanner scanner) {
        System.out.println("Seleccione una opción:");
        System.out.println("a) Interrupción de tráfico entre un par de ciudades");
        System.out.println("b) Establecer una conexión entre ciudad1 y ciudad2 con valor de x KM de distancia");
        char opcion = scanner.nextLine().charAt(0);

        if (opcion == 'a') {
            System.out.print("Ingrese la ciudad origen: ");
            String origen = scanner.nextLine().trim();
            System.out.print("Ingrese la ciudad destino: ");
            String destino = scanner.nextLine().trim();
            if (grafo.getAdyacencias().containsKey(origen) && grafo.getAdyacencias().get(origen).containsKey(destino)) {
                grafo.getAdyacencias().get(origen).remove(destino);
                System.out.println("Interrupción de tráfico registrada entre " + origen + " y " + destino);
            } else {
                System.out.println("No existe una conexión directa entre " + origen + " y " + destino);
            }
        } else if (opcion == 'b') {
            System.out.print("Ingrese la ciudad origen: ");
            String origen = scanner.nextLine().trim();
            System.out.print("Ingrese la ciudad destino: ");
            String destino = scanner.nextLine().trim();
            System.out.print("Ingrese la distancia en KM: ");
            int distancia = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            grafo.agregarArco(origen, destino, distancia);
            System.out.println("Conexión establecida entre " + origen + " y " + destino + " con una distancia de " + distancia + " KM");
        } else {
            System.out.println("Opción no válida. Intente de nuevo.");
        }

        // Recalcular distancias y el centro del grafo después de modificar el grafo
        distancias = floydWarshall.calcularDistancias(grafo);
    }
}




