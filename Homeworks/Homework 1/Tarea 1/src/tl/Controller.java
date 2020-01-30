package tl;

import bl.Node;
import bl.OrderedList;
import bl.Queue;
import bl.Stack;
import dl.Logic;

public class Controller {
    static Logic logic;
    public Controller() {
        logic = new Logic();
    }

    public void addToOrderedList(String bookName, int num, String category) {
        Node node = new Node(bookName, num, category);
        logic.addToOrderedList(node);
    }

    public void addToQueue(String bookName, int num, String category) {
        Node node = new Node(bookName, num, category);
        logic.addToQueue(node);
    }

    public void addToStack(String bookName, int num, String category) {
        Node node = new Node(bookName, num, category);
        logic.addToStack(node);
    }

    public void moveFromOrderedListToQueue(String bookName) {
        logic.getOrderedList().moveToQueue(logic.getQueue(), bookName);
    }

    public void moveFromOrderedListToStack(String bookName) {
        logic.getOrderedList().moveToStack(logic.getStack(), bookName);
    }

    public void moveFromQueueToOrderedList() {
        logic.getQueue().moveToOrderedList(logic.getOrderedList());
    }

    public void moveFromQueueToStack() {
        logic.getQueue().moveToStack(logic.getStack());
    }

    public void moveFromStackToOrderedList() {
        logic.getStack().moveToOrderedList(logic.getOrderedList());
    }

    public void moveFromStackToQueue() {
        logic.getStack().moveToQueue(logic.getQueue());
    }

    public boolean isOrderedListEmpty() {
        return logic.getOrderedList().isEmpty();
    }

    public boolean isQueueEmpty() {
        return logic.getQueue().isEmpty();
    }

    public boolean isStackEmpty() {
        return logic.getStack().isEmpty();
    }

    public OrderedList getOrderedList() {
        return logic.getOrderedList();
    }

    public Queue getQueue() {
        return logic.getQueue();
    }

    public Stack getStack() {
        return logic.getStack();
    }

    public String showOrderedList() {
        return getOrderedList().toString();
    }

    public String showQueue() {
        return getQueue().toString();
    }

    public String showStack() {
        return getStack().toString();
    }
}
