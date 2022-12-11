import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            Player player = new Player();

            while (true) {
                System.out.println();
                System.out.println("Enter 1: Create a playlist");
                System.out.println("Enter 2: Change Active playlist");
                System.out.println("Enter 3: Add a song");
                System.out.println("Enter 4: Next song");
                System.out.println("Enter 5: Previous song");
                System.out.println("Enter 6: Play");
                System.out.println("Enter 7: Seek Song");
                System.out.println("Enter 8: Toggle Loop Songs");
                System.out.println("Enter 9: List all playlists");
                System.out.println("Enter 10: List all current playlist songs");
                System.out.println("Enter 0: Exit");
                System.out.println();
                int option = scanner.nextInt();
                scanner.nextLine();
                if (option == 0)
                    break;
                switch (option) {
                    case 1:
                        System.out.println("Playlist name: ");
                        String plName = scanner.nextLine();
                        player.addPlayList(plName);
                        break;
                    case 2:
                        System.out.println("Change Playlist to: ");
                        String activePlName = scanner.nextLine();
                        player.setCurrentActivePlayList(activePlName);
                        break;
                    case 3:
                        System.out.println("Song name: ");
                        String songName = scanner.nextLine();
                        player.addSong(songName);
                        break;
                    case 4:
                        player.next();
                        break;
                    case 5:
                        player.previous();
                        break;
                    case 6:
                        player.setPlay(true);
                        break;
                    case 7:
                        System.out.println("Seek count: ");
                        int seekTo = scanner.nextInt();
                        player.seekSong(seekTo);
                        break;
                    case 8:
                        player.setLoopable(!player.canLoop());
                        break;
                    case 9:
                        player.displayAllPlayListName();
                        break;
                    case 10:
                        player.displayAllPlayListSong();
                        break;
                    default:
                        System.out.println("Invalid option.\n");
                        break;
                }
            }
            player = null;
            scanner.close();
        } catch (Exception error) {
            System.out.println("Something went wrong, please check your input, " + error);
        }
    }
}
