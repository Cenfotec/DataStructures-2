package dl;

import bl.Node;
import bl.OrderedList;
import bl.Queue;
import bl.Stack;

public class Logic {
    static OrderedList orderedList;
    static Queue queue;
    static Stack stack;

    public Logic() {
        orderedList = new OrderedList();
        queue = new Queue();
        stack = new Stack();
    }

    public void addToOrderedList(Node node) {
        orderedList.add(node);
    }

    public void addToQueue(Node node) {
        queue.add(node);
    }

    public void addToStack(Node node) {
        stack.add(node);
    }

    public OrderedList getOrderedList() {
        OrderedList orderedList = this.orderedList;
        return orderedList;
    }

    public Queue getQueue() {
        return this.queue;
    }

    public Stack getStack() {
        Stack stack = this.stack;
        return stack;
    }
}
