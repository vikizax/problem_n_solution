public class SortedLinkedList {
    private SortedLinkedList headNode;
    private Node head;

    enum SortDirection {
        ASCENDING,
        DESCENDING
    }

    private SortDirection currentDirection;

    public SortedLinkedList(int sortDirection) {
        this.currentDirection = SortDirection.values()[sortDirection];
    }

    public SortedLinkedList() {
        this.currentDirection = SortDirection.ASCENDING;
    }

    public SortDirection getSortDirection() {
        return this.currentDirection;
    }

    public void inserNode(int value) {
        Node node = new Node(value);

        if (head.getNext() == null) {
            head.setNext(node);
        } else {

            Node prevNode = null;
            Node currentNode = this.head;
            
        }
    }
}
