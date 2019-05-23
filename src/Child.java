import java.util.Random;
public class Child {
	Random randomNum=new Random();
	Career object_career;

	

            private double happiness;
		    private double food;
		    private double sleep;
		    private double toyGame;
		    private double hygiene;
		    private int missing;
		    private double Decreasingfood;
		    private double DecreasingtoyGame;
		    private double DecreasingHygiene;
		    private double DecreasingSleep;
		    private int state;
		    private Career career;
		    private Worker worker;
		    private int ID;
		    private int timeCheck;
		    public Child()
		    {
		    	timeCheck = 0;
		    	missing = 0;
		    	ID = 0;
		    	state = 0; // 1-food 2-game 3-hygiene 4-sleep  5-check 6-missing 7-catching
		    	career = null;
				food=randomNum.nextInt(15) + 45;
		    	toyGame=randomNum.nextInt(15) + 45;
		    	sleep=randomNum.nextInt(15) +45;
		    	hygiene=randomNum.nextInt(15) +45;
		    	happiness=randomNum.nextInt(15)+45;
		    	Decreasingfood=(double)Math.round((double )(Math.random() * 1.01 +0.50)*100)/100;
		    	DecreasingtoyGame=(double)Math.round((double )(Math.random() * 1.01 + 0.50)*100)/100;
				DecreasingSleep=(double)Math.round((double )(Math.random() * 0.51 + 0.25)*100)/100;
				DecreasingHygiene= (double)Math.round((double )(Math.random() * 0.31 + 0.20)*100)/100;

			
		    }
		    
		    public int missingProbability(){
		    	if(happiness >= 10 && happiness < 25){
		    	int x = randomNum.nextInt(5);
		    	return x ;
		    	}
		    	else if(happiness < 10) return -1;
		    	else return -2;
		    	
		    }
		    public void missing(){
		    	if(state == 6) missing++;
		    	else if(state == 7){
		    		missing--;
		    		if(missing == 0){
		    			state = 0;
		    			worker.setState(0);
		    			worker = null;
		    		}
		    	}
		    }
		    
		    public String display(){
		    	return "Child"+(ID+1)+"  ";
		    }
		    public void displayCheck(){
		    	
		    	System.out.println("F:"+(double)Math.round((double )(food)*100)/100);
		    	System.out.println(" T:"+(double)Math.round((double )(toyGame)*100)/100);
		    	System.out.println(" Hy:"+(double)Math.round((double )(hygiene)*100)/100);
		    	System.out.println(" S:"+(double)Math.round((double )(sleep)*100)/100);
		    	System.out.println(" Hap:"+(double)Math.round((double )(happiness)*100)/100);
		    	career.setState(0);
		    	state = 0;
		    	career.setChild(null);
		    	career = null;
		    }
		    public String pauseDisplay(){
		    	if(state == 6){
		    	return "Child "+(ID+1)+" F: "+(double)Math.round((double )(food)*100)/100+" T: "+(double)Math.round((double )(toyGame)*100)/100+
		    	" Hy: "+(double)Math.round((double )(hygiene)*100)/100
		    	+" S: "+(double)Math.round((double )(sleep)*100)/100+" Hap:"+(double)Math.round((double )(happiness)*100)/100+" Missing:"+missing;
		    	}
		    	else{
		    	return "Child "+(ID+1)+" F: "+(double)Math.round((double )(food)*100)/100+" T: "+(double)Math.round((double )(toyGame)*100)/100+
		       	" Hy: "+(double)Math.round((double )(hygiene)*100)/100
		       	+" Sl: "+(double)Math.round((double )(sleep)*100)/100+" Hap:"+(double)Math.round((double )(happiness)*100)/100;
		    	}
		    }
		    public void eat(int foodRate){
		    	food = food + foodRate;
		    }
		    public void play(int toyRate){
		    	toyGame = toyGame + toyRate;
		    }
		    public void cleaning(int hygieneRate){
		    	hygiene = hygiene + hygieneRate;
		    }
		    public void sleeping(int sleepRate){
		    	sleep = sleep + sleepRate;
		    }
		    public double calculateCredit(){
		    	return happiness * (1 + ((happiness-50)/50));
		    }
		    public void decreasingRates(){
		    	food -= Decreasingfood;
		    	toyGame -= DecreasingtoyGame;
		    	hygiene -= DecreasingHygiene;
		    	sleep -= DecreasingSleep;
		    }
		    public void decreasingHappines(){
		    	if(food < 25){
		    		happiness -= 4*0.24;
		    	}
		    	else if(food > 75){
		    		happiness -= 2*0.24;
		    	}
		    	else{
		    		happiness += 0.24;
		    	}
		    	if(toyGame < 25){
		    		happiness -= 4*0.12;
		    	}
		    	else if(toyGame > 75){
		    		happiness -= 2*0.12;
		    	}
		    	else{
		    		happiness += 0.12;
		    	}
		    	if(hygiene < 25){
		    		happiness -= 4*0.06;
		    	}
		    	else if(hygiene > 75){
		    		happiness -= 2*0.06;
		    	}
		    	else{
		    		happiness += 0.06;
		    	}
		    	if(sleep < 25){
		    		happiness -= 4*0.08;
		    	}
		    	else if(sleep > 75){
		    		happiness -= 2*0.08;
		    	}
		    	else{
		    		happiness += 0.08;
		    	}
		    }
		    
		   public int getTimeCheck() {
				return timeCheck;
			}

			public void setTimeCheck(int timeCheck) {
				this.timeCheck = timeCheck;
			}

		public Career getCareer() {
				return career;
			}

			public void setCareer(Career career) {
				this.career = career;
			}

		
			public Worker getWorker() {
				return worker;
			}

			public void setWorker(Worker worker) {
				this.worker = worker;
			}

			public int getState() {
				return state;
			}

			public void setState(int state) {
				this.state = state;
			}

			public double getDecreasingfood() {
				return Decreasingfood;
			}
			public void setDecreasingfood(double decreasingfood) {
				Decreasingfood = decreasingfood;
			}
			public double getDecreasingtoyGame() {
				return DecreasingtoyGame;
			}
			public void setDecreasingtoyGame(double decreasingtoyGame) {
				DecreasingtoyGame = decreasingtoyGame;
			}
			public double getDecreasingHygiene() {
				return DecreasingHygiene;
			}
			public void setDecreasingHygiene(double decreasingHygiene) {
				DecreasingHygiene = decreasingHygiene;
			}
			public double getDecreasingSleep() {
				return DecreasingSleep;
			}
			public void setDecreasingSleep(double decreasingSleep) {
				DecreasingSleep = decreasingSleep;
			}
		
			public double getHappiness() {
				return happiness;
			}

			public void setHappiness(double happiness) {
				this.happiness = happiness;
			}

			public double getFood() {
				return food;
			}

			public void setFood(double food) {
				this.food = food;
			}

			public double getSleep() {
				return sleep;
			}

			public void setSleep(double sleep) {
				this.sleep = sleep;
			}

			public double getToyGame() {
				return toyGame;
			}

			public void setToyGame(double toyGame) {
				this.toyGame = toyGame;
			}

			public double getHygiene() {
				return hygiene;
			}

			public void setHygiene(double hygiene) {
				this.hygiene = hygiene;
			}

			public int getMissing() {
				return missing;
			}
			public void setMissing(int missing) {
				this.missing = missing;
			}

			public int getID() {
				return ID;
			}

			public void setID(int iD) {
				ID = iD;
			}

}
