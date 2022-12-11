public class UserDataLinkedList {
    private UserDataNode head;

    public static enum AggrigationOps {
        MIN,
        MAX,
        AVG,
        TOTAL
    };

    public UserDataLinkedList() {
        seedData();
    }

    private int getMax(UserData.NumericFields key) {
        int maxValue = Integer.MIN_VALUE;
        UserDataNode currentNode = this.head;
        while (currentNode != null) {
            int currentValue = currentNode.getValue().getNumericFieldValue(key);
            maxValue = Integer.max(maxValue, currentValue);
            currentNode = currentNode.getNext();
        }
        if (maxValue == Integer.MIN_VALUE)
            return 0;
        return maxValue;
    }

    private int getMin(UserData.NumericFields key) {
        int minValue = Integer.MAX_VALUE;
        UserDataNode currentNode = this.head;
        while (currentNode != null) {
            int currentValue = currentNode.getValue().getNumericFieldValue(key);
            minValue = Integer.min(minValue, currentValue);
            currentNode = currentNode.getNext();

        }
        if (minValue == Integer.MAX_VALUE)
            return 0;
        return minValue;
    }

    private double getTotal(UserData.NumericFields key) {
        double total = 0;
        UserDataNode currentNode = this.head;
        while (currentNode != null) {
            int currentValue = currentNode.getValue().getNumericFieldValue(key);
            total += currentValue;
            currentNode = currentNode.getNext();
        }
        return total;
    }

    private double getAvg(UserData.NumericFields key) {
        double total = 0;
        int count = 0;
        UserDataNode currentNode = this.head;
        while (currentNode != null) {
            int currentValue = currentNode.getValue().getNumericFieldValue(key);
            total += currentValue;
            currentNode = currentNode.getNext();
            count += 1;
        }
        if (count == 0)
            return 0;
        return Math.ceil(total / count);
    }

    public double aggregation(AggrigationOps operation, UserData.NumericFields key) {
        if (key == null)
            return 0;
        switch (operation) {
            case MAX:
                return getMax(key);
            case MIN:
                return getMin(key);
            case TOTAL:
                return getTotal(key);
            case AVG:
                return getAvg(key);
            default:
                return 0;
        }
    }

    private void seedData() {
        for (int i = 0; i < 10000; i++) {
            RandomUserData randomUserData = new RandomUserData();
            UserDataNode newNode = new UserDataNode(randomUserData.getUserData());
            newNode.setNext(this.head);
            this.head = newNode;
            newNode = null;
            randomUserData = null;
        }
    }

    private void printDataHeader() {
        System.out.format("\n%22s %15s %22s %22s %8s %18s %12s\n", "Name", "Religion", "City", "Mother Tongue", "Age",
                "Annual Income", "No. of Vehical");
    }

    public void displayAllData() {
        printDataHeader();
        UserDataNode currentNode = this.head;
        while (currentNode != null) {
            UserData user = currentNode.getValue();
            System.out.format(
                    "%22s %15s %22s %22s %8d %18d %8d\n",
                    user.getStringFieldValue(UserData.StringFields.NAME),
                    user.getStringFieldValue(UserData.StringFields.RELIGION),
                    user.getStringFieldValue(UserData.StringFields.CITY),
                    user.getStringFieldValue(UserData.StringFields.MOTHER_TONGUE),
                    user.getNumericFieldValue(UserData.NumericFields.AGE),
                    user.getNumericFieldValue(UserData.NumericFields.ANNUAL_INCOME),
                    user.getNumericFieldValue(UserData.NumericFields.NO_OF_VEHICLE));
            currentNode = currentNode.getNext();
        }
    }

    private UserDataNode linkedListMiddle(UserDataNode head) {
        if (head == null)
            return head;
        UserDataNode fastNode = head;
        UserDataNode slowNode = head;

        while (fastNode.getNext() != null && fastNode.getNext().getNext() != null) {
            slowNode = fastNode.getNext();
            fastNode = fastNode.getNext().getNext();
        }
        return slowNode;
    }

    private UserDataNode merge(UserDataNode left, UserDataNode right, UserData.NumericFields key) {
        if (left == null)
            return right;
        if (right == null)
            return left;

        UserDataNode sortedHead = null;

        if (left.getValue().getNumericFieldValue(key) >= right.getValue()
                .getNumericFieldValue(key)) {
            sortedHead = left;
            sortedHead.setNext(merge(left.getNext(), right, key));
        } else {
            sortedHead = right;
            sortedHead.setNext(merge(right.getNext(), left, key));
        }

        return sortedHead;
    }

    private UserDataNode divide(UserDataNode head, UserData.NumericFields key) {
        if (head == null || head.getNext() == null || key == null) {
            return head;
        }

        UserDataNode middleNode = linkedListMiddle(head);
        UserDataNode leftSideNodeHead = head;
        UserDataNode rightSideNodeHead = middleNode.getNext();
        middleNode.setNext(null);
        UserDataNode leftNodeLL = divide(leftSideNodeHead, key);
        UserDataNode rightNodeLL = divide(rightSideNodeHead, key);
        UserDataNode newHead = merge(leftNodeLL, rightNodeLL, key);
        return newHead;
    }

    private void sort(UserData.NumericFields key) {
        this.head = divide(this.head, key);
    }

    public void topK(UserData.NumericFields key, int k) {
        sort(key);
        printDataHeader();
        UserDataNode currentNode = this.head;
        while (currentNode != null && k != 0) {
            UserData user = currentNode.getValue();
            System.out.format(
                    "%22s %15s %22s %22s %8d %18d %12d\n",
                    user.getStringFieldValue(UserData.StringFields.NAME),
                    user.getStringFieldValue(UserData.StringFields.RELIGION),
                    user.getStringFieldValue(UserData.StringFields.CITY),
                    user.getStringFieldValue(UserData.StringFields.MOTHER_TONGUE),
                    user.getNumericFieldValue(UserData.NumericFields.AGE),
                    user.getNumericFieldValue(UserData.NumericFields.ANNUAL_INCOME),
                    user.getNumericFieldValue(UserData.NumericFields.NO_OF_VEHICLE));
            currentNode = currentNode.getNext();
            k -= 1;
        }
    }

    public void topKGroup(UserData.NumericFields sortKey, UserData.StringFields groupKey, String groupValue, int k) {

        System.out.println(sortKey + " " + groupKey + " " + groupValue + " " + k);
        sort(sortKey);
        printDataHeader();
        if (sortKey == null || groupKey == null || groupValue == null || k == 0)
            return;
        UserDataNode currentNode = this.head;

        while (currentNode != null && k != 0) {
            UserData user = currentNode.getValue();
            String currentGroupValue = currentNode.getValue().getStringFieldValue(groupKey);
            
            if (groupValue.equals(currentGroupValue)) {

                System.out.format(
                        "%22s %15s %22s %22s %8d %18d %12d\n",
                        groupKey == UserData.StringFields.NAME ? groupValue
                                : user.getStringFieldValue(UserData.StringFields.NAME),
                        groupKey == UserData.StringFields.RELIGION ? groupValue
                                : user.getStringFieldValue(UserData.StringFields.RELIGION),
                        groupKey == UserData.StringFields.CITY ? groupValue
                                : user.getStringFieldValue(UserData.StringFields.CITY),
                        groupKey == UserData.StringFields.MOTHER_TONGUE ? groupValue
                                : user.getStringFieldValue(UserData.StringFields.MOTHER_TONGUE),
                        user.getNumericFieldValue(UserData.NumericFields.AGE),
                        user.getNumericFieldValue(UserData.NumericFields.ANNUAL_INCOME),
                        user.getNumericFieldValue(UserData.NumericFields.NO_OF_VEHICLE));
                k -= 1;
            }
            currentNode = currentNode.getNext();

        }
        System.out.println();
    }

}
