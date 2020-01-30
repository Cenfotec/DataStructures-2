package bl;

public class Node {
    private String bookName;
    private int num;
    private String category;
    Node ptr;

    public Node(String bookName, int num, String category) {
        this.bookName = bookName;
        this.num = num;
        this.category = category;
        ptr = null;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Number: %s \n" +
                             "Book Name: %s \n" +
                             "Category: %s \n",
                             num, bookName, category);
    }
}
