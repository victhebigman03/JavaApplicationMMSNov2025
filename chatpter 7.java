public class Card {
    private String face, suit;

    public Card(String face, String suit) {
        this.face = face;
        this.suit = suit;
    }

    public String getFace() { return face; }
    public String getSuit() { return suit; }

    public String toString() {
        return face + " of " + suit;
    }
}

  import java.util.Random;

public class DeckOfCards {
    private Card[] deck = new Card[52];
    private int currentCard = 0;

    private String[] faces = {
        "Ace","2","3","4","5","6","7",
        "8","9","10","Jack","Queen","King"
    };

    private String[] suits = {
        "Hearts","Diamonds","Clubs","Spades"
    };

    public DeckOfCards() {
        for (int i = 0; i < 52; i++)
            deck[i] = new Card(faces[i % 13], suits[i / 13]);
    }

    public void shuffle() {
        Random r = new Random();
        for (int i = 51; i > 0; i--) {
            int j = r.nextInt(i + 1);
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
        currentCard = 0;
    }

    public Card dealCard() {
        return currentCard < 52 ? deck[currentCard++] : null;
    }

    public boolean pair(Card[] h) {
        return count(h, 2) >= 1;
    }

    public boolean twoPairs(Card[] h) {
        return count(h, 2) == 2;
    }

    public boolean three(Card[] h) {
        return count(h, 3) >= 1;
    }

    public boolean four(Card[] h) {
        return count(h, 4) >= 1;
    }

    public boolean flush(Card[] h) {
        for (int i = 1; i < 5; i++)
            if (!h[i].getSuit().equals(h[0].getSuit())) return false;
        return true;
    }

    public boolean straight(Card[] h) {
        int[] n = new int[5];

        for (int i = 0; i < 5; i++)
            n[i] = value(h[i].getFace());

        for (int i = 0; i < 4; i++)
            for (int j = i + 1; j < 5; j++)
                if (n[i] > n[j]) {
                    int t = n[i]; n[i] = n[j]; n[j] = t;
                }

        for (int i = 0; i < 4; i++)
            if (n[i] + 1 != n[i + 1]) return false;

        return true;
    }

    public boolean fullHouse(Card[] h) {
        return pair(h) && three(h);
    }

    private int count(Card[] h, int x) {
        int[] c = new int[13];

        for (Card card : h)
            c[value(card.getFace()) - 1]++;

        int total = 0;

        for (int n : c)
            if (n == x) total++;

        return total;
    }

    private int value(String f) {
        String[] v = {
            "Ace","2","3","4","5","6","7",
            "8","9","10","Jack","Queen","King"
        };

        for (int i = 0; i < v.length; i++)
            if (f.equals(v[i])) return i + 1;

        return 0;
    }
}  
   
   // public class PokerHand {
    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        Card[] hand = new Card[5];

        for (int i = 0; i < 5; i++) {
            hand[i] = deck.dealCard();
            System.out.println(hand[i]);
        }

        if (deck.four(hand))
            System.out.println("Four of a kind");
        else if (deck.fullHouse(hand))
            System.out.println("Full house");
        else if (deck.flush(hand))
            System.out.println("Flush");
        else if (deck.straight(hand))
            System.out.println("Straight");
        else if (deck.three(hand))
            System.out.println("Three of a kind");
        else if (deck.twoPairs(hand))
            System.out.println("Two pairs");
        else if (deck.pair(hand))
            System.out.println("Pair");
        else
            System.out.println("High card");
    }
}   
   
   
   // public class TwoPokerHands {
    public static void main(String[] args) {
        DeckOfCards d = new DeckOfCards();
        d.shuffle();

        Card[] a = new Card[5];
        Card[] b = new Card[5];

        for (int i = 0; i < 5; i++) {
            a[i] = d.dealCard();
            b[i] = d.dealCard();
        }

        System.out.println("Player 1:");
        for (Card c : a) System.out.println(c);

        System.out.println("\nPlayer 2:");
        for (Card c : b) System.out.println(c);

        int x = score(d, a);
        int y = score(d, b);

        System.out.println("\nPlayer 1 score: " + x);
        System.out.println("Player 2 score: " + y);

        System.out.println(x > y ? "Player 1 wins!" :
                           x < y ? "Player 2 wins!" : "Tie!");
    }

    static int score(DeckOfCards d, Card[] h) {
        if (d.four(h)) return 7;
        if (d.fullHouse(h)) return 6;
        if (d.flush(h)) return 5;
        if (d.straight(h)) return 4;
        if (d.three(h)) return 3;
        if (d.twoPairs(h)) return 2;
        if (d.pair(h)) return 1;
        return 0;
    }
}  
   
   
   //public class PokerDealer {
    public static void main(String[] args) {
        DeckOfCards d = new DeckOfCards();
        d.shuffle();

        Card[] hand = new Card[5];

        for (int i = 0; i < 5; i++)
            hand[i] = d.dealCard();

        System.out.println("Dealer's hand:");
        for (Card c : hand)
            System.out.println(c);

        int replace = 3;

        if (d.four(hand) || d.fullHouse(hand) ||
            d.flush(hand) || d.straight(hand))
            replace = 0;
        else if (d.three(hand))
            replace = 2;
        else if (d.twoPairs(hand))
            replace = 1;

        for (int i = 0; i < replace; i++)
            hand[i] = d.dealCard();

        System.out.println("\nNew hand:");
        for (Card c : hand)
            System.out.println(c);
    }
}

   //import java.util.Scanner;

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

        System.out.println("Your hand:");
        for (int i = 0; i < 5; i++)
            System.out.println((i + 1) + ". " + player[i]);

        System.out.print("\nEnter card numbers to replace: ");
        String[] choice = in.nextLine().split(" ");

        for (String s : choice) {
            if (!s.equals("0")) {
                int n = Integer.parseInt(s) - 1;
                if (n >= 0 && n < 5)
                    player[n] = d.dealCard();
            }
        }

        int p = TwoPokerHands.score(d, player);
        int c = TwoPokerHands.score(d, dealer);

        System.out.println("\nYour score: " + p);
        System.out.println("Dealer score: " + c);

        System.out.println(p > c ? "YOU WIN!" :
                           p < c ? "DEALER WINS!" : "TIE!");

        in.close();
    }
}

    //public enum Face {
    ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN,
    EIGHT, NINE, TEN, JACK, QUEEN, KING
}

