import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;
    static GrafoMatrixAdyacencia primerGrafo;
    static GrafoMatrixAdyacencia segundoGrafo;

    public static void main(String[] args) throws IOException {
        menuPrincipal();
    }

    public static void menuPrincipal() throws IOException {
        String opcion;
        boolean menu;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            menu = seleccionarOpcion(opcion);
        } while (menu);
    }

    public static String leerOpcion() throws IOException {
        out.println("----------------------------------------");
        out.print("Seleccione una opción: ");
        String opcion = br.readLine();
        return opcion;
    }

    public static void mostrarMenu() {
        out.println("\n----------------------------------------");
        out.println("1. Crear primer grafo");
        out.println("2. Crear segundo grafo");
        out.println("3. Insertar primer grafo");
        out.println("4. Insertar segundo grafo");
        out.println("5. Mostrar primer grafo");
        out.println("6. Mostrar segundo grafo");
        out.println("3. Detener la aplicación");
    }

    public static void detenerAplicacion() {
        out.println("[!] Deteniendo la aplicación...");
        System.exit(0);
    }

    public static boolean seleccionarOpcion(String opcion) throws IOException {
        boolean menu = true;

        if (opcion.equals("1")) {
            crearPrimerGrafo();
        }

        if (opcion.equals("2")) {
            crearSegundoGrafo();
        }

        if (opcion.equals("3")) {
            insertarArtistaPrimerGrafo();
        }

        if (opcion.equals("4")) {
            insertarArtistaSegundoGrafo();
        }

        if (opcion.equals("5")) {
            mostrarPrimerGrafo();
        }

        if (opcion.equals("6")) {
            mostrarSegundoGrafo();
        }

        if (opcion.equals("7")) {
            detenerAplicacion();
        }

        return menu;
    }

    private static void crearPrimerGrafo() throws IOException {
        int artistas;

        System.out.println("Digite cantidad de artistas: ");
        artistas = Integer.parseInt(br.readLine());

        primerGrafo = new GrafoMatrixAdyacencia(8, artistas);
        System.out.println("[!] Primer grafo has sido creado!");
    }

    private static void crearSegundoGrafo() throws IOException {
        int verticies, artistas;

        System.out.println("Digite cantidad de vertices: ");
        verticies = Integer.parseInt(br.readLine());

        System.out.println("Digite cantidad de artistas: ");
        artistas = Integer.parseInt(br.readLine());

        segundoGrafo = new GrafoMatrixAdyacencia(verticies, artistas);
        System.out.println("[!] Segundo grafo has sido creado!");
    }

    private static void insertarArtistaPrimerGrafo() throws IOException {
        int artistas, a1, a2, dist;
        System.out.println("Cantidad de artistas por insertar: " );
        artistas = Integer.parseInt(br.readLine());
        for (int i = 0; i < artistas; i++) {
            System.out.print("Arista #1: ");
            a1 = Integer.parseInt(br.readLine());

            System.out.print("Arista #2: ");
            a2 = Integer.parseInt(br.readLine());

            System.out.print("Distancia: ");
            dist = Integer.parseInt(br.readLine());

            primerGrafo.insertaArista(a1, a2, dist);
            System.out.println("[!] Se ha insertado la artista!");
        }
    }

    public static void insertarArtistaSegundoGrafo() throws IOException {
        int artistas, a1, a2, dist;
        System.out.println("Cantidad de artistas por insertar: " );
        artistas = Integer.parseInt(br.readLine());
        for (int i = 0; i < artistas; i++) {
            System.out.print("Arista #1: ");
            a1 = Integer.parseInt(br.readLine());

            System.out.print("Arista #2: ");
            a2 = Integer.parseInt(br.readLine());

            System.out.print("Distancia: ");
            dist = Integer.parseInt(br.readLine());

            segundoGrafo.insertaArista(a1, a2, dist);
            System.out.println("[!] Se ha insertado la artista!");
        }
    }

    public static void mostrarPrimerGrafo() {
        primerGrafo.impMatrix();
    }

    public static void mostrarSegundoGrafo() {
        segundoGrafo.impMatrix();
    }
}