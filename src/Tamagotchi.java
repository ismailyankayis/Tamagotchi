
import enigma.core.Enigma;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Tamagotchi 
{
	public  static enigma.console.Console cn = Enigma.getConsole("Tamagotchi",180,90,true);
	public KeyListener klis; 
	public static int turn=1;
	public static int day=1;
	public static double score;
	public static double credit;
	public String command="";	
	public double avg_happiness=5;
	private Child[] children;
    private int childCount=0;
	private Career[] careers;
	private int careerCount;
	private Worker[] workers;
	private int workerCount;
	public static Worker tempWorker;
	public static Career tempCareer;
	public static Child tempChild;
	public static int timeChild,timeCareer,timeWorker;
	public static int timeMarket;
	public boolean pause;
	
	private Supplies supplies;
    // childapp.carerapp,worker app aç

	public Tamagotchi()
	{		    
		pause = false;
		klis=new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if((e.getKeyCode()>=65 && e.getKeyCode()<=90)||(e.getKeyCode()<=122&&e.getKeyCode()>=97))
				{
					command +=(char)e.getKeyChar();
					cn.getTextWindow().setCursorPosition(1,27);
					cn.getTextWindow().output("Command > "+command);
				}
				else if((e.getKeyCode()<=57 && e.getKeyCode()>=48))
				{
					command += e.getKeyChar();
					cn.getTextWindow().setCursorPosition(1,27);
					cn.getTextWindow().output("Command > "+command);
				}
				else if(e.getKeyCode()==32)//for space
				{
					command +=(char)e.getKeyChar();
					cn.getTextWindow().setCursorPosition(1,27);
					cn.getTextWindow().output("Command > "+command);
				}
				else if(e.getKeyCode() == 27){ //for Escape
					pause = !pause;
				}
				else if(e.getKeyCode()==8)//for backspace key on ascýý character
				{
					if(command.length() > 0){
					command = command.substring(0, command.length() - 1);
					}
					cn.getTextWindow().setCursorPosition(1,27);
					cn.getTextWindow().output("                                            ");
					cn.getTextWindow().setCursorPosition(1,27);
					cn.getTextWindow().output("Command > "+command);
				}
				else if(e.getKeyCode()==10)//for enter
				{

					cn.getTextWindow().setCursorPosition(1,27);	            	
					cn.getTextWindow().output("Command > "+command);
					if(command.length() != 0){
					command();
					}
					cn.getTextWindow().setCursorPosition(1,27);	            	
					cn.getTextWindow().output("Command >                      ");
					
					command="";
				}
			
			}
			public void keyReleased(KeyEvent e) {}
		};
		cn.getTextWindow().addKeyListener(klis);
		
		
		childCount = 1 ;
		workerCount = 1;
		careerCount = 1;
		careers = new Career[100];
		children = new Child[100];
		workers = new Worker[100];
		careers[0] =new Career();
		children[0] = new Child();
		workers[0] = new Worker();
		supplies = new Supplies(100,100,100);
		score = 0;
		credit = 200;
		avg_happiness = 0;
		tempWorker = null;
		tempCareer = null;
		tempChild = null;
		timeChild= 0;
		timeCareer=0;
		timeWorker=0;
	}

	
	
	public void addChild(Child c)
	  {
		  children[childCount]=c;
		  children[childCount].setID(childCount);
		  childCount++;
		  
	  }
	
	
	public void addCareer(Career c){
		careers[careerCount] = c;
		careers[careerCount].setID(careerCount);
		careerCount++;
	}
	
	public void addWorker(Worker w){
		workers[workerCount] = w;
		workers[workerCount].setID(workerCount);
		workerCount++;
	}
	public void command()
	{	
		String[] newCommand = new String[8];
		newCommand = command.split("[ ]");
		
		if(newCommand[0].length()>= 3 && newCommand[0].substring(0, 2).equalsIgnoreCase("ap"))
		{
			int unit = -1;
			try {
				unit = Integer.parseInt(newCommand[0].substring(2));
			} catch (NumberFormatException e) {
				cn.getTextWindow().setCursorPosition(1,28);
				System.out.println("                                           ");
				cn.getTextWindow().setCursorPosition(1,28);
			    System.err.println("NumberFormatException: " + e.getMessage());
			}
			
			
			if(unit == 1 && tempChild == null)
			{
				cn.getTextWindow().setCursorPosition(1,28);
				System.out.println("There is no child application");
			}
			else if(unit == 1 && tempChild != null){
				addChild(tempChild);
				tempChild = null;
				cn.getTextWindow().setCursorPosition(1,28);
				System.out.println("                                            ");
				cn.getTextWindow().setCursorPosition(1,28);
				System.out.println("The Child is accepted");
				cn.getTextWindow().setCursorPosition(1, 4);
				System.out.println("                                                                  ");
			}
			if(unit == 2 && tempCareer == null)
			{
				cn.getTextWindow().setCursorPosition(1,28);
				System.out.println("There is no career application");
			}
			else if( unit == 2 && tempCareer != null){
				addCareer(tempCareer);
				tempCareer = null;
				cn.getTextWindow().setCursorPosition(1,28);
				System.out.println("                                             ");
				cn.getTextWindow().setCursorPosition(1,28);
				System.out.println("The Career is accepted");
				cn.getTextWindow().setCursorPosition(1, 5);
				System.out.println("      ");
			}
			if(unit == 3 && tempWorker == null)
			{
				cn.getTextWindow().setCursorPosition(1,28);
				System.out.println("There is no worker application");
			}
			else if(unit == 3 && tempWorker != null){
				addWorker(tempWorker);
				tempWorker = null;
				cn.getTextWindow().setCursorPosition(1,28);
				System.out.println("                                                ");
				cn.getTextWindow().setCursorPosition(1,28);
				System.out.println("The Worker is accepted");
				cn.getTextWindow().setCursorPosition(1, 6);
				System.out.println("      ");

			}
			
			
		}
		else if(newCommand[0].length() >= 3 && newCommand[0].substring(0,2).equalsIgnoreCase("cr")){
			
			boolean statusCareer = false;
			boolean statusChild = false;
			int careerID = -1;
			try {
				careerID = Integer.parseInt(newCommand[0].substring(2)) - 1;
			} catch (NumberFormatException e) {
				
			}
			// Careerin varlýðýnýn kontrolü
			for (int i = 0; i < careerCount; i++) {
				if(i == careerID && careers[i] != null) statusCareer = true;
			}
			if(statusCareer == false){
				cn.getTextWindow().setCursorPosition(1,28);
				System.out.println("                                           ");
				cn.getTextWindow().setCursorPosition(1,28);
				System.out.println("This Career ID is not existed!");
			}
			
			if(statusCareer && newCommand[1].equalsIgnoreCase("f") && careers[careerID].getState() == 0){ //food
				int x = 0;
				int childID = -1;
				try {
					x = Integer.parseInt(newCommand[3]); // food amount
					childID = Integer.parseInt(newCommand[2].substring(2)) - 1;
				} catch (NumberFormatException e) {
					
				} catch (IndexOutOfBoundsException e){
					
				}
				
				for (int i = 0; i < childCount; i++) { 
					if(i == childID) statusChild = true;
				}
				if(statusChild == false){
					System.out.println("Wrong Command Entry!                            ");
				}
				
				if(statusChild == true && statusCareer == true){
					if(supplies.getFood() < x){
						cn.getTextWindow().setCursorPosition(1,28);
						System.out.println("                                           ");
						cn.getTextWindow().setCursorPosition(1,28);
						System.out.println("There is not enough food!");
					}
					else if(x > 25 && x < 0){
						cn.getTextWindow().setCursorPosition(1,28);
						System.out.println("                                           ");
						cn.getTextWindow().setCursorPosition(1,28);
						System.out.println("The Career cannot carry this food");
					}
					else {
						supplies.setFood(supplies.getFood() - x);
						careers[careerID].setSupplies(x);
						careers[careerID].setState(1);
						children[childID].setState(1);
						careers[careerID].setChild(children[childID]);
						children[childID].setCareer(careers[careerID]);
						
					}
				}
			}
			else if(statusCareer && newCommand[1].equalsIgnoreCase("g") && careers[careerID].getState() == 0){ //game
				
				
				int x = 0; // game amount
				int childID = -1;

				try {
					x = Integer.parseInt(newCommand[3]); // game amount
					childID = Integer.parseInt(newCommand[2].substring(2)) - 1;
				} catch (NumberFormatException e) {
					
				}catch (IndexOutOfBoundsException e){
					
				}
				
				for (int i = 0; i < childCount; i++) { 
					if(i == childID) statusChild = true;
				}
				if(statusChild == false){
					cn.getTextWindow().setCursorPosition(1,28);
					System.out.println("Wrong Command Entry!                           ");
				}
				
				if(statusChild == true && statusCareer == true){
					
					if(supplies.getToy() < x){
						cn.getTextWindow().setCursorPosition(1,28);
						System.out.println("                                           ");
						cn.getTextWindow().setCursorPosition(1,28);
						System.out.println("There is not enough toy!");
					}
					else if(x > 25 &&  x < 0){
						cn.getTextWindow().setCursorPosition(1,28);
						System.out.println("                                           ");
						cn.getTextWindow().setCursorPosition(1,28);
						System.out.println("The Career cannot carry this toy");
					}
					else {
						supplies.setFood(supplies.getToy() - x);
						careers[careerID].setSupplies(x);
						careers[careerID].setState(2);
						children[childID].setState(2);
						careers[careerID].setChild(children[childID]);
						children[childID].setCareer(careers[careerID]);
					}
					
				}
				
			}
			else if(statusCareer && newCommand[1].equalsIgnoreCase("h") && careers[careerID].getState() == 0){ //hygiene
				
				int x = 0; // hygiene amount
				int childID = -1;
				try {
					x = Integer.parseInt(newCommand[3]); // hygiene amount
					childID = Integer.parseInt(newCommand[2].substring(2)) - 1;
				} catch (NumberFormatException e) {
					
				}catch (IndexOutOfBoundsException e){
					
				}
				
				for (int i = 0; i < childCount; i++) { 
					if(i == childID) statusChild = true;
				}
				if(statusChild == false){
					cn.getTextWindow().setCursorPosition(1,28);
					System.out.println("Wrong Command Entry!                               ");
				}
				
				if(statusChild == true && statusCareer == true){
					
					if(supplies.getHygiene() < x){
						cn.getTextWindow().setCursorPosition(1,28);
						System.out.println("                                           ");
						cn.getTextWindow().setCursorPosition(1,28);
						System.out.println("There is not enough hygiene unit!");
					}
					else if(x > 25 && x < 0){
						cn.getTextWindow().setCursorPosition(1,28);
						System.out.println("                                           ");
						cn.getTextWindow().setCursorPosition(1,28);
						System.out.println("The Career cannot carry this hygiene units");
					}
					else {
						supplies.setHygiene(supplies.getHygiene() - x);
						careers[careerID].setSupplies(x);
						careers[careerID].setState(3);
						children[childID].setState(3);
						careers[careerID].setChild(children[childID]);
						children[childID].setCareer(careers[careerID]);
					}
					
				}
			}
			else if(statusCareer && newCommand[1].equalsIgnoreCase("s") && careers[careerID].getState() == 0){ //sleep
				
				int childID =  -1;
				try {
					childID = Integer.parseInt(newCommand[2].substring(2)) - 1;
				} catch (NumberFormatException e) {
					
				}
				
				for (int i = 0; i < childCount; i++) { 
					if(i == childID) statusChild = true;
				}
				if(statusChild == false){
					cn.getTextWindow().setCursorPosition(1,28);
					System.out.println("Wrong Command Entry!                          ");
				}
				
				if(statusChild == true && statusCareer == true){
					
					careers[careerID].setChild(children[childID]);
					careers[careerID].setState(3);
					if(newCommand.length == 3){
						careers[careerID].setSupplies(-1);
						careers[careerID].setState(4);
						children[childID].setState(4);
						careers[careerID].setChild(children[childID]);
						children[childID].setCareer(careers[careerID]);
					}
					else{
						int x = Integer.parseInt(newCommand[3]); 
						careers[careerID].setSupplies(x);
						careers[careerID].setState(4);
						children[childID].setState(4);
						careers[careerID].setChild(children[childID]);
						children[childID].setCareer(careers[careerID]);
					}
					
					
				}
				
			}
			else if(statusCareer &&  newCommand[1].equalsIgnoreCase("c") && careers[careerID].getState() == 0){ //check
				
				int childID = -1;
				try {
					childID = Integer.parseInt(newCommand[2].substring(2)) - 1;
				} catch (NumberFormatException e) {
					
				}
				for (int i = 0; i < childCount; i++) { 
					if(i == childID) statusChild = true;
				}
				if(statusChild == false){
					cn.getTextWindow().setCursorPosition(1,28);
					System.out.println("                                           ");
					cn.getTextWindow().setCursorPosition(1,28);
					System.out.println("This Child ID is not existed!");
				}
				
				if(statusChild == true && statusCareer == true){
					careers[careerID].setState(5);
					children[childID].setState(5);
					careers[careerID].setChild(children[childID]);
					children[childID].setCareer(careers[careerID]);
					children[childID].setTimeCheck(day*50-(50-turn)+2);
				}
				
			}
			else if(statusCareer && newCommand[1].equalsIgnoreCase("r")){ //break the job
				careers[careerID].setState(0);
				careers[careerID].getChild().setState(0);
				
				
			}
			else if(statusCareer && newCommand[1].equalsIgnoreCase("t") && careers[careerID].getState() == 0){
				careers[careerID] = null;
				credit -= 50;
				cn.getTextWindow().setCursorPosition(1,28);
				System.out.println("                                           ");
				cn.getTextWindow().setCursorPosition(1,28);
				System.out.println("The Career has been fired!");
			}
			else if(statusCareer && newCommand[1].equalsIgnoreCase("t") && careers[careerID].getState() != 0){
				cn.getTextWindow().setCursorPosition(1,28);
				System.out.println("                                           ");
				cn.getTextWindow().setCursorPosition(1,28);
				System.out.println("This Career can't be fired now!");
			}
			
		}
		else if(newCommand[0].length() >= 3 && newCommand[0].substring(0,2).equalsIgnoreCase("wr")){
			boolean statusWorker = false;
			int tempSupplies = 0;
			int workerID = -1;
			try {
				workerID = Integer.parseInt(newCommand[0].substring(2)) - 1;
			} catch (NumberFormatException e) {
				
			}
			for (int i = 0; i < workerCount; i++) {
				if(workers[i] != null && i == workerID) statusWorker = true;
				
			}
			
			if(statusWorker && newCommand[1].equalsIgnoreCase("m") && newCommand[2] != null){
				int food = 0;
				int toy = 0;
				int hygiene = 0;
				// FOOD
				if(newCommand[2].substring(0, 1).equalsIgnoreCase("f")){
					try {
						food = Integer.parseInt(newCommand[3]);
					} catch (NumberFormatException e) {
						workers[workerID].setState(3);
					}
				}
				else if(newCommand.length >= 6 && newCommand[4].substring(0, 1).equalsIgnoreCase("f")){
					try {
						food = Integer.parseInt(newCommand[5]);
					} catch (NumberFormatException e) {
						workers[workerID].setState(3);
					}
				}
				else if(newCommand.length >= 8 && newCommand[6].substring(0, 1).equalsIgnoreCase("f")){
					try {
						food = Integer.parseInt(newCommand[7]);
					} catch (NumberFormatException e) {
						workers[workerID].setState(3);
					}
				}
				// GAME
				if(newCommand[2].substring(0, 1).equalsIgnoreCase("g")){
					try {
						toy = Integer.parseInt(newCommand[3]);
					} catch (NumberFormatException e) {
						workers[workerID].setState(3);
					}
				}
				else if(newCommand.length >= 6 && newCommand[4].substring(0, 1).equalsIgnoreCase("g")){
					try {
						toy = Integer.parseInt(newCommand[5]);
					} catch (NumberFormatException e) {
						workers[workerID].setState(3);
					}
				}
				else if(newCommand.length >= 8 && newCommand[6].substring(0, 1).equalsIgnoreCase("g")){
					try {
						toy = Integer.parseInt(newCommand[7]);
					} catch (NumberFormatException e) {
						workers[workerID].setState(3);
					}
				}
				// HYGÝENE
				if(newCommand[2].substring(0, 1).equalsIgnoreCase("h")){
					try {
						hygiene = Integer.parseInt(newCommand[3]);
					} catch (NumberFormatException e) {
						workers[workerID].setState(3);
					}
				}
				else if(newCommand.length >= 6 && newCommand[4].substring(0, 1).equalsIgnoreCase("h")){
					try {
						hygiene = Integer.parseInt(newCommand[5]);
					} catch (NumberFormatException e) {
						workers[workerID].setState(3);
					}
				}
				else if(newCommand.length >= 8 && newCommand[6].substring(0, 1).equalsIgnoreCase("h")){
					try {
						hygiene = Integer.parseInt(newCommand[7]);
					} catch (NumberFormatException e) {
						workers[workerID].setState(3);
					}
				}
				//food
				if(newCommand.length >= 3 && newCommand[2].substring(0, 1).equalsIgnoreCase("f")){
					tempSupplies += food;
				}
				else if(newCommand.length >= 5 && newCommand[4].substring(0, 1).equalsIgnoreCase("f")){
					tempSupplies += food;
				}
				else if(newCommand.length >= 7 && newCommand[6].substring(0, 1).equalsIgnoreCase("f")){
					tempSupplies += food;
				}
				//game
				if(newCommand.length >= 3 && newCommand[2].substring(0, 1).equalsIgnoreCase("g")){
					tempSupplies += toy;
				}
				else if(newCommand.length >= 5 && newCommand[4].substring(0, 1).equalsIgnoreCase("g")){
					tempSupplies += toy;
				}
				else if(newCommand.length >= 7 && newCommand[6].substring(0, 1).equalsIgnoreCase("g")){
					tempSupplies += toy;
				}
				// hygiene
				if(newCommand.length >= 3 && newCommand[2].substring(0, 1).equalsIgnoreCase("h")){
					tempSupplies += hygiene;
				}
				else if(newCommand.length >= 5 && newCommand[4].substring(0, 1).equalsIgnoreCase("h")){
					tempSupplies += hygiene;
				}
				else if(newCommand.length >= 7 && newCommand[6].substring(0, 1).equalsIgnoreCase("h")){
					tempSupplies += hygiene;
				}
				
				if(workers[workerID].getState() == 3){
					cn.getTextWindow().setCursorPosition(1,28);
					System.out.println("Wrong Command Entry!                              ");
				}
				else if(workers[workerID].getState() != 0){
					cn.getTextWindow().setCursorPosition(1,28);
					System.out.println("                                           ");
					cn.getTextWindow().setCursorPosition(1,28);
					System.out.println("The Worker is not here now!");
				}
				else if(tempSupplies > 100){
					cn.getTextWindow().setCursorPosition(1,28);
					System.out.println("                                           ");
					cn.getTextWindow().setCursorPosition(1,28);
					System.out.println("The Worker cannot carry that much supply!");
				}
				else{
					workers[workerID].setTimeMarket(day*50-(50-turn)+10);
					workers[workerID].addSupply(food, toy, hygiene);
					workers[workerID].setSupplies(supplies);
					workers[workerID].setState(1);
				}
				
			}
			else if(statusWorker && newCommand[1].equalsIgnoreCase("c")){
				boolean statusChild = false;
				int childID = -1;
				try {
					childID = Integer.parseInt(newCommand[2].substring(2));
				} catch (NumberFormatException e) {

				}
				for (int i = 0; i < childCount; i++) {
					if(children[i] != null && i == childID) statusChild = true;
				}

				if(statusChild && workers[workerID].getState() == 0 && children[childID].getState() == 6){
					children[childID].setWorker(workers[workerID]);
					children[childID].setState(7);
				}
				else if(statusChild && children[childID].getState() != 6){
					cn.getTextWindow().setCursorPosition(1,28);
					System.out.println("The Child is no missing!                              ");
				}
			}
			else if(statusWorker && newCommand[1].equalsIgnoreCase("t")){
				
				if(workers[workerID].getState() == 0){
					workers[workerID] = null;
					credit -= 50;
					cn.getTextWindow().setCursorPosition(1,28);
					System.out.println("                                           ");
					cn.getTextWindow().setCursorPosition(1,28);
					System.out.println("The Worker has been fired!");
				}
				else{
					cn.getTextWindow().setCursorPosition(1,28);
					System.out.println("                                           ");
					cn.getTextWindow().setCursorPosition(1,28);
					System.out.println("This Worker can't be fired now!");
				}
			}
			else {
				cn.getTextWindow().setCursorPosition(1,28);
				System.out.println("Wrong Command Entry!                              ");
			}
			
			
		}
		
		else {
			cn.getTextWindow().setCursorPosition(1,28);
			System.out.println("Wrong Command Entry!                                ");
		}
	}

	public void display()
	{
		enigma.console.Console cn=Enigma.getConsole("Tamagotchi",180,90,false);
		
		Timer myTimer=new Timer();
		TimerTask gorev=new TimerTask() {		
			public void run() {
				for (int i = 0; i < 27; i++) {
					for (int j = 0; j < 80; j++) {
						cn.getTextWindow().setCursorPosition(0+j, 0+i);
						System.out.print(" ");
					}
				}
				while(pause){
					cn.getTextWindow().setCursorPosition(1, 1);
					cn.getTextWindow().output("Day:"+day);		
					cn.getTextWindow().setCursorPosition(10, 1);
					cn.getTextWindow().output("Turn:"+turn);
					cn.getTextWindow().setCursorPosition(19, 1);
					cn.getTextWindow().output("Current Avg. Happinness: "+avg_happiness);
					cn.getTextWindow().setCursorPosition(50, 1);
					cn.getTextWindow().output("Credit: " + credit);
					cn.getTextWindow().setCursorPosition(67, 1);
					cn.getTextWindow().output("Score: " + score);
					cn.getTextWindow().setCursorPosition(1, 7);
					cn.getTextWindow().setCursorPosition(67, 3);
					cn.getTextWindow().output("--Supplies--");
					cn.getTextWindow().setCursorPosition(67, 4);
					cn.getTextWindow().output(supplies.printFood());
					cn.getTextWindow().setCursorPosition(67, 5);
					cn.getTextWindow().output(supplies.printGame());
					cn.getTextWindow().setCursorPosition(67, 6);
					cn.getTextWindow().output(supplies.printHygiene());
					cn.getTextWindow().output("---Children---");
					int c = 0;
					for (int i = 0; i < childCount; i++) {
						if(children[i] != null){
							cn.getTextWindow().setCursorPosition(1, 8+c);
							cn.getTextWindow().output(children[i].pauseDisplay());
							c++;
						}
					}
				}
				
				cn.getTextWindow().setCursorPosition(1, 1);
				cn.getTextWindow().output("Day:"+day);		
				cn.getTextWindow().setCursorPosition(10, 1);
				cn.getTextWindow().output("Turn:"+turn);
				cn.getTextWindow().setCursorPosition(19, 1);
				cn.getTextWindow().output("Current Avg. Happinness: "+avg_happiness);
				cn.getTextWindow().setCursorPosition(50, 1);
				cn.getTextWindow().output("Credit: " + credit);
				cn.getTextWindow().setCursorPosition(67, 1);
				cn.getTextWindow().output("Score: " + score);
				cn.getTextWindow().setCursorPosition(1, 3);
				cn.getTextWindow().output("---Application---");
				cn.getTextWindow().setCursorPosition(1, 7);
				cn.getTextWindow().output("---Children---");
				int c = 0;
				for (int i = 0; i < childCount; i++) {
					if(children[i] != null){
						if(children[i].getState() == 5){
							if(children[i].getTimeCheck() >= turn){
							cn.getTextWindow().setCursorPosition(1+c, 8);
							cn.getTextWindow().output(children[i].display());

							cn.getTextWindow().setCursorPosition(1+c, 9);
							children[i].displayCheck();
							c+=8;
							}
						}
						else{
							cn.getTextWindow().setCursorPosition(1+c, 8);
							cn.getTextWindow().output(children[i].display());
							c+=8;
						}
					}
				}
				cn.getTextWindow().setCursorPosition(1, 14);		
				cn.getTextWindow().output("---Carers---");
				cn.getTextWindow().setCursorPosition(1, 15);
				for (int i = 0; i < careerCount; i++) {
					cn.getTextWindow().setCursorPosition(1, 15+i);
					cn.getTextWindow().output("                          ");
					if(careers[i] != null){
					cn.getTextWindow().setCursorPosition(1, 15+i);
					cn.getTextWindow().output(careers[i].display());
					}
				}
				cn.getTextWindow().setCursorPosition(1, 22);
				cn.getTextWindow().output("---Workers---");
				for (int j = 0; j < workerCount; j++) {
					cn.getTextWindow().setCursorPosition(1, 23+j);
					cn.getTextWindow().output("                          ");
					if(workers[j] != null){
						cn.getTextWindow().setCursorPosition(1, 23+j);
						cn.getTextWindow().output(workers[j].display());
					}
				}
				cn.getTextWindow().setCursorPosition(67, 3);
				cn.getTextWindow().output("--Supplies--");
				cn.getTextWindow().setCursorPosition(67, 4);
				cn.getTextWindow().output(supplies.printFood());
				cn.getTextWindow().setCursorPosition(67, 5);
				cn.getTextWindow().output(supplies.printGame());
				cn.getTextWindow().setCursorPosition(67, 6);
				cn.getTextWindow().output(supplies.printHygiene());
				cn.getTextWindow().setCursorPosition(5,1);	
				cn.getTextWindow().setCursorPosition(15,1);
				cn.getTextWindow().setCursorPosition(1,27);	            	
				cn.getTextWindow().output("Command >                                            ");
				cn.getTextWindow().setCursorPosition(1,27);	            	
				cn.getTextWindow().output("Command > "+command);
				turn++;
				if(turn<10)
				{
					cn.getTextWindow().setCursorPosition(16,1);
					System.out.print(" ");
				}
				if(turn==50)
				{
					turn=0;
					day++;
					cn.getTextWindow().setCursorPosition(5,1);
					score = calculateScore();
					credit = calculateCredit();
				}
				
				avg_happiness = calculateAvarageHappines();
				for (int i = 0; i < workerCount; i++) {
					if(workers[i] != null){
						if(workers[i].getState() == 1){
							if(workers[i].getTimeMarket() <= turn){
								credit -= workers[i].market();
							}
						}
					}
				}
				for (int i = 0; i < childCount; i++) {
					if(children[i] != null){
						
						if(children[i].getState() != 6 && children[i].getState() != 7){
							children[i].decreasingHappines();
							children[i].decreasingRates();
						}

						if(children[i].getState() == 0){
							if(children[i].missingProbability() == 1){
								children[i].setState(6);
							}
							else if(children[i].missingProbability() == -1){
								children[i] = null;
								credit -= 50;
							}
						}
						else if(children[i].getState() == 1){ //Food
							children[i].eat(children[i].getCareer().feed());
						}
						else if(children[i].getState() == 2) { //Toy
							children[i].play(children[i].getCareer().play());
						}
						else if(children[i].getState() == 3) { //Hygiene
							children[i].cleaning(children[i].getCareer().cleaning());
						}
						else if(children[i].getState() == 4) { //Sleep
							children[i].sleeping(children[i].getCareer().sleeping());
						}
						children[i].missing();
					}
				}
				createApplication();	
				if(timeCareer == day*50-(50-turn)){
					tempCareer = null;
					cn.getTextWindow().setCursorPosition(1, 5);
					System.out.println("      ");
				}
				else if(tempCareer != null){
					cn.getTextWindow().setCursorPosition(1, 5);
					System.out.println("Career");
				}
				if(timeWorker == day*50-(50-turn)){
					tempWorker = null;
					cn.getTextWindow().setCursorPosition(1, 6);
					System.out.println("      ");
				}
				else if(tempWorker != null){
					cn.getTextWindow().setCursorPosition(1, 6);
					System.out.println("Worker");
				}
				if(timeChild == day*50-(50-turn)){
					tempChild = null;
					cn.getTextWindow().setCursorPosition(1, 4);
					for (int i = 0; i < 66; i++) {
						System.out.print(" ");
					}
				}
				else if(tempChild != null){
					cn.getTextWindow().setCursorPosition(1, 4);
					System.out.println("Child - F: " + tempChild.getDecreasingfood() + "  " + " G: "
							+ tempChild.getDecreasingtoyGame() + "  " + " S: " + tempChild.getDecreasingSleep()
							+ " H :" + tempChild.getDecreasingHygiene() + "  " + "Happiness : "
							+ tempChild.getHappiness());
				}
				
				//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
			}

			

			public double calculateScore(){
				for (int i = 0; i < childCount; i++) {
					if(children[i] != null){
						score = score + avg_happiness - 50;
					}
				}
				return score;
				
			}
			public double calculateCredit(){
				for (int i = 0; i < childCount; i++) {
					if(children[i] != null)
					credit = credit + children[i].calculateCredit();
				}
				
				for (int i = 0; i < careerCount; i++) {
					if(careers[i] != null){
						credit = credit - 50;
					}
				}
				return (double)Math.round((double )(credit)*100)/100;
			}
			public double calculateAvarageHappines(){
				double sum = 0;
				int count = 0;
				for (int i = 0; i < childCount; i++) {
					if(children[i] != null)
					{
					sum += children[i].getHappiness();
					count++;
					}
				}
				return (double)Math.round((double )(sum/count)*100)/100;
			}
			
			private void createApplication() {
				if(tempCareer == null)
				{
					int randomForCareer = (int )(Math.random() * 5 + 1);
					if(randomForCareer == 1){
						timeCareer = day*50-(50-turn)+10;
					tempCareer = new Career();
					cn.getTextWindow().setCursorPosition(1, 5);
					System.out.println("Career");
					}
				}
				
				int randomForWorker = (int )(Math.random() * 5 + 1);
				if(randomForWorker==1 && tempWorker == null)
				{
					timeWorker = day*50-(50-turn)+10;
					tempWorker = new Worker();
					cn.getTextWindow().setCursorPosition(1, 6);
					System.out.println("Worker");
				}
			
			
				if(tempChild == null){
					int randomForChildren = (int) (Math.random() * 5 + 1);
				if (avg_happiness < 65)
				{
					
					if (randomForChildren == 1) {
						timeChild = day*50-(50-turn)+10;
						Child ch = new Child();
						tempChild = ch;
						cn.getTextWindow().setCursorPosition(1, 4);
						System.out.println("Child - F: " + tempChild.getDecreasingfood() + "  " + " G: "
								+ tempChild.getDecreasingtoyGame() + "  " + " S: " + tempChild.getDecreasingSleep()
								+ " H :" + tempChild.getDecreasingHygiene() + "  " + "Happiness : "
								+ tempChild.getHappiness());
					}
				}

				else if (avg_happiness > 65)
				{
					if (randomForChildren == 1 || randomForChildren == 2) {
						timeChild = day*50-(50-turn)+10;
						Child ch = new Child();
						tempChild = ch;
						cn.getTextWindow().setCursorPosition(1, 4);
						System.out.println("Child - F : " + tempChild.getDecreasingfood() + "  " + " G : "
								+ tempChild.getDecreasingtoyGame() + "  " + " S : " + tempChild.getDecreasingSleep()
								+ " H : " + tempChild.getDecreasingHygiene() + "  " + "Happiness : "
								+ tempChild.getHappiness());
					}
				}
			}
			}
		};
		myTimer.schedule(gorev,0,2000);

	}
}
