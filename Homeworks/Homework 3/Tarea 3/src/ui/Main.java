package ui;

import bl.AVLTree;
import bl.Node;

public class Main {
    public static void main(String[] args) {
        // 7, 14, 28, 5, 9, 8, 21, 3, 15, 24, 100 y 1
        Node root = new Node(7);
        AVLTree avlTree = new AVLTree(root);

        avlTree.insert(avlTree.getRoot(), 14);
        avlTree.insert(avlTree.getRoot(), 28);
        avlTree.insert(avlTree.getRoot(), 5);
        avlTree.insert(avlTree.getRoot(), 9);
        avlTree.insert(avlTree.getRoot(), 8);
        avlTree.insert(avlTree.getRoot(), 21);
        avlTree.insert(avlTree.getRoot(), 3);
        avlTree.insert(avlTree.getRoot(), 15);
        avlTree.insert(avlTree.getRoot(), 24);
        avlTree.insert(avlTree.getRoot(), 100);
        avlTree.insert(avlTree.getRoot(), 1);





        System.out.print("Preorder: ");
        avlTree.showPreOrder(avlTree.getRoot());
        System.out.print("\nPostOrder: ");
        avlTree.showPostOrder(avlTree.getRoot());
        System.out.print("\nInOrder: ");
        avlTree.showInOrder(avlTree.getRoot());
    }
}
