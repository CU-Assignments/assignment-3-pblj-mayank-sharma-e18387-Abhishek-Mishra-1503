import java.util.Scanner;

public class SquareRootCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Prompt user for input
            System.out.print("Enter a number to calculate its square root: ");
            String input = scanner.nextLine();

            // Try converting input to a double
            double number = Double.parseDouble(input);

            // Check if number is negative
            if (number < 0) {
                throw new IllegalArgumentException("Cannot calculate square root of a negative number.");
            }

            // Calculate square root
            double result = Math.sqrt(number);
            System.out.printf("Square root of %.2f is %.4f\n", number, result);

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid numeric value.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
