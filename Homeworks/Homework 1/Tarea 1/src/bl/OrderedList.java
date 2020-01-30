package bl;

public class OrderedList {
    private Node head;

    public OrderedList() {
        this.head = null;
    }

    public Node getHead() {
        return head;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void addHead(Node node1, Node node2, Node node3) {
        if (node1 == this.head) {
            this.head = node2;
        } else {
            node3.ptr = node2;
        }
    }

    public void add(Node node) {
        Node aux = this.head;
        Node ant = null;
        while (aux != null && aux.getBookName().charAt(0) < node.getBookName().charAt(0)) {
            ant = aux;
            aux = aux.ptr;
        }
        addHead(aux, node, ant);
        node.ptr = aux;
    } // Arbol, Andar, Bicho, Calamardo [Binario]

    public Node remove(String bookName) {
        Node node = null;
        if (this.head.getBookName().equals(bookName)) {
            if (!isEmpty()) {
                node = this.head;
                this.head = this.head.ptr;
                return node;
            } else {
                this.head = null;
                return node;
            }
        }

        Node aux = this.head;
        while (aux.ptr != null) {
            if (aux.ptr.getBookName().equals(bookName)) {
                node = aux.ptr;
                aux.ptr = aux.ptr.ptr;
                node.ptr = null;
                return node;
            }
        }
        node.ptr = null;
        return node;
    }

    public void moveToQueue(Queue queue, String bookName) {
        queue.add(this.remove(bookName));
    }

    public void moveToStack(Stack stack, String bookName) {
        stack.add(this.remove(bookName));
    }

    @Override
    public String toString() {
        String res = "";
        if (isEmpty()) {
            return "Ordered list is empty!";
        }

        Node aux = this.head;
        while (aux != null) {
            res += aux.toString() + "\n";
            aux = aux.ptr;
        }
        return res;
    }
}
