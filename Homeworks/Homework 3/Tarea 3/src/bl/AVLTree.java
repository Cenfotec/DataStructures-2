package bl;

public class AVLTree {
    private Node root;

    public AVLTree() { }

    public AVLTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    void updateHeight(Node n) {
        n.setHeight(1 + Math.max(height(n.getLeft()), height(n.getRight())));
    }

    int height(Node n) {
        return n == null ? -1 : n.getHeight();
    }

    int getBalance(Node n) {
        return (n == null) ? 0 : height(n.getRight()) - height(n.getLeft());
    }

    public Node rotateRight(Node y) {
        Node x = y.getLeft();
        Node z = x.getRight();
        x.setRight(y);
        y.setLeft(z);
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    Node rotateLeft(Node y) {
        Node x = y.getRight();
        Node z = x.getLeft();
        x.setLeft(y);
        y.setRight(z);
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    Node rebalance(Node z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.getRight().getRight()) > height(z.getRight().getLeft())) {
                z = rotateLeft(z);
            } else {
                z.setRight(rotateRight(z.getRight()));
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.getLeft().getLeft()) > height(z.getLeft().getRight()))
                z = rotateRight(z);
            else {
                z.setLeft(rotateLeft(z.getLeft()));
                z = rotateRight(z);
            }
        }
        return z;
    }

    public Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        } else if (node.getValue() > value) {
            node.setLeft(insert(node.getLeft(), value));
        } else if (node.getValue() < value) {
            node.setRight(insert(node.getRight(), value));
        } else {
            throw new RuntimeException("This value already exists!");
        }
        return rebalance(node);
    }


    public Node find(int key) {
        Node current = root;
        while (current != null) {
            if (current.getValue() == key) {
                break;
            }
            current = current.getValue() < key ? current.getRight() : current.getLeft();
        }
        return current;
    }

    public void showPreOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getValue() + ", ");
        this.showPreOrder(node.getLeft());
        this.showPreOrder(node.getRight());
    }

    public void showPostOrder(Node node) {
        if (node != null) {
            this.showPostOrder(node.getLeft());
            this.showPostOrder(node.getRight());
            System.out.print(node.getValue() + ", ");
        }
    }

    public void showInOrder(Node node) {
        if (node == null) {
            return;
        }
        this.showInOrder(node.getLeft());
        System.out.print(node.getValue() + ", ");
        this.showInOrder(node.getRight());
    }
}
