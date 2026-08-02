6.10 — Sales Commissions
import java.util.Scanner;

public class SalesCommissions {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] salary = new int[9];

        System.out.print("Enter number of salespeople: ");
        int n = input.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter gross sales: ");
            double sales = input.nextDouble();

            int pay = (int)(200 + sales * 0.09);

            if (pay >= 1000)
                salary[8]++;
            else
                salary[(pay - 200) / 100]++;
        }

        System.out.println("\nSalary Range\tSalespeople");

        for (int i = 0; i < 8; i++)
            System.out.printf("$%d-%d\t\t%d%n",
                    200 + i * 100, 299 + i * 100, salary[i]);

        System.out.println("$1000 and over\t" + salary[8]);
    }
}
6.11 — One-Dimensional Array Operations

a) Set 10 elements to zero

int[] counts = new int[10];

for (int i = 0; i < counts.length; i++)
    counts[i] = 0;

b) Add one to each of 15 elements

int[] bonus = new int[15];

for (int i = 0; i < bonus.length; i++)
    bonus[i]++;

c) Display five values in column format

for (int i = 0; i < bestScores.length; i++)
    System.out.println(bestScores[i]);
6.12 — Duplicate Elimination
import java.util.Scanner;

public class DuplicateElimination {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] numbers = new int[5];
        int count = 0;

        for (int i = 0; i < 5; i++) {
            System.out.print("Enter number (10-100): ");
            int num = input.nextInt();

            boolean duplicate = false;

            for (int j = 0; j < count; j++)
                if (numbers[j] == num)
                    duplicate = true;

            if (!duplicate) {
                numbers[count++] = num;

                System.out.print("Unique values: ");
                for (int j = 0; j < count; j++)
                    System.out.print(numbers[j] + " ");
                System.out.println();
            }
        }
    }
}
6.13 — Three-by-Five Array

The elements are set to zero in this order:

sales[0][0]
sales[0][1]
sales[0][2]
sales[0][3]
sales[0][4]

sales[1][0]
sales[1][1]
sales[1][2]
sales[1][3]
sales[1][4]

sales[2][0]
sales[2][1]
sales[2][2]
sales[2][3]
sales[2][4]
6.14 — Variable-Length Argument List
public class Product {
    public static int product(int... numbers) {
        int result = 1;

        for (int n : numbers)
            result *= n;

        return result;
    }

    public static void main(String[] args) {
        System.out.println(product(2, 3));
        System.out.println(product(2, 3, 4));
        System.out.println(product(1, 2, 3, 4, 5));
    }
}
6.15 — Command-Line Arguments
public class ArraySize {
    public static void main(String[] args) {
        int size = args.length > 0 ? Integer.parseInt(args[0]) : 10;

        int[] array = new int[size];

        for (int i = 0; i < array.length; i++)
            array[i] = i + 1;

        for (int n : array)
            System.out.println(n);
    }
}
6.16 — Enhanced for Statement
public class SumArguments {
    public static void main(String[] args) {
        double sum = 0;

        for (String value : args)
            sum += Double.parseDouble(value);

        System.out.println("Sum = " + sum);
    }
}

Example:

java SumArguments 10.5 20.5 30

Output:

Sum = 61.0
6.17 — Dice Rolling
import java.util.Random;

public class DiceRolling {
    public static void main(String[] args) {
        Random random = new Random();
        int[] totals = new int[13];

        for (int i = 0; i < 36000000; i++) {
            int die1 = random.nextInt(6) + 1;
            int die2 = random.nextInt(6) + 1;
            totals[die1 + die2]++;
        }

        System.out.println("Sum\tFrequency");

        for (int i = 2; i <= 12; i++)
            System.out.println(i + "\t" + totals[i]);
    }
}
6.18 — Airline Reservations System
import java.util.Scanner;

public class AirlineReservations {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean[] seats = new boolean[10];

        while (true) {
            System.out.print("Enter 1 for First Class, 2 for Economy: ");
            int choice = input.nextInt();

            int start = choice == 1 ? 0 : 5;
            int end = start + 5;
            int seat = -1;

            for (int i = start; i < end; i++) {
                if (!seats[i]) {
                    seat = i;
                    break;
                }
            }

            if (seat == -1) {
                int other = choice == 1 ? 5 : 0;
                int otherEnd = other + 5;

                System.out.print("Section full. Accept another section? (yes/no): ");
                String answer = input.next();

                if (answer.equalsIgnoreCase("yes")) {
                    for (int i = other; i < otherEnd; i++) {
                        if (!seats[i]) {
                            seat = i;
                            break;
                        }
                    }
                } else {
                    System.out.println("Next flight leaves in 3 hours.");
                    continue;
                }
            }

            if (seat != -1) {
                seats[seat] = true;

                System.out.println("\nBoarding Pass");
                System.out.println("Seat: " + (seat + 1));
                System.out.println(seat < 5 ? "First Class" : "Economy");
            }
        }
    }
}
6.19 — Total Sales
import java.util.Scanner;

public class TotalSales {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double[][] sales = new double[5][4];

        System.out.print("Enter number of sales slips: ");
        int n = input.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Salesperson (1-4): ");
            int person = input.nextInt();

            System.out.print("Product (1-5): ");
            int product = input.nextInt();

            System.out.print("Sales amount: ");
            double amount = input.nextDouble();

            sales[product - 1][person - 1] += amount;
        }

        System.out.println("\nProduct\tS1\tS2\tS3\tS4\tTotal");

        for (int i = 0; i < 5; i++) {
            double total = 0;
            System.out.print((i + 1) + "\t");

            for (int j = 0; j < 4; j++) {
                System.out.printf("%.2f\t", sales[i][j]);
                total += sales[i][j];
            }

            System.out.printf("%.2f%n", total);
        }

        System.out.print("Total\t");

        for (int j = 0; j < 4; j++) {
            double total = 0;

            for (int i = 0; i < 5; i++)
                total += sales[i][j];

            System.out.printf("%.2f\t", total);
        }
    }
}