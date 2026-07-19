import java.util.Scanner;

public class PracticeMinNumComparisons {

    // 1. Method to find the smallest number among 3 numbers
    public static int findSmallest(int a, int b, int c) {
        int smallest = a;
        if (b < smallest) {
            smallest = b;
        }
        if (c < smallest) {
            smallest = c;
        }
        return smallest;
    }

    // 2. Method to calculate the average of 3 numbers
    public static double calculateAverage(int a, int b, int c) {
        return (a + b + c) / 3.0; // use 3.0 to get decimal result
    }

    // 3. Method to calculate the sum of 10 numbers
    public static int sumOfTen(int[] numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    // 4. Method to accept five numbers from user and check if sum is even or odd
    public static void checkEvenOddSum(Scanner sc) {
        int sum = 0;
        System.out.println("Enter 5 numbers:");
        for (int i = 1; i <= 5; i++) {
            System.out.print("Number " + i + ": ");
            sum += sc.nextInt();
        }

        if (sum % 2 == 0) {
            System.out.println("Sum = " + sum + " is Even");
        } else {
            System.out.println("Sum = " + sum + " is Odd");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Test 1: Smallest of 3
        System.out.println("Smallest of 10, 5, 20: " + findSmallest(10, 5, 20));

        // Test 2: Average of 3
        System.out.println("Average of 10, 20, 30: " + calculateAverage(10, 20, 30));

        // Test 3: Sum of 10 numbers
        int[] tenNums = {1,2,3,4,5,6,7,8,9,10};
        System.out.println("Sum of 1 to 10: " + sumOfTen(tenNums));

        // Test 4: Even/Odd sum of 5 user inputs
        checkEvenOddSum(sc);

        sc.close();
    }
}