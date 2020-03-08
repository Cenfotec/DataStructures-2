package dl;

import bl.AVLTree;
import bl.BPlusTree;

public class Logic {
    static AVLTree avlTree;
    static BPlusTree bPlusTree;

    public Logic() {
        avlTree = new AVLTree();
        bPlusTree = new BPlusTree();
    }

    public AVLTree getAvlTree() {
        AVLTree avlTree = this.avlTree;
        return avlTree;
    }

    public BPlusTree getbPlusTree() {
        BPlusTree bPlusTree = this.bPlusTree;
        return bPlusTree;
    }
}
