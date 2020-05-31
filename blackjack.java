import java.util.ArrayList;
import java.util.Scanner;

public class blackjack
{

    public static void main(String args[])
    {

        ArrayList<card> deck = new ArrayList<card>();
        ArrayList<card> shuffledDeck = new ArrayList<card>();

        player you = new player(); //the user
        player dealer = new player(); //the "AI"

        // create a new blank deck
        for (int i = 0; i < 52; i++)
            deck.add(new card());

        // filling the deck
        int count = -1;
        for (int i = 1; i <= 4; i++)
        {
            for (int x = 2; x <= 14; x++)
            {
                count++;
                if (i == 1)
                    deck.get(count).setSuit("spade");
                else if (i == 2)
                    deck.get(count).setSuit("heart");
                else if (i == 3)
                    deck.get(count).setSuit("diamond");
                else if (i == 4)
                    deck.get(count).setSuit("clubs");

                if (x >= 11)
                {
                    if (x == 14)
                    {
                        deck.get(count).setVal(11);
                        deck.get(count).setName("A");
                    }
                    else
                    {
                        deck.get(count).setVal(10);
                        if (x == 11)
                            deck.get(count).setName("J");
                        else if (x == 12)
                            deck.get(count).setName("Q");
                        else if (x == 13)
                            deck.get(count).setName("K");
                    }
                }
                else
                    deck.get(count).setVal(x);
            }
        }

        // deck initialization test
        /*for (int i = 0; i < 52; i++)
        {
        System.out.println(deck.get(i));
        }*/

        // shuffle
        for (int i = 0; i < 52; i++)
        {
            int rand = (int)(Math.random() * deck.size());
            shuffledDeck.add(deck.get(rand));
            deck.remove(rand);
        }

        // shuffled deck test
        /*for (int i = 0; i < 52; i++)
        {
        System.out.println(shuffledDeck.get(i));
        }*/

        //first two
        int round = 1; 
        Scanner scan = new Scanner(System.in);
        you.hit(shuffledDeck, -1);
        you.hit(shuffledDeck, 0);
        dealer.hit(shuffledDeck, -1);
        dealer.hit(shuffledDeck, 0);
        if (you.getTotal() == 21)
        {
            winCheck(you, dealer, round);
        }
        boolean hit = true;
        while (you.check() && dealer.check() && round <= 3 && you.getTotal() != 21 
        && dealer.getTotal() != 21)
        {
            System.out.println("|round " + round + "|");
            printBoth(you, dealer, false);
            if (hit)
            {
                System.out.println("stand or hit");
                String answer = scan.nextLine();
                if (answer.equals("stand"))
                    hit = false;
                else
                {
                    you.hit(shuffledDeck, round);
                    if (you.getTotal() > 21)
                        break;
                }
            }
            if (dealer.getTotal() < 17)
                dealer.hit(shuffledDeck, round);
            if (dealer.getTotal() > 21)
                break;
            if (dealer.getTotal() >= 17 && hit == false)
                break;
            round++;
            System.out.println("");
            System.out.println("");
        }
        winCheck(you, dealer, round);
    }
        
    public static void printBoth(player a, player b, boolean end)
        {
        System.out.print("your hand:");
        System.out.println(a);
        System.out.print("dealer's hand");
        System.out.println(b.getDealer(end));
        }

    public static void winCheck(player you, player dealer, int round)
    {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        if (round == 5 && you.getTotal() < 21)
            {
               System.out.println("you won because you reached five rounds without going over 21");
            }
        if (you.getTotal() == 21)
        {
            if (dealer.getTotal() != 21)
                System.out.println("blackjack --> you won.");
            else if (dealer.getTotal() == 21)
                System.out.println("blackjack --> push.");
            printBoth(you, dealer, true);
        }
        else if (you.getTotal() < 21)
        {
            if (dealer.getTotal() > you.getTotal())
            {
                if (dealer.getTotal() > 21)
                {
                    System.out.println("you won");
                    printBoth(you, dealer, true);
                }
                else
                {
                    System.out.println("you lost");
                    printBoth(you, dealer, true);
                }
            }
            else if (dealer.getTotal() < you.getTotal())
            {
                System.out.println("you won");
                printBoth(you, dealer, true);
            }
            else
            {
                System.out.println("push");
                printBoth(you, dealer, true);
            }
        }
        else
            {
                System.out.println("busted");
                printBoth(you, dealer, true);
            }
        System.out.println(you.getTotal());
        System.out.println(dealer.getTotal());
    }
}
