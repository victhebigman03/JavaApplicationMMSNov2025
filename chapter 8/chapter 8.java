8.4 — Rectangle Class
class Rectangle {
    private double length = 1, width = 1;

    public void setLength(double x) {
        if (x > 0 && x < 20) length = x;
        else throw new IllegalArgumentException("Invalid length");
    }

    public void setWidth(double x) {
        if (x > 0 && x < 20) width = x;
        else throw new IllegalArgumentException("Invalid width");
    }

    public double getLength() { return length; }
    public double getWidth() { return width; }

    public double area() { return length * width; }
    public double perimeter() { return 2 * (length + width); }
}

public class RectangleTest {
    public static void main(String[] args) {
        Rectangle r = new Rectangle();

        r.setLength(5.5);
        r.setWidth(3.2);

        System.out.println("Length: " + r.getLength());
        System.out.println("Width: " + r.getWidth());
        System.out.println("Area: " + r.area());
        System.out.println("Perimeter: " + r.perimeter());
    }
}
8.5 — Time2 Using Seconds
class Time2 {
    private int seconds;

    public Time2() { this(0, 0, 0); }

    public Time2(int h) { this(h, 0, 0); }

    public Time2(int h, int m) { this(h, m, 0); }

    public Time2(int h, int m, int s) {
        setTime(h, m, s);
    }

    public Time2(Time2 t) {
        seconds = t.seconds;
    }

    public void setTime(int h, int m, int s) {
        if (h < 0 || h > 23 || m < 0 || m > 59 || s < 0 || s > 59)
            throw new IllegalArgumentException("Invalid time");
        seconds = h * 3600 + m * 60 + s;
    }

    public int getHour() { return seconds / 3600; }
    public int getMinute() { return seconds % 3600 / 60; }
    public int getSecond() { return seconds % 60; }

    public String toUniversalString() {
        return String.format("%02d:%02d:%02d", getHour(), getMinute(), getSecond());
    }

    public String toString() {
        int h = getHour();
        return String.format("%d:%02d:%02d %s",
                h == 0 || h == 12 ? 12 : h % 12,
                getMinute(), getSecond(), h < 12 ? "AM" : "PM");
    }
}

public class TimeTest {
    public static void main(String[] args) {
        Time2 t = new Time2(14, 30, 20);
        System.out.println(t.toUniversalString());
        System.out.println(t);
    }
}
8.6 — Savings Account
class SavingsAccount {
    private double balance;
    private static double annualInterestRate;

    public SavingsAccount(double b) {
        balance = b;
    }

    public void calculateMonthlyInterest() {
        balance += balance * annualInterestRate / 12;
    }

    public double getBalance() {
        return balance;
    }

    public static void modifyInterestRate(double r) {
        annualInterestRate = r;
    }
}

public class SavingsTest {
    public static void main(String[] args) {
        SavingsAccount saver1 = new SavingsAccount(2000);
        SavingsAccount saver2 = new SavingsAccount(3000);

        SavingsAccount.modifyInterestRate(0.04);

        for (int i = 1; i <= 12; i++) {
            saver1.calculateMonthlyInterest();
            saver2.calculateMonthlyInterest();
            System.out.printf("%d: %.2f  %.2f%n",
                    i, saver1.getBalance(), saver2.getBalance());
        }

        SavingsAccount.modifyInterestRate(0.05);
        saver1.calculateMonthlyInterest();
        saver2.calculateMonthlyInterest();

        System.out.printf("5%%: %.2f  %.2f%n",
                saver1.getBalance(), saver2.getBalance());
    }
}
8.7 — Enhanced Time2
class Time2 {
    private int seconds;

    public Time2(int h, int m, int s) {
        setTime(h, m, s);
    }

    public void setTime(int h, int m, int s) {
        if (h < 0 || h > 23 || m < 0 || m > 59 || s < 0 || s > 59)
            throw new IllegalArgumentException("Invalid time");
        seconds = h * 3600 + m * 60 + s;
    }

    public int getHour() { return seconds / 3600; }
    public int getMinute() { return seconds % 3600 / 60; }
    public int getSecond() { return seconds % 60; }

    public void tick() {
        seconds = (seconds + 1) % 86400;
    }

    public void incrementMinute() {
        seconds = (seconds + 60) % 86400;
    }

    public void incrementHour() {
        seconds = (seconds + 3600) % 86400;
    }

    public String toString() {
        int h = getHour();
        return String.format("%d:%02d:%02d %s",
                h == 0 || h == 12 ? 12 : h % 12,
                getMinute(), getSecond(), h < 12 ? "AM" : "PM");
    }
}

