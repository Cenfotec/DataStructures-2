package bl;

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void insertNode(int key) {
        root = insertNode(this.root, new Node(key));
    }

    private Node insertNode(Node currentParent, Node newNode) {
        if (currentParent == null) {
            return newNode;
        } else if (newNode.getId() > currentParent.getId()) {
            currentParent.setRight(insertNode(currentParent.getRight(), newNode));
        } else if (newNode.getId() < currentParent.getId()) {
            currentParent.setLeft(insertNode(currentParent.getLeft(), newNode));
        }

        return currentParent;
    }

    public void showPreOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getId() + ", ");
        this.showPreOrder(node.getLeft());
        this.showPreOrder(node.getRight());
    }

    public void showPostOrder(Node node) {
        if (node != null) {
            this.showPostOrder(node.getLeft());
            this.showPostOrder(node.getRight());
            System.out.print(node.getId() + ", ");
        }
    }

    public void showInOrder(Node node) {
        if (node == null) {
            return;
        }
        this.showInOrder(node.getLeft());
        System.out.print(node.getId() + ", ");
        this.showInOrder(node.getRight());
    }
}
