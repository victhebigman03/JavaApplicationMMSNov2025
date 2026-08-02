Face.java
public enum Face {
    DEUCE, THREE, FOUR, FIVE, SIX, SEVEN,
    EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
}
2. Suit.java
public enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}
3. Card.java
public class Card {
    private Face face;
    private Suit suit;

    public Card(Face f, Suit s) {
        face = f;
        suit = s;
    }

    public Face getFace() { return face; }
    public Suit getSuit() { return suit; }

    public String toString() {
        return face + " of " + suit;
    }
}
4. DeckOfCards.java
import java.util.Random;

public class DeckOfCards {
    Card[] deck = new Card[52];
    int current = 0;

    public DeckOfCards() {
        int i = 0;
        for (Suit s : Suit.values())
            for (Face f : Face.values())
                deck[i++] = new Card(f, s);
    }

    public void shuffle() {
        Random r = new Random();

        for (int i = 51; i > 0; i--) {
            int j = r.nextInt(i + 1);
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }

        current = 0;
    }

    public Card dealCard() {
        return current < 52 ? deck[current++] : null;
    }

    int[] count(Card[] h) {
        int[] c = new int[13];

        for (Card x : h)
            c[x.getFace().ordinal()]++;

        return c;
    }

    public boolean pair(Card[] h) {
        for (int n : count(h))
            if (n == 2) return true;
        return false;
    }

    public boolean twoPairs(Card[] h) {
        int p = 0;
        for (int n : count(h))
            if (n == 2) p++;
        return p == 2;
    }

    public boolean three(Card[] h) {
        for (int n : count(h))
            if (n == 3) return true;
        return false;
    }

    public boolean four(Card[] h) {
        for (int n : count(h))
            if (n == 4) return true;
        return false;
    }

    public boolean flush(Card[] h) {
        for (Card c : h)
            if (c.getSuit() != h[0].getSuit()) return false;
        return true;
    }

    public boolean straight(Card[] h) {
        int[] c = count(h);

        for (int i = 0; i <= 8; i++) {
            boolean ok = true;

            for (int j = 0; j < 5; j++)
                if (c[i + j] != 1) ok = false;

            if (ok) return true;
        }

        return c[12] == 1 && c[0] == 1 &&
               c[1] == 1 && c[2] == 1 && c[3] == 1;
    }

    public boolean fullHouse(Card[] h) {
        return three(h) && pair(h);
    }

    public String evaluate(Card[] h) {
        if (straight(h) && flush(h)) return "Straight Flush";
        if (four(h)) return "Four of a Kind";
        if (fullHouse(h)) return "Full House";
        if (flush(h)) return "Flush";
        if (straight(h)) return "Straight";
        if (three(h)) return "Three of a Kind";
        if (twoPairs(h)) return "Two Pairs";
        if (pair(h)) return "Pair";
        return "High Card";
    }

    public int value(Card[] h) {
        String s = evaluate(h);

        String[] names = {
            "High Card", "Pair", "Two Pairs", "Three of a Kind",
            "Straight", "Flush", "Full House",
            "Four of a Kind", "Straight Flush"
        };

        for (int i = 0; i < names.length; i++)
            if (s.equals(names[i])) return i;

        return 0;
    }
}
5. Exercise 1 — One Poker Hand

PokerHand.java

public class PokerHand {
    public static void main(String[] args) {

        DeckOfCards d = new DeckOfCards();
        d.shuffle();

        Card[] hand = new Card[5];

        for (int i = 0; i < 5; i++)
            hand[i] = d.dealCard();

        for (Card c : hand)
            System.out.println(c);

        System.out.println("Hand: " + d.evaluate(hand));
    }
}
6. Exercise 2 — Two Poker Hands

TwoPokerHands.java

