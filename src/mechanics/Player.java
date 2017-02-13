package mechanics;
import  java.util.Scanner;

public class Player {
	//Used to determine the profession of the player
    public enum profession{
    	alchemist,
    	barbarian,
    	drunkard,
    	knight,
    	priest,
    	ranger,
    	rogue,
    	scribe,
    	civilian
    }
	
	 	//Base stats for player
		protected String name;
		protected profession job;
	    protected int health 
	    ,maxHealth
	    ,attack
	    ,defence
	    ,initiative
	    ,initMod;
	    protected boolean alive
	    ,isDefending;
	    
	    //Skill Checks
	    //to be added soon

	    public Player(){
	    	name = "Placeholder";
	    	job = profession.civilian;
	    	health = 8;
	    	maxHealth = 8;
	    	attack = 0;
	    	defence = 0;
	    	initiative = 0;
	    	initMod = 0;
	    	alive = true;
	    	isDefending = false;
	    }
	    
	    public Player(String nam, int HP, int ATK, int DEF, int INIT){
	    	name = nam;
	    	job = profession.civilian;
	    	health = HP;
	    	maxHealth = HP;
	    	attack = ATK;
	    	defence = DEF;
	    	initiative = 0;
	    	initMod = INIT;
	    	alive = true;
	    	isDefending = false;
	    }
	    
	    
	    //Prints the summary of a players stats
	    public void statSum(){
	    	System.out.println("-------------------------------------\n" +
	    					   " NAME: " + this.name + "\n" +
	    					   " JOB:  " + this.job.toString() + "\n" +
	    					   " HEALTH:  "+ this.health + "/ " + this.maxHealth + "\n" +
	    					   " ATK:  " + this.attack + "\n" +
	    					   " DEF:  " + this.defence + "\n" +
	    					   " INIT: " + this.initMod + "\n" +
	    					   "--------------------------------------");
	    }
	    
	   //Causes player to lose health on attack
	    public void takeDamage(int nmeATK){
	        int damage = (nmeATK - defence);
	        if(damage > 0)										
	        	this.health -= damage;
	        if(this.health <= 0)
	        	this.health -= damage;					//if the damage would kill, set alive to false
	        this.alive = false;
	    }
	    
	    public void heal(int amount){
	    	this.health += amount;
	    }
	    
	    public void rollInit(){
	    	this.initiative = coreMath.rollD6() + this.initMod;
	    }
	    
	    //this determines what happens on a player's turn
	    public void turn(Enemy target){
	    	Scanner scanner = new Scanner( System.in );
	    	// print("use and item? y / n")
	        // if yes
	        // print("select item")
	        //     get selection
	        //     use item
	        // else
	        boolean noChoice = true;
            while(noChoice){
            	System.out.println("attack or defend? ");				// get selection
            	String choice = scanner.nextLine();
                if (choice.toLowerCase().equals("attack")){				// if attack Attack()
                	this.Attack(target);
                	System.out.println("choice1");
                    noChoice = false;
                }else if (choice.toLowerCase().equals("defend")){		// if defend Defend()
                	this.Defend();
                	System.out.println("choice2");
                    noChoice = false;
                }else{
                	System.out.println("nochoice");
                    noChoice = true;
                }
            }
            scanner.close();
	    }
	    
	    //Attacks target entity
	    public void Attack(Enemy target){
	    	int hit = this.attack + coreMath.rollD6();
	    	this.isDefending = false;
	    	System.out.println("You strike for "+ hit + " damage.");
	    	target.takeDamage(hit);
	    	
	    }
	    
	    //sets defend value to true
	    public void Defend(){
	    	this.isDefending = true;
	    }
	    
	    //Get methods 
	    public String getName(){
	    	return this.name; 
	    }
}