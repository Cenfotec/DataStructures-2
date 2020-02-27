package bl;

public class AVLTree {
    private AVLTreeNode root;

    public AVLTree() { }

    public AVLTree(AVLTreeNode root) {
        this.root = root;
    }

    public AVLTreeNode getRoot() {
        return root;
    }

    public void setRoot(AVLTreeNode root) {
        this.root = root;
    }

    AVLTreeNode updateHeight(AVLTreeNode n) {
        n.setHeight(1 + Math.max(height(n.getLeft()), height(n.getRight())));
        return n;
    }

    int height(AVLTreeNode n) {
        return n == null ? -1 : n.getHeight();
    }

    int getBalance(AVLTreeNode n) {
        return (n == null) ? 0 : height(n.getRight()) - height(n.getLeft());
    }

    public AVLTreeNode rotateRight(AVLTreeNode y) {
        AVLTreeNode x = y.getLeft();
        AVLTreeNode z = x.getRight();
        x.setRight(y);
        y.setLeft(z);
        updateHeight(y);
        x = updateHeight(x);
        return x;
    }

    AVLTreeNode rotateLeft(AVLTreeNode y) {
        AVLTreeNode x = y.getRight();
        AVLTreeNode z = x.getLeft();
        x.setLeft(y);
        y.setRight(z);
        updateHeight(y);
        x = updateHeight(x);
        return x;
    }

    AVLTreeNode rebalance(AVLTreeNode z) {
        z = updateHeight(z);
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

    public AVLTreeNode insert(AVLTreeNode node, int value) {
        if (node == null) {
            return new AVLTreeNode(value);
        } else if (node.getValue() > value) {
            node.setLeft(insert(node.getLeft(), value));
        } else if (node.getValue() < value) {
            node.setRight(insert(node.getRight(), value));
        } else {
            throw new RuntimeException("This value already exists!");
        }
        return rebalance(node);
    }


    public AVLTreeNode find(int key) {
        AVLTreeNode current = root;
        while (current != null) {
            if (current.getValue() == key) {
                break;
            }
            current = current.getValue() < key ? current.getRight() : current.getLeft();
        }
        return current;
    }

    public void showPreOrder(AVLTreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getValue() + ", ");
        this.showPreOrder(node.getLeft());
        this.showPreOrder(node.getRight());
    }

    public void showPostOrder(AVLTreeNode node) {
        if (node != null) {
            this.showPostOrder(node.getLeft());
            this.showPostOrder(node.getRight());
            System.out.print(node.getValue() + ", ");
        }
    }

    public void showInOrder(AVLTreeNode node) {
        if (node == null) {
            return;
        }
        this.showInOrder(node.getLeft());
        System.out.print(node.getValue() + ", ");
        this.showInOrder(node.getRight());
    }
}