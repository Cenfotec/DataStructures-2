package bl;

public class Queue {
    private Node first;

    public Queue() {
        this.first = null;
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public void add(Node node) {
        if (isEmpty()) {
            this.first = node;
        } else {
            Node aux = first;
            while (first.ptr != null) {
                aux = aux.ptr;
            }
            aux.ptr = node;
        }
    }

    public Node remove() {
        if (!isEmpty()) {
            Node aux = this.first;
            this.first = aux.ptr;
            aux.ptr = null;
            return aux;
        }
        return null;
    }

    public void moveToOrderedList(OrderedList orderedList) {
        orderedList.add(this.remove());
    }

    public void moveToStack(Stack stack) {
        stack.add(this.remove());
    }

    @Override
    public String toString() {
        String res = "";
        if (isEmpty()) {
            return "bl.Queue is empty!";
        }

        Node aux = this.first;
        while (aux != null) {
            res += aux.toString() + "\n";
            aux = aux.ptr;
        }
        return res;
    }
}
