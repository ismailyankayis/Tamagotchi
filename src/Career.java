
public class Career {

	private int foodRate,toyRate,hygieneRate,sleepRate,salary;
	private int supplies;
	private int state;
	private int ID;
	Child child;
	
	
	
	
	public Career() {
		
		this.foodRate = (int) Math.random() * 12 + 6;;
		this.toyRate = (int) Math.random() * 6 + 3;
		this.hygieneRate = (int) Math.random() * 18 + 8;
		this.sleepRate = (int) Math.random() * 10 + 5;
		this.salary = 50;
		child = null;
		ID = 0;
		
		
		state = 0; // 1-food 2-toy 3-hygiene 4-sleep
		supplies = 0; 
		
	}

	
	public String display(){
		if(child != null){
			if(state == 1){
				return "Career "+(ID+1)+" (Child "+(child.getID()+1)+" Food:"+supplies+")";
			}
			else if(state == 2){
				return "Career "+(ID+1)+" (Child "+(child.getID()+1)+" Toy:"+supplies+")";
			}
			else if(state == 3){
				return "Career "+(ID+1)+" (Child "+(child.getID()+1)+" Hygiene:"+supplies+")";
			}
			else if(state == 4){
				return "Career "+(ID+1)+" (Child "+(child.getID()+1)+" Sleep)";
			}
			else{
				return "Career "+(ID+1)+" is available!";
			}
		}
		else{
			return "Career "+(ID+1)+" is available!";
		}
	}
	public int feed(){
		if(supplies < foodRate){
			int s = supplies;
			supplies = 0;
			state = 0;
			child.setState(0);
			child.setCareer(null);
			child = null;
			return s;
			
		}
		else{
			
			supplies = supplies - foodRate;
			return foodRate;
		}
	}
	
	public int play(){
		if(supplies < toyRate){
			int t = supplies;
			supplies = 0;
			state = 0;
			child.setState(0);
			child.setCareer(null);
			child = null;
			return t;
		}
		else{
			supplies-= toyRate;
			return toyRate;
		}
	}
	
	public int cleaning(){
		if(supplies < hygieneRate){
			int t = supplies;
			supplies = 0;
			state = 0;
			child.setState(0);
			child.setCareer(null);
			child = null;
			return t;
		}
		else{
			supplies-= hygieneRate;
			return hygieneRate;
		}
	}
	public int sleeping(){
		if(supplies == -1){
			return sleepRate;
		}
		else if(supplies < sleepRate){
			int s = supplies;
			supplies = 0;
			state = 0;
			child.setState(0);
			child.setCareer(null);
			child = null;
			return s;
		}
		else{
			supplies-= sleepRate;
			return sleepRate;
		}
	}
	

	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public Child getChild() {
		return child;
	}



	public void setChild(Child child) {
		this.child = child;
	}



	public int getState() {
		return state;
	}



	public void setState(int state) {
		this.state = state;
	}



	public int getFoodRate() {
		return foodRate;
	}

	public void setFoodRate(int foodRate) {
		this.foodRate = foodRate;
	}

	public int getToyRate() {
		return toyRate;
	}

	public void setToyRate(int toyRate) {
		this.toyRate = toyRate;
	}

	public int getHygieneRate() {
		return hygieneRate;
	}

	public void setHygieneRate(int hygieneRate) {
		this.hygieneRate = hygieneRate;
	}

	public int getSleepRate() {
		return sleepRate;
	}

	public void setSleepRate(int sleepRate) {
		this.sleepRate = sleepRate;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}



	public int getSupplies() {
		return supplies;
	}



	public void setSupplies(int supplies) {
		this.supplies = supplies;
	}

	
	

	
	
	
	


	
	
	
	
	
}
