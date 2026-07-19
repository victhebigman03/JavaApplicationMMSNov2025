public class SmallestNumber {

    // Method to find the smallest of three numbers
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

    public static void main(String[] args) {
        int result = findSmallest(15, 8, 22);
        System.out.println("The smallest number is: " + result);
    }
}