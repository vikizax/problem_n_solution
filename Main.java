import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Sorted Linked List options:");
            int option = scanner.nextInt();
            SortedLinkedList sortedLL = new SortedLinkedList();
            System.out.println(option);
            System.out.println(sortedLL.getSortDirection());
            scanner.close();
        } catch (Exception error) {
            System.out.println("Something went wrong, please check your input");
        }
    }
}