public class EvenOddCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = new int[10];
        int evenCount = 0;
        int oddCount = 0;

        // Store 10 elements
        System.out.print("Enter number 1: ");
        numbers[0] = scanner.nextInt();

        System.out.print("Enter number 2: ");
        numbers[1] = scanner.nextInt();

        System.out.print("Enter number 3: ");
        numbers[2] = scanner.nextInt();

        System.out.print("Enter number 4: ");
        numbers[3] = scanner.nextInt();

        System.out.print("Enter number 5: ");
        numbers[4] = scanner.nextInt();

        System.out.print("Enter number 6: ");
        numbers[5] = scanner.nextInt();

        System.out.print("Enter number 7: ");
        numbers[6] = scanner.nextInt();

        System.out.print("Enter number 8: ");
        numbers[7] = scanner.nextInt();

        System.out.print("Enter number 9: ");
        numbers[8] = scanner.nextInt();

        System.out.print("Enter number 10: ");
        numbers[9] = scanner.nextInt();

        // Check each number
        if (numbers[0] % 2 == 0) {
            evenCount++;
        } else {
            oddCount++;
        }

        if (numbers[1] % 2 == 0) {
            evenCount++;
        } else {
            oddCount++;
        }

        if (numbers[2] % 2 == 0) {
            evenCount++;
        } else {
            oddCount++;
        }

        if (numbers[3] % 2 == 0) {
            evenCount++;
        } else {
            oddCount++;
        }

        if (numbers[4] % 2 == 0) {
            evenCount++;
        } else {
            oddCount++;
        }

        if (numbers[5] % 2 == 0) {
            evenCount++;
        } else {
            oddCount++;
        }

        if (numbers[6] % 2 == 0) {
            evenCount++;
        } else {
            oddCount++;
        }

        if (numbers[7] % 2 == 0) {
            evenCount++;
        } else {
            oddCount++;
        }

        if (numbers[8] % 2 == 0) {
            evenCount++;
        } else {
            oddCount++;
        }

        if (numbers[9] % 2 == 0) {
            evenCount++;
        } else {
            oddCount++;
        }

        // Display results
        System.out.println("Number of even numbers: " + evenCount);
        System.out.println("Number of odd numbers: " + oddCount);

        scanner.close();
    }
}