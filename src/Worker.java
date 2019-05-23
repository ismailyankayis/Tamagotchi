
//command parsing eðer wr ile baþlýyorsa kodlarý kullan
public class Worker {
	
	private int salary;
	private int state;
	private int ID = 0;
	private int food,toy,hygiene;
	private Supplies supplies;
	private int timeMarket;

	public Worker() {
		timeMarket = 0;
		ID = 0;
		salary = 30;
		state = 0; // 1-market 2-catch 3-wrong enrty
		food = 0;
		toy = 0;
		hygiene = 0;
		supplies = null;
	}
	
	public double market(){
		supplies.setFood(supplies.getFood() + food);
		supplies.setToy(supplies.getToy() + toy);
		supplies.setHygiene(supplies.getHygiene() + hygiene);
		food = 0;
		toy = 0;
		hygiene = 0;
		state = 0;
		supplies = null;
		return(food*0.2) + (toy*0.2) + (hygiene*0.1);
		
	}
	public void addSupply(int food,int toy,int hygiene){
		this.food = food;
		this.toy = toy;
		this.hygiene = hygiene;
	}
	
	public String display(){
		if(state == 1) return "Worker "+(ID+1)+" (Market - Food:"+food+", Game:"+toy+", Hygiene:"+hygiene+")";
		else if(state == 2) return "Worker "+(ID+1)+" Catching a child!";
		else return "Worker "+(ID+1)+" is available";
	}
	

	public int getTimeMarket() {
		return timeMarket;
	}

	public void setTimeMarket(int timeMarket) {
		this.timeMarket = timeMarket;
	}

	public Supplies getSupplies() {
		return supplies;
	}

	public void setSupplies(Supplies supplies) {
		this.supplies = supplies;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getToy() {
		return toy;
	}

	public void setToy(int toy) {
		this.toy = toy;
	}

	public int getHygiene() {
		return hygiene;
	}

	public void setHygiene(int hygiene) {
		this.hygiene = hygiene;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	

}