public class TwoPokerHands {
    public static void main(String[] args) {

        DeckOfCards d = new DeckOfCards();
        d.shuffle();

        Card[] p = new Card[5];
        Card[] c = new Card[5];

        for (int i = 0; i < 5; i++) {
            p[i] = d.dealCard();
            c[i] = d.dealCard();
        }

        System.out.println("PLAYER:");
        for (Card x : p) System.out.println(x);
        System.out.println(d.evaluate(p));

        System.out.println("\nCOMPUTER:");
        for (Card x : c) System.out.println(x);
        System.out.println(d.evaluate(c));

        if (d.value(p) > d.value(c))
            System.out.println("Player Wins!");
        else if (d.value(c) > d.value(p))
            System.out.println("Computer Wins!");
        else
            System.out.println("Tie!");
    }
}
7. Exercise 3 — Dealer

DealerPoker.java

public class DealerPoker {
    public static void main(String[] args) {

        DeckOfCards d = new DeckOfCards();
        d.shuffle();

        Card[] hand = new Card[5];

        for (int i = 0; i < 5; i++)
            hand[i] = d.dealCard();

        String type = d.evaluate(hand);
        System.out.println("Dealer Hand: " + type);

        int n = 0;

        if (type.equals("Pair")) n = 3;
        else if (type.equals("Two Pairs")) n = 1;
        else if (type.equals("Three of a Kind")) n = 2;

        for (int i = 0; i < n; i++)
            hand[i] = d.dealCard();

        System.out.println("\nNew Hand:");

        for (Card c : hand)
            System.out.println(c);

        System.out.println("Result: " + d.evaluate(hand));
    }
}
8. Exercise 4 — Player Chooses Cards

PokerGame.java

import java.util.Scanner;

public class PokerGame {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        DeckOfCards d = new DeckOfCards();
        d.shuffle();

        Card[] player = new Card[5];
        Card[] dealer = new Card[5];

        for (int i = 0; i < 5; i++) {
            player[i] = d.dealCard();
            dealer[i] = d.dealCard();
        }

        System.out.println("Your cards:");

        for (int i = 0; i < 5; i++)
            System.out.println((i + 1) + ". " + player[i]);

        System.out.print("How many cards to replace? ");
        int n = in.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Card number: ");
            int x = in.nextInt();
            player[x - 1] = d.dealCard();
        }

        System.out.println("\nYour hand: " + d.evaluate(player));
        System.out.println("Dealer hand: " + d.evaluate(dealer));

        if (d.value(player) > d.value(dealer))
            System.out.println("YOU WIN!");
        else if (d.value(player) < d.value(dealer))
            System.out.println("DEALER WINS!");
        else
            System.out.println("TIE!");
    }
}
9. Exercise 7.22 — HeartRates
HeartRates.java
import java.time.LocalDate;

public class HeartRates {
    String first, last;
    int month, day, year;

    public HeartRates(String f, String l, int m, int d, int y) {
        first = f;
        last = l;
        month = m;
        day = d;
        year = y;
    }

    public int age() {
        LocalDate t = LocalDate.now();
        int a = t.getYear() - year;

        if (t.getMonthValue() < month ||
           (t.getMonthValue() == month && t.getDayOfMonth() < day))
            a--;

        return a;
    }

    public int maxRate() {
        return 220 - age();
    }

    public double minTarget() {
        return maxRate() * .50;
    }

    public double maxTarget() {
        return maxRate() * .85;
    }
}
HeartRatesTest.java
import java.util.Scanner;

public class HeartRatesTest {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("First name: ");
        String f = in.next();

        System.out.print("Last name: ");
        String l = in.next();

        System.out.print("Birth month: ");
        int m = in.nextInt();

        System.out.print("Birth day: ");
        int d = in.nextInt();

        System.out.print("Birth year: ");
        int y = in.nextInt();

        HeartRates h = new HeartRates(f, l, m, d, y);

        System.out.println("\nName: " + h.first + " " + h.last);
        System.out.println("DOB: " + m + "/" + d + "/" + y);
        System.out.println("Age: " + h.age());
        System.out.println("Maximum Heart Rate: " + h.maxRate());

        System.out.printf("Target Rate: %.0f - %.0f%n",
                h.minTarget(), h.maxTarget());
    }
}