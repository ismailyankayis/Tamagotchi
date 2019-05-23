

public class Supplies {
	private int food;
	private int toy;
	private int hygiene;

	public Supplies(int food,int toy,int hygiene)
	{
		this.food=food;
		this.toy=toy;
		this.hygiene=hygiene;
	}
	
	

	public int getFood()
	{
		return food;
	}
	public void setFood(int food)
	{
		this.food=food;
	}
	public int getToy()
	{
		return toy;
	}
	public void setToy(int toy)
	{
		this.toy=toy;
	}
	public int getHygiene()
	{
		return hygiene;
	}
	public void setHygiene(int hygiene)
	{
		this.hygiene=hygiene;
	}
	public String printFood()
	{
		return  "Food: "+food;
		
	}
	public String printGame()
	{
		return "Game: "+toy;
		
	}
	public String printHygiene()
	{
		return "Hygiene: "+hygiene;
	}

}
