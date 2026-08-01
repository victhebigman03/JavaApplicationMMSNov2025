public class Rectangle {
    private double length = 1.0;
    private double width = 1.0;

    public void setLength(double length) {
        if (length > 0.0 && length < 20.0)
            this.length = length;
    }

    public void setWidth(double width) {
        if (width > 0.0 && width < 20.0)
            this.width = width;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double area() {
        return length * width;
    }

    public double perimeter() {
        return 2 * (length + width);
    }

    public static void main(String[] args) {
        Rectangle r = new Rectangle();

        r.setLength(10);
        r.setWidth(5);

        System.out.println("Length: " + r.getLength());
        System.out.println("Width: " + r.getWidth());
        System.out.println("Area: " + r.area());
        System.out.println("Perimeter: " + r.perimeter());
    }
}
8.5 — Time2 Using Seconds
public class Time2 {
    private int seconds;

    public Time2() {
        this(0, 0, 0);
    }

    public Time2(int h) {
        this(h, 0, 0);
    }

    public Time2(int h, int m) {
        this(h, m, 0);
    }

    public Time2(int h, int m, int s) {
        if (h < 0 || h >= 24 || m < 0 || m >= 60 || s < 0 || s >= 60)
            throw new IllegalArgumentException("Invalid time");

        seconds = h * 3600 + m * 60 + s;
    }

    public int getHour() {
        return seconds / 3600;
    }

    public int getMinute() {
        return (seconds % 3600) / 60;
    }

    public int getSecond() {
        return seconds % 60;
    }

    public String toUniversalString() {
        return String.format("%02d:%02d:%02d",
                getHour(), getMinute(), getSecond());
    }

    public String toString() {
        return String.format("%d:%02d:%02d %s",
                (getHour() == 0 || getHour() == 12) ? 12 : getHour() % 12,
                getMinute(), getSecond(),
                getHour() < 12 ? "AM" : "PM");
    }
}
8.6 — SavingsAccount
public class SavingsAccount {
    private double savingsBalance;
    private static double annualInterestRate;

    public SavingsAccount(double balance) {
        savingsBalance = balance;
    }

    public void calculateMonthlyInterest() {
        savingsBalance += savingsBalance * annualInterestRate / 12;
    }

    public static void modifyInterestRate(double rate) {
        annualInterestRate = rate;
    }

    public double getBalance() {
        return savingsBalance;
    }

    public static void main(String[] args) {
        SavingsAccount saver1 = new SavingsAccount(2000);
        SavingsAccount saver2 = new SavingsAccount(3000);

        modifyInterestRate(.04);

        for (int i = 1; i <= 12; i++) {
            saver1.calculateMonthlyInterest();
            saver2.calculateMonthlyInterest();
        }

        System.out.printf("Saver1: %.2f%n", saver1.getBalance());
        System.out.printf("Saver2: %.2f%n", saver2.getBalance());

        modifyInterestRate(.05);

        saver1.calculateMonthlyInterest();
        saver2.calculateMonthlyInterest();

        System.out.printf("Saver1: %.2f%n", saver1.getBalance());
        System.out.printf("Saver2: %.2f%n", saver2.getBalance());
    }
}
8.7 — Enhancing Time2
public class Time2 {
    private int hour, minute, second;

    public Time2(int h, int m, int s) {
        hour = h;
        minute = m;
        second = s;
    }

    public void tick() {
        second++;

        if (second == 60) {
            second = 0;
            incrementMinute();
        }
    }

    public void incrementMinute() {
        minute++;

        if (minute == 60) {
            minute = 0;
            incrementHour();
        }
    }

    public void incrementHour() {
        hour++;

        if (hour == 24)
            hour = 0;
    }

    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public static void main(String[] args) {
        Time2 t = new Time2(23, 59, 59);

        t.tick();
        System.out.println(t);

        t.incrementMinute();
        System.out.println(t);

        t.incrementHour();
        System.out.println(t);
    }
}
8.8 — Date with nextDay
public class Date {
    private int month, day, year;

    private static final int[] days =
        {0,31,28,31,30,31,30,31,31,30,31,30,31};

    public Date(int m, int d, int y) {
        if (m < 1 || m > 12)
            throw new IllegalArgumentException("Invalid month");

        if (d < 1 || d > days[m])
            throw new IllegalArgumentException("Invalid day");

        if (m == 2 && d == 29 &&
            !(y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)))
            throw new IllegalArgumentException("Invalid day");

        month = m;
        day = d;
        year = y;
    }

    public void nextDay() {
        day++;

        if (day > days[month]) {
            day = 1;
            month++;

            if (month == 13) {
                month = 1;
                year++;
            }
        }
    }

    public String toString() {
        return month + "/" + day + "/" + year;
    }

    public static void main(String[] args) {
        Date d = new Date(12, 31, 2026);

        for (int i = 0; i < 3; i++) {
            System.out.println(d);
            d.nextDay();
        }
    }
}
8.9 — DrawPanel

