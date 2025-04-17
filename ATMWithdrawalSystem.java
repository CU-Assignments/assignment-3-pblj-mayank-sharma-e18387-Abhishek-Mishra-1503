import java.util.Scanner;

// Custom exception for invalid PIN
class InvalidPinException extends Exception {
    public InvalidPinException(String message) {
        super(message);
    }
}

// Custom exception for insufficient balance
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class ATMWithdrawalSystem {
    private static final String CORRECT_PIN = "4321"; // Pre-set PIN
    private static double balance = 5000.0; // Initial balance

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Prompt for PIN
            System.out.print("Enter your PIN: ");
            String enteredPin = scanner.nextLine();

            // Check for PIN validity
            if (!enteredPin.equals(CORRECT_PIN)) {
                throw new InvalidPinException("Invalid PIN entered!");
            }

            // Ask for withdrawal amount
            System.out.print("Enter withdrawal amount: ₹");
            double amount = scanner.nextDouble();

            // Check balance
            if (amount > balance) {
                throw new InsufficientBalanceException("Insufficient balance for withdrawal.");
            }

            // Perform withdrawal
            balance -= amount;
            System.out.println("✅ Withdrawal of ₹" + amount + " successful!");

        } catch (InvalidPinException | InsufficientBalanceException e) {
            System.out.println("❌ Transaction Failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected Error: " + e.getMessage());
        } finally {
            System.out.printf("💰 Remaining Balance: ₹%.2f\n", balance);
            scanner.close();
        }
    }
}
