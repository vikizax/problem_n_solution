import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Set Direction of Sorted Linked List");
            System.out.println("a. Ascending: enter 1");
            System.out.println("b. Descending: enter -1\n");
            int option = scanner.nextInt();
            SortedLinkedList sortedLL = new SortedLinkedList(option);
            while (true) {
                System.out.println("\na. Insert: enter 1");
                System.out.println("b. Delete: enter 2");
                System.out.println("c. Print Sorted Linked List: enter 3");
                System.out.println("d. Exit: enter 0\n");

                int input = scanner.nextInt();

                if (input == 0)
                    break;

                switch (input) {
                    case 1:
                        System.out.println("\nEnter a number: ");
                        int number = scanner.nextInt();
                        sortedLL.inserNode(number);
                        break;
                    case 2:
                        System.out.println("Enter number to delete: \n");
                        int deleteNumber = scanner.nextInt();
                        sortedLL.deleteNode(deleteNumber);
                        break;
                    case 3:
                        sortedLL.print();
                        break;
                    default:
                        System.out.println("Invalid option\n");
                        break;
                }

            }
            sortedLL = null;
            scanner.close();
        } catch (Exception error) {
            System.out.println("Something went wrong, please check your input, " + error);
        }
    }
}