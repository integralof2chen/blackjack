import java.util.ArrayList;

public class player
{
    public card[] hand = new card[5];
    private int total = 0;
    public player(){
    }
    
    public int getTotal(){
        return this.total;
    }
    
    public String getDealer(boolean end){
        if (end)
            return this.toString();
        else
        {
            int count = 2;
            int total = this.hand[0].getVal();
            if (total == 1)
                total = 11;
            String output = "|" + this.hand[0].toString() + "[======]";
            while (this.hand[count] != null && count != 4)
            {
                output += "[======]";
                count++;
            }
            return output + "| total: " + total;
        }
    }
    
    public void hit(ArrayList<card> deck, int round)
    {
        round += 1;
        this.hand[round] = deck.get(0);
        deck.remove(0);
        this.total += this.hand[round].getVal();
        if (this.total > 21)
            while (round >= 0)
            {
                if (this.hand[round].getVal() == 11)
                {
                    this.hand[round].setVal(1);
                    this.total -=10;
                }
                round--;
            }
    }
    
    // the boolean check does not check if the player has blackjack, 
    // it checks if the player is busted
    public boolean check()
    {
        if (this.total > 21){
            return false;
        }    
        else
            return true;
    }
 
    public String toString(){
        String result = "";
        for (int i = 0; i < 5; i ++)
        {
            if (hand[i] != null)
                result += hand[i].toString();
        }
        return "|" + result + "| " + "total: " + this.total;
    }
}