The exercise asks you to rewrite the program so each static Math member has its own import. The short version is:

import static java.lang.Math.random;
import static java.lang.Math.nextInt;

However, Math does not have a static nextInt() method. Therefore, for the given DrawPanel code, the correct approach is to keep using SecureRandom:

import java.awt.Color;
import java.awt.Graphics;
import java.security.SecureRandom;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
    private SecureRandom random = new SecureRandom();
    private MyLine[] lines;

    public DrawPanel() {
        setBackground(Color.WHITE);
        lines = new MyLine[5 + random.nextInt(5)];

        for (int i = 0; i < lines.length; i++) {
            int x1 = random.nextInt(300);
            int y1 = random.nextInt(300);
            int x2 = random.nextInt(300);
            int y2 = random.nextInt(300);

            Color c = new Color(random.nextInt(256),
                    random.nextInt(256), random.nextInt(256));

            lines[i] = new MyLine(x1, y1, x2, y2, c);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (MyLine line : lines)
            line.draw(g);
    }
}
8.10 — TrafficLight Enum
enum TrafficLight {
    RED(30),
    GREEN(40),
    YELLOW(5);

    private int duration;

    TrafficLight(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}

public class TestTrafficLight {
    public static void main(String[] args) {
        for (TrafficLight light : TrafficLight.values())
            System.out.println(light + ": " + light.getDuration() + " seconds");
    }
}
8.11 — Complex Numbers
public class Complex {
    private double real;
    private double imaginary;

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

    public static void main(String[] args) {
        Complex a = new Complex(4, 5);
        Complex b = new Complex(2, 3);

        System.out.println("Add: " + Complex.add(a, b));
        System.out.println("Subtract: " + Complex.subtract(a, b));
    }
}
8.12 — DateAndTime
public class DateAndTime {
    private Date date;
    private Time2 time;

    public DateAndTime(int m, int d, int y, int h, int min, int s) {
        date = new Date(m, d, y);
        time = new Time2(h, min, s);
    }

    public void incrementHour() {
        int oldHour = time.getHour();
        time.incrementHour();

        if (oldHour == 23)
            date.nextDay();
    }

    public String toString() {
        return date + " " + time;
    }

    public static void main(String[] args) {
        DateAndTime dt = new DateAndTime(12, 31, 2026, 23, 0, 0);

        System.out.println(dt);
        dt.incrementHour();
        System.out.println(dt);
    }
}
8.13 — IntegerSet
public class IntegerSet {
    private boolean[] set = new boolean[101];

    public void insertElement(int n) {
        if (n >= 0 && n <= 100)
            set[n] = true;
    }

    public void deleteElement(int n) {
        if (n >= 0 && n <= 100)
            set[n] = false;
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
            if (set[i] != b.set[i])
                return false;

        return true;
    }

    public String toString() {
        String result = "";

        for (int i = 0; i <= 100; i++)
            if (set[i])
                result += i + " ";

        return result.isEmpty() ? "---" : result;
    }

    public static void main(String[] args) {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.insertElement(10);
        a.insertElement(20);

        b.insertElement(20);
        b.insertElement(30);

        System.out.println("A: " + a);
        System.out.println("B: " + b);
        System.out.println("Union: " + union(a, b));
        System.out.println("Intersection: " + intersection(a, b));
    }
}
8.14 — Date Class
public class Date {
    private int month, day, year;

    public Date(int m, int d, int y) {
        month = m;
        day = d;
        year = y;
    }

    public Date(String m, int d, int y) {
        String[] months = {
            "January","February","March","April","May","June",
            "July","August","September","October","November","December"
        };

        for (int i = 0; i < months.length; i++)
            if (months[i].equals(m))
                month = i + 1;

        day = d;
        year = y;
    }

    public Date(int dayOfYear, int y) {
        month = 1;
        year = y;

        while (dayOfYear > 31) {
            dayOfYear -= 31;
            month++;
        }

        day = dayOfYear;
    }

