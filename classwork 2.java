import java.util.Scanner;

public class NumberCalculation {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double[] numbers = new double[10];

        System.out.println("Enter 10 numbers:");

        for (int i = 0; i < 10; i++) {
            System.out.print("Number " + (i + 1) + ": ");
            numbers[i] = scanner.nextDouble();
        }

        // Sum of numbers 6 to 9
        double sum6to9 = numbers[5] + numbers[6] + numbers[7] + numbers[8];

        // Sum of numbers 2, 4, and 9
        double sum249 = numbers[1] + numbers[3] + numbers[8];

        // Sum of numbers 2 to 5
        double sum2to5 = numbers[1] + numbers[2] + numbers[3] + numbers[4];

        System.out.println("\nSum of 6 to 9: " + sum6to9);
        System.out.println("Sum of 2, 4, and 9: " + sum249);
        System.out.println("Sum of 2 to 5: " + sum2to5);

        if (sum2to5 == 0) {
            System.out.println("Cannot divide by zero! Sum of 2 to 5 is 0.");
        } else {
            double result = (sum6to9 + sum249) / sum2to5;
            System.out.println("Result = (sum6to9 + sum249) / sum2to5 = " + result);
        }

        scanner.close();
    }
}