public class TimeTest {
    public static void main(String[] args) {
        Time2 t = new Time2(23, 59, 59);

        System.out.println(t);
        t.tick();
        System.out.println(t);

        t = new Time2(10, 59, 0);
        t.incrementMinute();
        System.out.println(t);

        t = new Time2(11, 0, 0);
        t.incrementHour();
        System.out.println(t);
    }
}
8.8 — Enhanced Date
class Date {
    private int month, day, year;

    private static final int[] days =
        {0,31,28,31,30,31,30,31,31,30,31,30,31};

    public Date(int m, int d, int y) {
        if (y <= 0 || m < 1 || m > 12)
            throw new IllegalArgumentException("Invalid date");

        int max = days[m];
        if (m == 2 && leap(y)) max = 29;

        if (d < 1 || d > max)
            throw new IllegalArgumentException("Invalid date");

        month = m; day = d; year = y;
    }

    private boolean leap(int y) {
        return y % 400 == 0 || y % 4 == 0 && y % 100 != 0;
    }

    public void nextDay() {
        int max = days[month];
        if (month == 2 && leap(year)) max = 29;

        if (++day > max) {
            day = 1;
            if (++month > 12) {
                month = 1;
                year++;
            }
        }
    }

    public String toString() {
        return month + "/" + day + "/" + year;
    }
}

public class DateTest {
    public static void main(String[] args) {
        Date d = new Date(12, 30, 2026);

        for (int i = 0; i < 5; i++) {
            System.out.println(d);
            d.nextDay();
        }
    }
}
8.9 — Static Math Imports

The important part of this exercise is using separate static imports for the Math members.

import static java.lang.Math.random;
import static java.lang.Math.round;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

class DrawPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < 5; i++) {
            int x1 = (int) round(random() * 300);
            int y1 = (int) round(random() * 300);
            int x2 = (int) round(random() * 300);
            int y2 = (int) round(random() * 300);

            g.setColor(new Color(
                (int)(random() * 256),
                (int)(random() * 256),
                (int)(random() * 256)));

            g.drawLine(x1, y1, x2, y2);
        }
    }
}
import javax.swing.JFrame;

