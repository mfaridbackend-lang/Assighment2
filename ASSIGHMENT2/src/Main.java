import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int CORRECT_PIN = 1234;
        final int MAX_ATTEMPTS = 3;

        double balance = 2500.75;
        int transactionCount = 0;

        int attempts = 0;
        boolean loggedIn = false;

        do {
            System.out.print("Enter your 4-digit PIN: ");
            int enteredPin = scanner.nextInt();
            attempts++;

            if (enteredPin == CORRECT_PIN) {
                loggedIn = true;
            } else if (attempts < MAX_ATTEMPTS) {
                System.out.println("Wrong PIN. Attempts left: " + (MAX_ATTEMPTS - attempts));
            }
        } while (!loggedIn && attempts < MAX_ATTEMPTS);

        if (!loggedIn) {
            System.out.println("Your account has been locked.");
            scanner.close();
            return;
        }


        while (true) {
            System.out.println();
            System.out.println("========= ATM =========");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Show Account Status");
            System.out.println("5. Exit");
            System.out.println("=======================");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 5) {
                System.out.println("Thank you for using our ATM.");
                System.out.println("Total successful transactions: " + transactionCount);
                break;
            }

            switch (choice) {
                case 1 -> System.out.println("Current balance: " + balance);

                case 2 -> {
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();

                    if (depositAmount > 0) {
                        balance += depositAmount;
                        transactionCount++;
                        System.out.println("Deposit successful. Updated balance: " + balance);
                    } else {
                        System.out.println("Invalid amount.");
                    }
                }

                case 3 -> {
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();

                    if (withdrawAmount == 0) {
                        System.out.println("Transaction cancelled.");
                    } else if (withdrawAmount < 0) {
                        System.out.println("Invalid amount.");
                    } else if (withdrawAmount > balance) {
                        System.out.println("Insufficient balance.");
                    } else {
                        balance -= withdrawAmount;
                        transactionCount++;
                        System.out.println("Withdrawal successful. Updated balance: " + balance);

                        if (balance < 0.005) {
                            System.out.println("Warning: Your account is empty.");
                        }
                    }
                }

                case 4 -> {
                    if (balance >= 5000) {
                        System.out.println("Account status: VIP Customer");
                    } else if (balance >= 1000) {
                        System.out.println("Account status: Regular Customer");
                    } else {
                        System.out.println("Account status: Low Balance");
                    }
                }

                default -> System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}