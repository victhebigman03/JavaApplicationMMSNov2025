import java.util.Scanner;

public class Exercise610SalesCommissions {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int[] counters = new int[9];

        System.out.print("Enter number of salespeople: ");
        int people = input.nextInt();

        for (int i = 0; i < people; i++) {
            System.out.print("Enter gross sales for salesperson "
                    + (i + 1) + ": ");

            double sales = input.nextDouble();

            int salary = (int) (200 + 0.09 * sales);

            if (salary >= 1000) {
                counters[8]++;
            } else {
                counters[(salary - 200) / 100]++;
            }
        }

        String[] ranges = {
            "$200-$299",
            "$300-$399",
            "$400-$499",
            "$500-$599",
            "$600-$699",
            "$700-$799",
            "$800-$899",
            "$900-$999",
            "$1,000 and over"
        };

        System.out.println("\nSalary Range\t\tNumber of Salespeople");

        for (int i = 0; i < counters.length; i++) {
            System.out.printf("%-20s%d%n", ranges[i], counters[i]);
        }

        input.close();
    }
} 

  public class Exercise611ArrayStatements {
    public static void main(String[] args) {

        // a) Set the 10 elements of counts to zero
        int[] counts = new int[10];

        // b) Add one to each of the 15 elements of bonus
        int[] bonus = new int[15];

        for (int i = 0; i < bonus.length; i++) {
            bonus[i]++;
        }

        // c) Display the five values of bestScores in column format
        int[] bestScores = {95, 88, 91, 100, 84};

        for (int score : bestScores) {
            System.out.println(score);
        }
    }
} 

  import java.util.Scanner;

public class Exercise612DuplicateElimination {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int[] unique = new int[5];
        int count = 0;

        for (int i = 0; i < 5; i++) {

            int value;

            while (true) {
                System.out.print(
                    "Enter number " + (i + 1) + " between 10 and 100: "
                );

                value = input.nextInt();

                if (value >= 10 && value <= 100) {
                    break;
                }

                System.out.println(
                    "Invalid number. Enter a number from 10 to 100."
                );
            }

            boolean duplicate = false;

            for (int j = 0; j < count; j++) {
                if (unique[j] == value) {
                    duplicate = true;
                    break;
                }
            }

            if (!duplicate) {
                unique[count] = value;
                count++;

                System.out.println("Number added: " + value);
            } else {
                System.out.println("Duplicate number: " + value);
            }

            System.out.print("Unique values: ");

            for (int j = 0; j < count; j++) {
                System.out.print(unique[j] + " ");
            }

            System.out.println("\n");
        }

        input.close();
    }
}  

   public class Exercise613ArrayOrder {
    public static void main(String[] args) {

        int[][] sales = new int[3][5];

        int order = 1;

        for (int row = 0; row < sales.length; row++) {

            for (int col = 0; col < sales[row].length; col++) {

                sales[row][col] = order;

                order++;
            }
        }

        System.out.println("Order in which elements are set:");

        for (int row = 0; row < sales.length; row++) {

            for (int col = 0; col < sales[row].length; col++) {

                System.out.printf("%2d ", sales[row][col]);
            }

            System.out.println();
        }
    }
}
  
  public class Exercise614VariableLengthProduct {

    public static int product(int... numbers) {

        int result = 1;

        for (int number : numbers) {
            result *= number;
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println(
            "product(2, 3) = " + product(2, 3)
        );

        System.out.println(
            "product(2, 3, 4) = " + product(2, 3, 4)
        );

        System.out.println(
            "product(1, 2, 3, 4, 5) = "
            + product(1, 2, 3, 4, 5)
        );

        System.out.println(
            "product(7) = " + product(7)
        );
    }
}