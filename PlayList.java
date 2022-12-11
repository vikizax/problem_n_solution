public class PlayList {
    private SongNode head;
    private String name;
    private int length;

    public PlayList(String name) {
        this.head = null;
        this.name = name;
        length = 0;
    }

    public String getPlayListName() {
        return this.name;
    }

    public void insertSong(String name) {
        if (name == null)
            return;
        SongNode currentNode = this.head;
        SongNode newSong = new SongNode(name);
        if (currentNode == null) {
            this.head = newSong;
            this.head.setNext(newSong);
            this.head.setPrevious(newSong);
            length += 1;
            return;
        }

        SongNode lastNode = currentNode.getPrevious();
        lastNode.setNext(newSong);
        newSong.setPrevious(lastNode);
        newSong.setNext(currentNode);
        currentNode.setPrevious(newSong);

        length += 1;
    }

    public void displayPlayListSongs() {
        SongNode songNode = this.head;
        System.out.println();
        if (songNode == null) {
            System.out.println("No songs available.");
            System.out.println();
            return;
        }
        int iter = 1;
        do {
            System.out.println("#" + iter + ": " + songNode.getSongName());
            songNode = songNode.getNext();
            iter += 1;
        } while (songNode != this.head);

    }

    public SongNode getHead() {
        return this.head;
    }

    public int getPlayListLength() {
        return length;
    }
}