Suit.java

public enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}

Card.java

public class Card {
    private Face face;
    private Suit suit;

    public Card(Face face, Suit suit) {
        this.face = face;
        this.suit = suit;
    }

    public Face getFace() { return face; }
    public Suit getSuit() { return suit; }

    public String toString() {
        return face + " of " + suit;
    }
}

DeckOfCards.java

import java.util.Random;

public class DeckOfCards {
    private Card[] deck = new Card[52];
    private int currentCard;

    private Face[] faces = Face.values();
    private Suit[] suits = Suit.values();

    public DeckOfCards() {
        for (int i = 0; i < 52; i++)
            deck[i] = new Card(faces[i % 13], suits[i / 13]);
    }

    public void shuffle() {
        Random r = new Random();

        for (int i = 51; i > 0; i--) {
            int j = r.nextInt(i + 1);
            Card t = deck[i];
            deck[i] = deck[j];
            deck[j] = t;
        }

        currentCard = 0;
    }

    public Card dealCard() {
        return currentCard < 52 ? deck[currentCard++] : null;
    }
}
7.21 — Fisher-Yates

The short version of the shuffle() method is:

public void shuffle() {
    Random r = new Random();

    for (int i = 51; i > 0; i--) {
        int j = r.nextInt(i + 1);

        Card temp = deck[i];
        deck[i] = deck[j];
        deck[j] = temp;
    }

    currentCard = 0;
}
7.22 — Heart Rates

HeartRates.java

import java.time.LocalDate;
import java.time.Period;

public class HeartRates {
    private String firstName, lastName;
    private int month, day, year;

    public HeartRates(String f, String l, int m, int d, int y) {
        firstName = f;
        lastName = l;
        month = m;
        day = d;
        year = y;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getMonth() { return month; }
    public int getDay() { return day; }
    public int getYear() { return year; }

    public int getAge() {
        LocalDate birth = LocalDate.of(year, month, day);
        return Period.between(birth, LocalDate.now()).getYears();
    }

    public int getMaximumHeartRate() {
        return 220 - getAge();
    }

    public String getTargetHeartRate() {
        double min = getMaximumHeartRate() * .50;
        double max = getMaximumHeartRate() * .85;
        return String.format("%.0f - %.0f bpm", min, max);
    }
}

HeartRatesTest.java

//import java.util.Scanner;

public class HeartRatesTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("First name: ");
        String f = in.nextLine();

        System.out.print("Last name: ");
        String l = in.nextLine();

        System.out.print("Birth month: ");
        int m = in.nextInt();

        System.out.print("Birth day: ");
        int d = in.nextInt();

        System.out.print("Birth year: ");
        int y = in.nextInt();

        HeartRates h = new HeartRates(f, l, m, d, y);

        System.out.println("\nName: " +
                h.getFirstName() + " " + h.getLastName());

        System.out.println("DOB: " +
                h.getMonth() + "/" + h.getDay() + "/" + h.getYear());

        System.out.println("Age: " + h.getAge());
        System.out.println("Maximum HR: " +
                h.getMaximumHeartRate());

        System.out.println("Target HR: " +
                h.getTargetHeartRate());

        in.close();
    }
}