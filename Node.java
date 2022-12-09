public class Node {
    private int value;
    private int count;
    private Node next;

    public Node(int value, int count) {
        this.value = value;
        this.count = count;
        this.next = null;
    }

    public Node(int value) {
        this.value = value;
        this.count = 1;
        this.next = null;
    }

    public int getValue() {
        return this.value;
    }

    public int getCount() {
        return this.count;
    }

    public int increaseCount() {
        this.count += 1;
        return this.count;
    }

    public int reduceCount() {
        this.count -= 1;
        return this.count;
    }

    public int reduceCount(int decreaseBy) {
        if (decreaseBy > this.count)
            return 0;
        this.count -= decreaseBy;
        return this.count;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node node) {
        this.next = node;
    }

}
