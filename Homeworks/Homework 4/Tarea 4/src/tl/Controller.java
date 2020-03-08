package tl;

import bl.AVLTree;
import bl.AVLTreeNode;
import dl.Logic;

public class Controller {
    static Logic logic;

    public Controller() {
        logic = new Logic();
    }


    public void createAVLTree(String input) {
        AVLTree avlTree = logic.getAvlTree();
        AVLTreeNode node = new AVLTreeNode(Integer.parseInt(input));
        avlTree.setRoot(avlTree.insert(avlTree.getRoot(), node.getValue()));
    }

    public AVLTree getAvlTree() {
        return logic.getAvlTree();
    }

    public void bPlusTreeInsert(String key, String value) {
        logic.getbPlusTree().insert(key, value);
    }

    public void bPlusTreeDelete(String key) {
        logic.getbPlusTree().delete(key);
    }

    public String bPlusTreeSearch(String key) {
        return logic.getbPlusTree().search(key).toString();
    }
}
