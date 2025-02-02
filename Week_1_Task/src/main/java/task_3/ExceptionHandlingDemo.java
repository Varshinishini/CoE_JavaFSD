package task_3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionHandlingDemo {
	public static void processInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a number: ");
            double number = scanner.nextDouble();

            if (number == 0) {
                throw new ArithmeticException("Division by zero is not allowed.");
            }

            double reciprocal = 1 / number;
            System.out.println("Reciprocal: " + reciprocal);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a numerical value.");
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Process completed.");
        }
    }

    public static void main(String[] args) {
        processInput();
    }

}
