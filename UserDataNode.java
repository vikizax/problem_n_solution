public class UserDataNode {
    private UserData userData;
    private UserDataNode next;

    public UserDataNode(UserData userData) {
        this.userData = userData;
        this.next = null;
    }

    public UserDataNode getNext() {
        return this.next;
    }

    public void setNext(UserDataNode node) {
        this.next = node;
    }

    public UserData getValue() {
        return this.userData;
    }
}
