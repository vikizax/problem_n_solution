public class SortedLinkedList {
    private Node head;

    enum SortDirection {
        ASCENDING,
        DESCENDING
    }

    private SortDirection currentDirection;

    public SortedLinkedList(int sortDirection) {
        this.currentDirection = sortDirection == 1 ? SortDirection.ASCENDING
                : sortDirection == -1 ? SortDirection.DESCENDING : SortDirection.ASCENDING;
    }

    public SortedLinkedList() {
        this.currentDirection = SortDirection.ASCENDING;
    }

    public SortDirection getSortDirection() {
        return this.currentDirection;
    }

    private void ascendingInsert(int value) {
        Node newNode = new Node(value);
        Node prevNode = null;
        Node currentNode = this.head;

        if (currentNode == null) {
            this.head = newNode;
            return;
        }

        int currentValue = currentNode.getValue();

        while (currentNode != null && value > currentValue) {
            prevNode = currentNode;
            currentNode = currentNode.getNext();
            if (currentNode != null)
                currentValue = currentNode.getValue();
        }

        if (value == currentValue) {
            currentNode.increaseCount();
            return;
        }

        if (prevNode == null) {
            this.head = newNode;
            newNode.setNext(currentNode);
        } else {
            newNode.setNext(prevNode.getNext());
            prevNode.setNext(newNode);
        }
    }

    private void descendingInsert(int value) {
        Node newNode = new Node(value);
        Node prevNode = null;
        Node currentNode = this.head;

        if (currentNode == null) {
            this.head = newNode;
            return;
        }

        int currentValue = currentNode.getValue();

        while (currentNode != null && value < currentValue) {
            prevNode = currentNode;
            currentNode = currentNode.getNext();
            if (currentNode != null)
                currentValue = currentNode.getValue();
        }

        if (value == currentValue) {
            currentNode.increaseCount();
            return;
        }

        if (prevNode == null) {
            this.head = newNode;
            newNode.setNext(currentNode);
        } else {
            newNode.setNext(prevNode.getNext());
            prevNode.setNext(newNode);
        }
    }

    public void inserNode(int value) {
        if (currentDirection == SortDirection.ASCENDING) {
            ascendingInsert(value);
        } else {
            descendingInsert(value);
        }
    }

    private void deleteOperation(Node node, Node prevNode) {
        node.reduceCount();
        if (node.getCount() == 0) {

            if (prevNode == null)
                this.head = node.getNext();
            else
                prevNode.setNext(node.getNext());
        }
    }

    public void deleteNode(int value) {
        Node prevNode = null;
        Node currentNode = this.head;

        if (currentNode == null)
            return;

        int currentValue = currentNode.getValue();

        while (currentNode != null && value != currentValue) {
            prevNode = currentNode;
            currentNode = currentNode.getNext();
            if (currentNode != null)
                currentValue = currentNode.getValue();
        }

        if (currentNode == null)
            return;

        deleteOperation(currentNode, prevNode);

    }

    public void print() {
        Node currentNode = this.head;
        System.out.println("-------result start-------");
        while (currentNode != null) {
            System.out.println(currentNode.getValue());
            currentNode = currentNode.getNext();
        }
        System.out.println("-------result end---------");
    }
}
