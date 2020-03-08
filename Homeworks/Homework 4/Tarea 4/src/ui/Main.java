package ui;

import bl.AVLTree;
import bl.AVLTreeNode;
import bl.BPlusTree;
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
        out.println("1. [AVL] Create");
        out.println("2. [AVL] PreOrder");
        out.println("3. [AVL] InOrder");
        out.println("4. [AVL] PostOrder");
        out.println("5. [BPlus] Create");
        out.println("6. [BPlus] Delete");
        out.println("7. [BPlus] Search");
        out.println("8. Detener la aplicación");
    }

    public static void detenerAplicacion() {
        out.println("[!] Deteniendo la aplicación...");
        System.exit(0);
    }

    public static boolean seleccionarOpcion(String opcion) throws IOException {
        boolean menu = true;

        if (opcion.equals("1")) {
            createAVLTree();
        }

        if (opcion.equals("2")) {
            showPreOrder();
        }

        if (opcion.equals("3")) {
            showInOrder();
        }

        if (opcion.equals("4")) {
            showPostOrder();
        }

        if (opcion.equals("5")) {
            createBPlusTree();
        }

        if (opcion.equals("6")) {
            deleteBPlusNode();
        }

        if (opcion.equals("7")) {
            searchBPlus();
        }

        if (opcion.equals("8")) {
            detenerAplicacion();
        }

        return menu;
    }

    public static void createAVLTree() throws IOException {
        String input;
        while (true) {
            System.out.println("'exit' to quit.");
            System.out.print("Digite un numero: ");
            input = br.readLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            controller.createAVLTree(input);
        }
    }

    public static void showPreOrder() {
        controller.getAvlTree().showPreOrder(controller.getAvlTree().getRoot());
    }

    public static void showInOrder() {
        controller.getAvlTree().showInOrder(controller.getAvlTree().getRoot());
    }

    public static void showPostOrder() {
        controller.getAvlTree().showPostOrder(controller.getAvlTree().getRoot());
    }

    public static void createBPlusTree() throws IOException {
        String key;
        String value;
        while (true) {
            System.out.println("'exit' to quit.");
            System.out.print("Digite una llave: ");
            key = br.readLine();
            if (key.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.print("Digite un valor: ");
            value = br.readLine();

            controller.bPlusTreeInsert(key, value);
        }
    }

    public static void deleteBPlusNode() throws IOException {
        System.out.print("Digite una llave para eliminar: ");
        String key = br.readLine();
        try {
            controller.bPlusTreeDelete(key);
            System.out.println("La llave " + key + " ha sido eliminada!");
        } catch (Exception e) {
            System.out.println("No se pudo eliminar la llave " + key);
        }
    }

    public static void searchBPlus() throws IOException {
        System.out.print("Digite una llave para buscar: ");
        String key = br.readLine();
        System.out.println(controller.bPlusTreeSearch(key));
    }
}