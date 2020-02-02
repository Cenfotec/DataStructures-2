package ui;

import bl.BinarySearchTree;
import bl.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        BinarySearchTree bst = new BinarySearchTree();

        String input = "";
        while (true) {
            System.out.println("'exit' to quit.");
            System.out.print("Digite un numero: ");
            input = br.readLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            Node node = new Node(Integer.parseInt(input));
            bst.insertNode(node.getId());
        }

        System.out.print("Preorder: ");
        bst.showPreOrder(bst.getRoot());
        System.out.print("\nPostOrder: ");
        bst.showPostOrder(bst.getRoot());
        System.out.print("\nInOrder: ");
        bst.showInOrder(bst.getRoot());
    }
}
