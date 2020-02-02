package bl;

public class Node {
    private int id;
    private Node left;
    private Node right;

    public Node(int id) {
        this.id = id;
        this.left = null;
        this.right = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "ID: " + this.id;
    }
}

