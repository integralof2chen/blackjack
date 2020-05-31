public class card{
	private String suit;
	private String name;
	private int value;
	
	public card(){	
	}
	
	public int getVal(){
		return this.value;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getSuit(){
		return this.suit;
	}
	
	public void setVal(int value1){
		this.value = value1;
	}
	
	public void setName(String name1){
		this.name = name1;
	}

	public void setSuit(String suit1){
		this.suit = suit1;
	}
	
	public String toString()
	{
	    if (this.name != null)
	       return "[" + this.name + " of " + this.suit + "]";
	    else
	       return "[" + this.value + " of " + this.suit + "]";
	}
	// new toString
}
