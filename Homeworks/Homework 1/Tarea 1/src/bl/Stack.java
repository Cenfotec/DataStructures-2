package bl;

public class Stack {
    private Node top;

    public Stack() {
        this.top = null;
    }

    public boolean isEmpty() {
        return this.top == null;
    }

    public void add(Node node) {
        if (isEmpty()) {
            this.top = node;
        } else {
            Node aux = this.top;
            this.top = node;
            this.top.ptr = aux;
        }
    }

    public Node pop() {
        Node aux = this.top;
        this.top = aux.ptr;
        aux.ptr = null;
        return aux;
    }

    public void moveToOrderedList(OrderedList orderedList) {
        orderedList.add(this.pop());
    }

    public void moveToQueue(Queue queue) {
        queue.add(this.pop());
    }

    @Override
    public String toString() {
        String res = "";
        if (isEmpty()) {
            return "bl.Stack is empty!";
        }

        Node aux = this.top;
        while (aux != null) {
            res += aux.toString() + "\n";
            aux = aux.ptr;
        }
        return res;
    }
}
