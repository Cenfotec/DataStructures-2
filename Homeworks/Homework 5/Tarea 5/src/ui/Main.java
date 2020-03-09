package ui;

import bl.Map;
import tl.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;
    static Controller controller = new Controller();

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
        out.println("1. [Open Hash] Create");
        out.println("2. [Open Hash] Search");
        out.println("3. [Open Hash] Find by Lastname");
        out.println("4. Detener la aplicación");
    }

    public static void detenerAplicacion() {
        out.println("[!] Deteniendo la aplicación...");
        System.exit(0);
    }

    public static boolean seleccionarOpcion(String opcion) throws IOException {
        boolean menu = true;

        if (opcion.equals("1")) {
            createMap();
        }

        if (opcion.equals("2")) {
            searchMap();
        }

        if (opcion.equals("3")) {
            getPeopleByLastname();
        }

        if (opcion.equals("4")) {
            detenerAplicacion();
        }

        return menu;
    }

    public static void createMap() throws IOException {
        String key;
        String[] personValue = new String[2];
        while (true) {
            System.out.println("'exit' to quit.");
            System.out.print("Digite una llave: ");
            key = br.readLine();
            if (key.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.print("Digite el nombre: ");
            personValue[0] = br.readLine();
            System.out.print("Digite el apellido: ");
            personValue[1] = br.readLine();

            controller.mapInsert(Integer.parseInt(key), personValue);
        }
    }

    public static void searchMap() throws IOException {
        System.out.print("Digite una llave para buscar: ");
        int key = Integer.parseInt(br.readLine());
        System.out.println("Resultado: " + controller.searchMap(key));
    }

    public static void getPeopleByLastname() throws IOException {
        System.out.print("Digite el apellido: ");
        String apellido = br.readLine();
        String[] values = controller.getPeopleByLastname(apellido);
        if (values.length == 0) {
            out.println("No se encontró a nadie con el apellido: " + apellido);
        } else {
            String res = "";
            for (int i = 0; i < values.length; i++) {
                res += values[i] + "\n";
            }
            out.println(res.substring(0, res.length()-1));
        }

    }
}