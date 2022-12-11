import java.util.Scanner;

public class Main {

    private static void aggregationCase(UserDataLinkedList.AggrigationOps operation, Scanner sc,
            UserDataLinkedList udLL) {
        System.out.println("Select Field:");
        System.out.println("Enter 1: Age");
        System.out.println("Enter 2: No. of Vehicle");
        System.out.println("Enter 3: Annual Income");
        System.out.println("Enter 0: Exit");
        System.out.println();
        int option = sc.nextInt();

        switch (option) {
            case 0:
                return;
            case 1:
                System.out.println("Result: " + udLL.aggregation(operation, UserData.NumericFields.AGE));
                break;
            case 2:
                System.out.println("Result: " + udLL.aggregation(operation, UserData.NumericFields.NO_OF_VEHICLE));
                break;
            case 3:
                System.out.println("Result: " + udLL.aggregation(operation, UserData.NumericFields.ANNUAL_INCOME));
                break;
            default:
                System.out.println("Invalid input.\n");
                break;
        }
    }

    private static void topKCase(UserData.NumericFields operation, Scanner sc,
            UserDataLinkedList udLL) {
        System.out.println("K value: ");
        System.out.println();
        int k = sc.nextInt();
        udLL.topK(operation, k);
    }

    private static void topKGroupCase(UserData.NumericFields operation, Scanner sc,
            UserDataLinkedList udLL) {
        System.out.println("Select Field:");
        System.out.println("Enter 1: Name");
        System.out.println("Enter 2: Religion");
        System.out.println("Enter 3: City");
        System.out.println("Enter 4: Mother Tongue");
        System.out.println("Enter 0: Exit");
        System.out.println();
        int option = sc.nextInt();
        System.out.println();
        System.out.println("Field Value: ");
        System.out.println();
        sc.nextLine();
        String value = sc.nextLine(); 
        System.out.println();
        System.out.println("K Value: ");
        System.out.println();
        int k = sc.nextInt();
        System.out.println();
        switch (option) {
            case 0:
                return;
            case 1:
                udLL.topKGroup(operation, UserData.StringFields.NAME, value, k);
                break;
            case 2:
                udLL.topKGroup(operation, UserData.StringFields.RELIGION, value, k);
                break;
            case 3:
                udLL.topKGroup(operation, UserData.StringFields.CITY, value, k);
                break;
            case 4:
                udLL.topKGroup(operation, UserData.StringFields.MOTHER_TONGUE, value, k);
                break;
            default:
                System.out.println("Invalid input.\n");
                break;
        }
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            UserDataLinkedList udLL = new UserDataLinkedList();

            while (true) {
                System.out.println();
                System.out.println("Operation:");
                System.out.println("Enter 1: Aggregation");
                System.out.println("Enter 2: Top K");
                System.out.println("Enter 3: Top K Group");
                System.out.println("Enter 4: Exit");
                System.out.println();
                int operation = scanner.nextInt();
                System.out.println();

                if (operation == 4)
                    break;

                switch (operation) {
                    case 1:
                        System.out.println("Aggregation operation:");
                        System.out.println("Enter 1: MAX");
                        System.out.println("Enter 2: MIN");
                        System.out.println("Enter 3: TOTAL");
                        System.out.println("Enter 4: AVERAGE");
                        System.out.println("Enter 0: Exit");
                        System.out.println();
                        int aggregationOptn = scanner.nextInt();

                        if (aggregationOptn == 0)
                            break;

                        if (aggregationOptn == 1) {
                            aggregationCase(UserDataLinkedList.AggrigationOps.MAX, scanner, udLL);
                        }

                        if (aggregationOptn == 2) {
                            aggregationCase(UserDataLinkedList.AggrigationOps.MIN, scanner, udLL);
                        }

                        if (aggregationOptn == 3) {
                            aggregationCase(UserDataLinkedList.AggrigationOps.TOTAL, scanner, udLL);
                        }

                        if (aggregationOptn == 4) {
                            aggregationCase(UserDataLinkedList.AggrigationOps.AVG, scanner, udLL);
                        }

                        break;
                    case 2:
                        System.out.println("Top K");
                        System.out.println("Enter 1: Age");
                        System.out.println("Enter 2: No. of Vehicle");
                        System.out.println("Enter 3: Annual Income");
                        System.out.println("Enter 0: Exit");
                        System.out.println();
                        int topKOperation = scanner.nextInt();
                        System.out.println();

                        if (topKOperation == 0)
                            break;

                        if (topKOperation == 1) {
                            topKCase(UserData.NumericFields.AGE, scanner, udLL);
                        }

                        if (topKOperation == 2) {
                            topKCase(UserData.NumericFields.NO_OF_VEHICLE, scanner, udLL);
                        }

                        if (topKOperation == 3) {
                            topKCase(UserData.NumericFields.ANNUAL_INCOME, scanner, udLL);
                        }
                        break;
                    case 3:
                        System.out.println("Top K Group");
                        System.out.println("Enter 1: Age");
                        System.out.println("Enter 2: No. of Vehicle");
                        System.out.println("Enter 3: Annual Income");
                        System.out.println("Enter 0: Exit");
                        System.out.println();
                        int topKGroupOperation = scanner.nextInt();
                        System.out.println();

                        if (topKGroupOperation == 0)
                            break;

                        if (topKGroupOperation == 1) {
                            topKGroupCase(UserData.NumericFields.AGE, scanner, udLL);
                        }

                        if (topKGroupOperation == 2) {
                            topKGroupCase(UserData.NumericFields.NO_OF_VEHICLE, scanner, udLL);
                        }

                        if (topKGroupOperation == 3) {
                            topKGroupCase(UserData.NumericFields.ANNUAL_INCOME, scanner, udLL);
                        }
                        break;
                    default:
                        System.out.println("Invalid input.\n");
                }

            }
            udLL = null;
            scanner.close();
        } catch (Exception error) {
            System.out.println("Something went wrong, please check your input, " + error);
        }
    }
}
