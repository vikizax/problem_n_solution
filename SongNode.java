public class SongNode {
    private SongNode next;
    private SongNode prev;
    private String name;

    public SongNode(String name) {
        this.next = null;
        this.prev = null;
        this.name = name;
    }

    public SongNode getNext() {
        return this.next;
    }

    public SongNode getPrevious() {
        return this.prev;
    }

    public void setNext(SongNode node) {
        this.next = node;
    }

    public void setPrevious(SongNode node) {
        this.prev = node;
    }

    public String getSongName() {
        return this.name;
    }

}
