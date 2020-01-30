package ui;

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
        out.println("1. Add to OrderedList");
        out.println("2. Add to Queue");
        out.println("3. Add to Stack");
        out.println("4. Move from ordered list to queue");
        out.println("5. Move from ordered list to stack");
        out.println("6. Move from queue to ordered list");
        out.println("7. Move from queue to stack");
        out.println("8. Move from stack to ordered list");
        out.println("9. Move from stack to queue");
        out.println("10. Show OrderedList");
        out.println("11. Show Queue");
        out.println("12. Show Stack");
        out.println("13. Detener la aplicación");
    }

    public static void detenerAplicacion() {
        out.println("[!] Deteniendo la aplicación...");
        System.exit(0);
    }

    public static boolean seleccionarOpcion(String opcion) throws IOException {
        boolean menu = true;

        if (opcion.equals("1")) {
            addToOrderedList();
        }

        if (opcion.equals("2")) {
            addToQueue();
        }

        if (opcion.equals("3")) {
            addToStack();
        }

        if (opcion.equals("4")) {
            moveFromOrderedListToQueue();
        }

        if (opcion.equals("5")) {
            moveFromOrderedListToStack();
        }

        if (opcion.equals("6")) {
            moveFromQueueToOrderedList();
        }

        if (opcion.equals("7")) {
            moveFromQueueToStack();
        }

        if (opcion.equals("8")) {
            moveFromStackToOrderedList();
        }

        if (opcion.equals("9")) {
            moveFromStackToQueue();
        }

        if (opcion.equals("10")) {
            showOrderedList();
        }

        if (opcion.equals("11")) {
            showQueue();
        }

        if (opcion.equals("12")) {
            showStack();
        }

        if (opcion.equals("13")) {
            detenerAplicacion();
        }

        return menu;
    }

    public static void addToOrderedList() throws IOException {
        String bookName, category;
        int num;

        out.print("Book name: ");
        bookName = br.readLine();

        out.print("Number: ");
        num = Integer.parseInt(br.readLine());

        out.print("Category: ");
        category = br.readLine();

        controller.addToOrderedList(bookName, num, category);
    }

    public static void addToQueue() throws IOException {
        String bookName, category;
        int num;

        out.print("Book name: ");
        bookName = br.readLine();

        out.print("Number: ");
        num = Integer.parseInt(br.readLine());

        out.print("Category: ");
        category = br.readLine();

        controller.addToQueue(bookName, num, category);
    }

    public static void addToStack() throws IOException {
        String bookName, category;
        int num;

        out.print("Book name: ");
        bookName = br.readLine();

        out.print("Number: ");
        num = Integer.parseInt(br.readLine());

        out.print("Category: ");
        category = br.readLine();

        controller.addToStack(bookName, num, category);
    }

    public static void moveFromOrderedListToQueue() throws IOException {
        if (controller.isOrderedListEmpty()) {
            out.println("Ordered list is empty!");
        } else {
            String bookName;
            out.print("Book name: ");
            bookName = br.readLine();
            controller.moveFromOrderedListToQueue(bookName);
        }
    }

    public static void moveFromOrderedListToStack() throws IOException {
        if (controller.isOrderedListEmpty()) {
            out.println("Ordered list is empty");
        } else {
            String bookName;
            out.print("Book name: ");
            bookName = br.readLine();
            controller.moveFromOrderedListToStack(bookName);
        }

    }

    public static void moveFromQueueToOrderedList() {
        if (controller.isQueueEmpty()) {
            out.println("bl.Queue is empty!");
        } else {
            controller.moveFromQueueToOrderedList();
        }

    }

    public static void moveFromQueueToStack() {
        if (controller.isQueueEmpty()) {
            out.println("bl.Queue is empty!");
        } else {
            controller.moveFromQueueToStack();
        }
    }

    public static void moveFromStackToOrderedList() {
        if (controller.isStackEmpty()) {
            out.println("bl.Stack is empty!");
        } else {
            controller.moveFromStackToOrderedList();
        }
    }

    public static void moveFromStackToQueue() {
        if (controller.isStackEmpty()) {
            out.println("bl.Stack is empty!");
        } else {
            controller.moveFromStackToQueue();
        }
    }

    public static void showOrderedList() {
        out.println(controller.showOrderedList());
    }

    public static void showQueue() {
        out.println(controller.showQueue());
    }

    public static void showStack() {
        out.println(controller.showStack());
    }
}