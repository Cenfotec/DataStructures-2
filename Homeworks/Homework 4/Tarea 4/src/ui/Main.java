package ui;

import bl.AVLTree;
import bl.AVLTreeNode;
import bl.BPlusTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;

    static AVLTree avlTree;
    static BPlusTree bPlusTree;

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
            showPreOrder(avlTree);
        }

        if (opcion.equals("3")) {
            showInOrder(avlTree);
        }

        if (opcion.equals("4")) {
            showPostOrder(avlTree);
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
        avlTree = new AVLTree();

        String input;
        while (true) {
            System.out.println("'exit' to quit.");
            System.out.print("Digite un numero: ");
            input = br.readLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            AVLTreeNode node = new AVLTreeNode(Integer.parseInt(input));
            avlTree.setRoot(avlTree.insert(avlTree.getRoot(), node.getValue()));
        }
    }

    public static void showPreOrder(AVLTree avlTree) {
        avlTree.showPreOrder(avlTree.getRoot());
    }

    public static void showInOrder(AVLTree avlTree) {
        avlTree.showInOrder(avlTree.getRoot());
    }

    public static void showPostOrder(AVLTree avlTree) {
        avlTree.showPostOrder(avlTree.getRoot());
    }

    public static void createBPlusTree() throws IOException {
        bPlusTree = new BPlusTree();

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

            bPlusTree.insert(key, value);
        }
    }

    public static void deleteBPlusNode() throws IOException {
        System.out.print("Digite una llave para eliminar: ");
        String key = br.readLine();
        try {
            bPlusTree.delete(key);
            System.out.println("La llave " + key + " ha sido eliminada!");
        } catch (Exception e) {
            System.out.println("No se pudo eliminar la llave " + key);
        }
    }

    public static void searchBPlus() throws IOException {
        System.out.print("Digite una llave para buscar: ");
        String key = br.readLine();
        System.out.println(bPlusTree.search(key));
    }
}