public class TestDraw {
    public static void main(String[] args) {
        JFrame f = new JFrame("Random Lines");

        f.add(new DrawPanel());
        f.setSize(300, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
8.10 — TrafficLight Enum
enum TrafficLight {
    RED(30), GREEN(45), YELLOW(5);

    private int duration;

    TrafficLight(int d) {
        duration = d;
    }

    public int getDuration() {
        return duration;
    }
}

public class TrafficTest {
    public static void main(String[] args) {
        for (TrafficLight t : TrafficLight.values())
            System.out.println(t + " = " + t.getDuration() + " seconds");
    }
}
8.11 — Complex Numbers
class Complex {
    private double real, imaginary;

    public Complex() {
        this(0, 0);
    }

    public Complex(double r, double i) {
        real = r;
        imaginary = i;
    }

    public static Complex add(Complex a, Complex b) {
        return new Complex(a.real + b.real, a.imaginary + b.imaginary);
    }

    public static Complex subtract(Complex a, Complex b) {
        return new Complex(a.real - b.real, a.imaginary - b.imaginary);
    }

    public String toString() {
        return "(" + real + ", " + imaginary + ")";
    }
}

public class ComplexTest {
    public static void main(String[] args) {
        Complex a = new Complex(4, 3);
        Complex b = new Complex(2, 1);

        System.out.println("Add: " + Complex.add(a, b));
        System.out.println("Subtract: " + Complex.subtract(a, b));
    }
}
8.12 — DateAndTime
class DateAndTime {
    private int day, month, year;
    private int seconds;

    public DateAndTime(int m, int d, int y, int h, int min, int s) {
        month = m; day = d; year = y;
        seconds = h * 3600 + min * 60 + s;
    }

    private void nextDay() {
        day++;

        if (day > 31) {
            day = 1;
            month++;
        }

        if (month > 12) {
            month = 1;
            year++;
        }
    }

    public void incrementHour() {
        seconds += 3600;

        if (seconds >= 86400) {
            seconds -= 86400;
            nextDay();
        }
    }

    public String toString() {
        int h = seconds / 3600;
        int m = seconds % 3600 / 60;
        int s = seconds % 60;

        return String.format("%d/%d/%d %02d:%02d:%02d",
                month, day, year, h, m, s);
    }
}

public class DateTimeTest {
    public static void main(String[] args) {
        DateAndTime d = new DateAndTime(12, 31, 2026, 23, 0, 0);

        System.out.println(d);
        d.incrementHour();
        System.out.println(d);
    }
}
8.13 — IntegerSet
class IntegerSet {
    private boolean[] set = new boolean[101];

    public void insertElement(int n) {
        if (n >= 0 && n <= 100) set[n] = true;
    }

    public void deleteElement(int n) {
        if (n >= 0 && n <= 100) set[n] = false;
    }

    public static IntegerSet union(IntegerSet a, IntegerSet b) {
        IntegerSet c = new IntegerSet();

        for (int i = 0; i <= 100; i++)
            c.set[i] = a.set[i] || b.set[i];

        return c;
    }

    public static IntegerSet intersection(IntegerSet a, IntegerSet b) {
        IntegerSet c = new IntegerSet();

        for (int i = 0; i <= 100; i++)
            c.set[i] = a.set[i] && b.set[i];

        return c;
    }

    public boolean isEqualTo(IntegerSet b) {
        for (int i = 0; i <= 100; i++)
            if (set[i] != b.set[i]) return false;

        return true;
    }

    public String toString() {
        String s = "";

        for (int i = 0; i <= 100; i++)
            if (set[i]) s += i + " ";

        return s.isEmpty() ? "---" : s;
    }
}

public class IntegerSetTest {
    public static void main(String[] args) {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.insertElement(10);
        a.insertElement(20);
        a.insertElement(30);

        b.insertElement(20);
        b.insertElement(40);

        System.out.println("A: " + a);
        System.out.println("B: " + b);
        System.out.println("Union: " + IntegerSet.union(a, b));
        System.out.println("Intersection: " + IntegerSet.intersection(a, b));
        System.out.println("Equal: " + a.isEqualTo(b));
    }
}
8.14 — Date Class
class Date {
    private int month, day, year;

    private static final String[] names = {
        "", "January", "February", "March", "April",
        "May", "June", "July", "August", "September",
        "October", "November", "December"
    };

    public Date(int m, int d, int y) {
        month = m; day = d; year = y;
    }

    public Date(String m, int d, int y) {
        for (int i = 1; i <= 12; i++)
            if (names[i].equals(m)) month = i;

        day = d;
        year = y;
    }

    public Date(int dayOfYear, int year) {
        month = 1;
        while (dayOfYear > days(month)) {
            dayOfYear -= days(month++);
        }

        day = dayOfYear;
        this.year = year;
    }

    private int days(int m) {
        int[] d = {0,31,28,31,30,31,30,31,31,30,31,30,31};
        return d[m];
    }

    public String toString() {
        return String.format("%02d/%02d/%04d%n%s %d, %d%n%d %d",
                month, day, year, names[month], day, year,
                dayOfYear(), year);
    }

    private int dayOfYear() {
        int total = day;
        for (int i = 1; i < month; i++)
            total += days(i);
        return total;
    }
}

public class DateTest {
    public static void main(String[] args) {
        System.out.println(new Date(6, 14, 1992));
        System.out.println(new Date("June", 14, 1992));
        System.out.println(new Date(166, 1992));
    }
}
8.15 — Rational Numbers
class Rational {
    private int n, d;

    public Rational() {
        this(0, 1);
    }

    public Rational(int n, int d) {
        if (d == 0) throw new IllegalArgumentException("Zero denominator");

        int g = gcd(Math.abs(n), Math.abs(d));

        this.n = n / g;
        this.d = d / g;

        if (this.d < 0) {
            this.n = -this.n;
            this.d = -this.d;
        }
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a == 0 ? 1 : a;
    }

    public static Rational add(Rational a, Rational b) {
        return new Rational(a.n * b.d + b.n * a.d, a.d * b.d);
    }

    public static Rational subtract(Rational a, Rational b) {
        return new Rational(a.n * b.d - b.n * a.d, a.d * b.d);
    }

    public static Rational multiply(Rational a, Rational b) {
        return new Rational(a.n * b.n, a.d * b.d);
    }

    public static Rational divide(Rational a, Rational b) {
        return new Rational(a.n * b.d, a.d * b.n);
    }

    public String toString() {
        return n + "/" + d;
    }

    public String decimal() {
        return String.format("%.3f", (double)n / d);
    }
}

public class RationalTest {
    public static void main(String[] args) {
        Rational a = new Rational(2, 4);
        Rational b = new Rational(3, 5);

        System.out.println("A = " + a);
        System.out.println("Add = " + Rational.add(a, b));
        System.out.println("Subtract = " + Rational.subtract(a, b));
        System.out.println("Multiply = " + Rational.multiply(a, b));
        System.out.println("Divide = " + Rational.divide(a, b));
        System.out.println("Decimal = " + a.decimal());
    }
}
8.16 — HugeInteger
class HugeInteger {
    private int[] digits = new int[40];

    public void parse(String s) {
        if (s.length() > 40)
            throw new IllegalArgumentException("Too large");

        for (int i = 0; i < s.length(); i++)
            digits[40 - s.length() + i] = s.charAt(i) - '0';
    }

    public String toString() {
        String s = "";

        for (int n : digits) s += n;

        return s.replaceFirst("^0+(?!$)", "");
    }

    public boolean isZero() {
        return toString().equals("0");
    }

    public boolean isEqualTo(HugeInteger x) {
        return toString().equals(x.toString());
    }

    public boolean isNotEqualTo(HugeInteger x) {
        return !isEqualTo(x);
    }

    public boolean isGreaterThan(HugeInteger x) {
        return toString().compareTo(x.toString()) > 0;
    }

    public boolean isLessThan(HugeInteger x) {
        return toString().compareTo(x.toString()) < 0;
    }

    public boolean isGreaterThanOrEqualTo(HugeInteger x) {
        return !isLessThan(x);
    }

    public boolean isLessThanOrEqualTo(HugeInteger x) {
        return !isGreaterThan(x);
    }
}

public class HugeIntegerTest {
    public static void main(String[] args) {
        HugeInteger a = new HugeInteger();
        HugeInteger b = new HugeInteger();

        a.parse("123456789");
        b.parse("987654321");

        System.out.println(a);
        System.out.println(b);
        System.out.println(a.isLessThan(b));
    }
}
8.17 — Tic-Tac-Toe
import java.util.Scanner;

class TicTacToe {
    enum Cell { X, O, EMPTY }

    private Cell[][] board = new Cell[3][3];

    public TicTacToe() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = Cell.EMPTY;
    }

    public boolean move(int r, int c, Cell p) {
        if (r < 0 || r > 2 || c < 0 || c > 2 ||
            board[r][c] != Cell.EMPTY)
            return false;

        board[r][c] = p;
        return true;
    }

    public boolean won(Cell p) {
        for (int i = 0; i < 3; i++)
            if (board[i][0] == p && board[i][1] == p && board[i][2] == p ||
                board[0][i] == p && board[1][i] == p && board[2][i] == p)
                return true;

        return board[0][0] == p && board[1][1] == p && board[2][2] == p ||
               board[0][2] == p && board[1][1] == p && board[2][0] == p;
    }

    public boolean draw() {
        for (Cell[] r : board)
            for (Cell c : r)
                if (c == Cell.EMPTY) return false;
        return true;
    }

    public void print() {
        for (Cell[] r : board) {
            for (Cell c : r) System.out.print(c + " ");
            System.out.println();
        }
    }
}

public class TicTacToeTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TicTacToe game = new TicTacToe();

        for (int turn = 0; turn < 9; turn++) {
            game.print();

            TicTacToe.Cell p =
                turn % 2 == 0 ? TicTacToe.Cell.X : TicTacToe.Cell.O;

            System.out.print(p + " row and column: ");
            int r = in.nextInt();
            int c = in.nextInt();

            if (!game.move(r, c, p)) {
                System.out.println("Invalid move");
                turn--;
                continue;
            }

            if (game.won(p)) {
                game.print();
                System.out.println(p + " wins!");
                return;
            }
        }

        System.out.println("Draw!");
    }
}
8.18 — Account Using BigDecimal
import java.math.BigDecimal;

