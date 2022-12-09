import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
           
            scanner.close();
        } catch (Exception error) {
            System.out.println("Something went wrong, please check your input, " + error);
        }
    }
}