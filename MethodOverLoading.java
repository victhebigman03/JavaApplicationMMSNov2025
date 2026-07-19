import java.util.Scanner;

public class MethodOverLoading {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        MethodOverLoading mo1 = new MethodOverLoading();

        System.out.println("Calculating the perimeter of different shapes");
        System.out.println("Enter 1: for Square");
        System.out.println("Enter 2: for Rectangle");
        System.out.println("Enter 3: for Circle");
        System.out.println("Enter 4: for Trapezium");
        System.out.println("Enter 5: Exit");

        System.out.print("Enter your choice: ");
        int choice = scan.nextInt();

        switch (choice) {

            case 1:
                System.out.println("You want to calculate the perimeter of a square.");

                System.out.print("Enter the length: ");
                int length = scan.nextInt();

                System.out.printf("The perimeter of the square is %d%n",
                        mo1.perimeter(length));
                break;

            case 2:
                System.out.println("You want to calculate the perimeter of a rectangle.");

                System.out.print("Enter the length: ");
                int rectLength = scan.nextInt();

                System.out.print("Enter the breadth: ");
                int breadth = scan.nextInt();

                System.out.printf("The perimeter of the rectangle is %d%n",
                        mo1.perimeter(rectLength, breadth));
                break;

            case 3:
                System.out.println("You want to calculate the perimeter of a circle.");

                System.out.print("Enter the radius: ");
                double radius = scan.nextDouble();

                System.out.printf("The perimeter of the circle is %.2f%n",
                        mo1.perimeter(radius));
                break;

            case 4:
                System.out.println("You want to calculate the perimeter of a trapezium.");

                System.out.print("Enter side 1: ");
                int side1 = scan.nextInt();

                System.out.print("Enter side 2: ");
                int side2 = scan.nextInt();

                System.out.print("Enter side 3: ");
                int side3 = scan.nextInt();

                System.out.print("Enter side 4: ");
                int side4 = scan.nextInt();

                System.out.printf("The perimeter of the trapezium is %d%n",
                        mo1.perimeter(side1, side2, side3, side4));
                break;

            case 5:
                System.out.println("Exiting...");
                break;

            default:
                System.out.println("Invalid choice.");
        }

        scan.close();
    }

    // Square
    public int perimeter(int length) {
        return 4 * length;
    }

    // Rectangle
    public int perimeter(int length, int breadth) {
        return 2 * (length + breadth);
    }

    // Circle
    public double perimeter(double radius) {
        return 2 * Math.PI * radius;
    }

    // Trapezium
    public int perimeter(int side1, int side2, int side3, int side4) {
        return side1 + side2 + side3 + side4;
    }
}