class Account {
    private BigDecimal balance;

    public Account(BigDecimal balance) {
        this.balance = balance;
    }

    public void credit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void debit(BigDecimal amount) {
        if (amount.compareTo(balance) <= 0)
            balance = balance.subtract(amount);
        else
            System.out.println("Debit exceeds balance");
    }

    public BigDecimal getBalance() {
        return balance;
    }
}

public class AccountTest {
    public static void main(String[] args) {
        Account a = new Account(new BigDecimal("1000.00"));

        a.credit(new BigDecimal("250.50"));
        a.debit(new BigDecimal("100.25"));

        System.out.println("Balance: " + a.getBalance());
    }
}
8.19 — Emergency Response Class

A simple design can represent who reported the emergency, phone number, location, emergency type, response type, time and status.

class Emergency {
    private String name, phone, location;
    private String type, response, status;

    public Emergency(String name, String phone, String location,
                     String type, String response) {
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.type = type;
        this.response = response;
        status = "Pending";
    }

    public void updateStatus(String s) {
        status = s;
    }

    public String toString() {
        return "Reporter: " + name +
               "\nPhone: " + phone +
               "\nLocation: " + location +
               "\nEmergency: " + type +
               "\nResponse: " + response +
               "\nStatus: " + status;
    }
}

public class EmergencyTest {
    public static void main(String[] args) {
        Emergency e = new Emergency(
            "John", "08012345678", "Main Street",
            "Fire", "Fire Department");

        System.out.println(e);

        e.updateStatus("Responding");

        System.out.println("\nUpdated:");
        System.out.println(e);
    }
}