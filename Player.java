import java.util.Map;
import java.util.TreeMap;

public class Player {
    private PlayList activePlayList;
    private TreeMap<String, PlayList> playListMap;
    private boolean canLoop;
    private SongNode activeSong;
    private boolean isPlaying;

    public Player() {
        canLoop = false;
        activePlayList = null;
        playListMap = new TreeMap<>();
        activeSong = null;
        isPlaying = false;
    }

    public void addPlayList(String name) {
        if (name == null)
            return;
        if (playListMap.containsKey(name)) {
            System.out.println("Play list already exists. Enter unique name.");
            return;
        }
        PlayList playList = new PlayList(name);
        if (activePlayList == null) {
            activePlayList = playList;
        }
        playListMap.put(name, playList);
        playList = null;
    }

    public void addSong(String name) {
        if (name == null)
            return;
        if (activePlayList == null) {
            System.out.println("No playlist avaiable. Create a playlist.");
            return;
        }

        activePlayList.insertSong(name);

        if (activeSong == null && activePlayList != null) {
            activeSong = activePlayList.getHead();
        }
    }

    public void displayAllPlayListSong() {
        if (activePlayList == null) {
            System.out.println("No current active playlist.");
            return;
        }
        activePlayList.displayPlayListSongs();
    }

    public void displayAllPlayListName() {
        if (playListMap.size() == 0) {
            System.out.println("No playlist available.");
            return;
        }
        System.out.println();
        for (Map.Entry<String, PlayList> map : playListMap.entrySet()) {
            System.out.println(map.getKey());
        }
        System.out.println();
    }

    public void displayCurrentActivePlayList() {
        System.out.println();
        System.out.println(this.activePlayList.getPlayListName());
        System.out.println();
    }

    public void displayCanLoop() {
        System.out.println();
        System.out.println("Can Loop: " + canLoop);
        System.out.println();
    }

    public void setCurrentActivePlayList(String name) {
        if (name == null)
            return;
        if (playListMap.containsKey(name) == false) {
            System.out.println("Play list does not exist.");
            return;
        }
        activePlayList = playListMap.get(name);
        activeSong = activePlayList.getHead();
        canLoop = false;
    }

    public void setLoopable(boolean canLoop) {
        if (activePlayList == null) {
            System.out.println();
            System.out.println("No playlist available.");
            System.out.println();
            return;
        }

        if (activePlayList.getPlayListLength() == 0) {
            System.out.println();
            System.out.println("No song avaiable.");
            System.out.println();
            return;
        }
        this.canLoop = canLoop;
    }

    public boolean canLoop() {
        return this.canLoop;
    }

    public void setPlay(boolean play) {
        if (activePlayList == null) {
            System.out.println();
            System.out.println("No playlist available.");
            System.out.println();
            return;
        }

        if (activePlayList.getPlayListLength() == 0) {
            System.out.println();
            System.out.println("No song avaiable.");
            System.out.println();
            return;
        }

        isPlaying = play;
        if (isPlaying) {
            System.out.println();
            System.out.println("Playing: " + activeSong.getSongName());
            System.out.println();
        } else {
            System.out.println();
            System.out.println("Paused.");
            System.out.println();
        }
    }

    public void seekSong(int seek) {
        if (activePlayList == null) {
            System.out.println();
            System.out.println("No playlist available.");
            System.out.println();
            return;
        }

        int length = activePlayList.getPlayListLength();

        if (length == 0) {
            System.out.println();
            System.out.println("No song avaiable.");
            System.out.println();
            return;
        }

        int seekTo = seek % length;

        SongNode head = activePlayList.getHead();

        if (seek >= length && canLoop == false) {
            System.out.println();
            System.out.println("No more songs to play");
            System.out.println();
            activeSong = head;
            return;
        }

        while (seekTo != 0) {
            if (activeSong.getNext() == head && canLoop == false) {
                System.out.println();
                System.out.println("No more songs to play");
                System.out.println();
                activeSong = head;
                return;
            }

            activeSong = activeSong.getNext();
            seekTo -= 1;
        }

        System.out.println();
        System.out.println("Seek Complete");
        System.out.println("Current Song: " + activeSong.getSongName());
        System.out.println();
    }

    public void next() {
        if (activePlayList == null) {
            System.out.println();
            System.out.println("No playlist available.");
            System.out.println();
            return;
        }

        if (activePlayList.getPlayListLength() == 0) {
            System.out.println();
            System.out.println("No song avaiable.");
            System.out.println();
            return;
        }

        if (activeSong.getNext() == activePlayList.getHead() && canLoop == false) {
            System.out.println();
            System.out.println("No more songs to play");
            System.out.println();
            activeSong = activePlayList.getHead();
            return;
        }

        activeSong = activeSong.getNext();
        System.out.println();
        System.out.println("Current Song: " + activeSong.getSongName());
        System.out.println();
    }

    public void previous() {
        if (activePlayList == null) {
            System.out.println();
            System.out.println("No playlist available.");
            System.out.println();
            return;
        }

        if (activePlayList.getPlayListLength() == 0) {
            System.out.println();
            System.out.println("No song avaiable.");
            System.out.println();
            return;
        }

        if (activeSong == activePlayList.getHead() && canLoop == false) {
            System.out.println();
            System.out.println("No more songs to play");
            System.out.println();
            activeSong = activePlayList.getHead();
            return;
        }

        activeSong = activeSong.getPrevious();
        System.out.println();
        System.out.println("Current Song: " + activeSong.getSongName());
        System.out.println();
    }

}