    public String toString() {
        return month + "/" + day + "/" + year;
    }
}
8.15 — Rational Numbers
public class Rational {
    private int numerator;
    private int denominator;

    public Rational() {
        this(0, 1);
    }

    public Rational(int n, int d) {
        int gcd = gcd(n, d);

        numerator = n / gcd;
        denominator = d / gcd;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return Math.abs(a);
    }

    public static Rational add(Rational a, Rational b) {
        return new Rational(
            a.numerator * b.denominator +
            b.numerator * a.denominator,
            a.denominator * b.denominator);
    }

    public static Rational subtract(Rational a, Rational b) {
        return new Rational(
            a.numerator * b.denominator -
            b.numerator * a.denominator,
            a.denominator * b.denominator);
    }

    public static Rational multiply(Rational a, Rational b) {
        return new Rational(
            a.numerator * b.numerator,
            a.denominator * b.denominator);
    }

    public static Rational divide(Rational a, Rational b) {
        return new Rational(
            a.numerator * b.denominator,
            a.denominator * b.numerator);
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

    public double toDouble() {
        return (double) numerator / denominator;
    }
}
8.16 — HugeInteger
import java.math.BigInteger;

public class HugeInteger {
    private BigInteger number = BigInteger.ZERO;

    public void parse(String s) {
        number = new BigInteger(s);
    }

    public String toString() {
        return number.toString();
    }

    public HugeInteger add(HugeInteger x) {
        HugeInteger result = new HugeInteger();
        result.number = number.add(x.number);
        return result;
    }

    public HugeInteger subtract(HugeInteger x) {
        HugeInteger result = new HugeInteger();
        result.number = number.subtract(x.number);
        return result;
    }

    public boolean isEqualTo(HugeInteger x) {
        return number.equals(x.number);
    }

    public boolean isNotEqualTo(HugeInteger x) {
        return !number.equals(x.number);
    }

    public boolean isGreaterThan(HugeInteger x) {
        return number.compareTo(x.number) > 0;
    }

    public boolean isLessThan(HugeInteger x) {
        return number.compareTo(x.number) < 0;
    }

    public boolean isGreaterThanOrEqualTo(HugeInteger x) {
        return number.compareTo(x.number) >= 0;
    }

    public boolean isLessThanOrEqualTo(HugeInteger x) {
        return number.compareTo(x.number) <= 0;
    }

    public boolean isZero() {
        return number.equals(BigInteger.ZERO);
    }
}
8.17 — Tic-Tac-Toe
import java.util.Scanner;

public class TicTacToe {
    enum Cell { X, O, EMPTY }

    private Cell[][] board = new Cell[3][3];

    public TicTacToe() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = Cell.EMPTY;
    }

    public void move(int row, int col, Cell player) {
        if (board[row][col] == Cell.EMPTY)
            board[row][col] = player;
    }

    public boolean won(Cell p) {
        for (int i = 0; i < 3; i++)
            if (board[i][0] == p && board[i][1] == p && board[i][2] == p)
                return true;

        for (int i = 0; i < 3; i++)
            if (board[0][i] == p && board[1][i] == p && board[2][i] == p)
                return true;

        return board[0][0] == p && board[1][1] == p && board[2][2] == p ||
               board[0][2] == p && board[1][1] == p && board[2][0] == p;
    }

    public void display() {
        for (Cell[] row : board) {
            for (Cell c : row)
                System.out.print(c + " ");
            System.out.println();
        }
    }
}
8.18 — Account Using BigDecimal
import java.math.BigDecimal;

public class Account {
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
            System.out.println("Debit amount exceeded account balance.");
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
8.19 — Emergency Class

The exercise asks you to design attributes for an emergency-response class.

public class Emergency {
    private String callerName;
    private String phoneNumber;
    private String location;
    private String emergencyType;
    private String description;
    private String responseType;
    private String status;
    private String reportTime;

    public Emergency(String callerName, String phoneNumber,
                     String location, String emergencyType,
                     String description, String responseType,
                     String status, String reportTime) {

        this.callerName = callerName;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.emergencyType = emergencyType;
        this.description = description;
        this.responseType = responseType;
        this.status = status;
        this.reportTime = reportTime;
    }

    public void display() {
        System.out.println("Caller: " + callerName);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Location: " + location);
        System.out.println("Emergency: " + emergencyType);
        System.out.println("Description: " + description);
        System.out.println("Response: " + responseType);
        System.out.println("Status: " + status);
        System.out.println("Time: " + reportTime);
    }
} 