package com.czurch.rtl.mechanics;
import  java.util.Scanner;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.czurch.rtl.mechanics.Professions.*;
import com.czurch.rtl.mechanics.Enemy;
import com.czurch.rtl.mechanics.Items.*;
import com.czurch.rtl.mechanics.coreMath;


public class Player extends Character{
	static Scanner keyboard = new Scanner( System.in );
	//Used to determine the profession of the player
    public enum profession{
    	alchemist,
    	barbarian,
    	drunkard,
    	knight,
    	priest,
    	ranger,
    	rogue,
    	scribe
    }
	
	 	//Base stats for player
		protected profession job;
	    protected boolean isDefending;
	    
	    //Skill Checks
	    //to be added soon
	    
	    public Player()
	    {
	    	name = "Placeholder";
	    	job = profession.knight;
	    	health = 8;
	    	maxHealth = 8;
	    	attack = 0;
	    	defence = 0;
	    	armor = 0;
	    	initiative = 0;
	    	initMod = 0;
	    	alive = true;
	    	isDefending = false;
	    }

	    public Player(int x, int y, int rot, int sc, int sp, int dir)
	    {
			super(10, 10, 10, 10, 10, 10, 10);
	    	
	    	name = "Placeholder";
	    	job = profession.knight;
	    	health = 8;
	    	maxHealth = 8;
	    	attack = 0;
	    	defence = 0;
	    	armor = 0;
	    	initiative = 0;
	    	initMod = 0;
	    	alive = true;
	    	isDefending = false;
	    }
	    
	    public Player(String nam, int HP, int ATK, int DEF, int ARMR, int INIT, Weapon startWeapon, int x, int y, int rot, int sc, int sp, int dir){
	    	super(10, 10, 10, 10, 10, 10, 10);
	    	
	    	name = nam;
	    	job = profession.knight;
	    	health = HP;
	    	maxHealth = HP;
	    	attack = ATK;
	    	defence = DEF;
	    	armor = ARMR;
	    	initiative = 0;
	    	initMod = INIT;
	    	alive = true;
	    	isDefending = false;
	    	weaponEquipped = startWeapon;
	    }
	   
	    
	    // Prints the summary of a players stats
	    public void statSumm(){
	    	System.out.println("-------------------------------------\n" +
	    					   " NAME: " + this.name + "\n" +
	    					   " JOB:  " + this.job.toString() + "\n" +
	    					   " HEALTH:  "+ this.health + "/ " + this.maxHealth + "\n" +
	    					   " ATK:  " + this.attack + "\n" +
	    					   " DEF:  " + this.defence + "\n" +
	    					   " INIT: " + this.initMod + "\n" +
	    					   "--------------------------------------");
	    }
	    
	   // Causes player to lose health on attack
	    public void takeDamage(Character enemy, int nmeATK, int rollVsDEF){
	    	int damage = nmeATK - this.armor;
	    	if(rollVsDEF >= 20)																// if opponent rolls natural 20 it automatically hits
	    	{
	        	if(damage > 0)																// if [damage > 0] deal damage to health.
		        { 						
		        	this.health -= damage;
		        	System.out.println("You lose " + damage + " health.");
		        }
	    	}
	    	else if(this.isDefending)														// if player is defending
	        {
	        	int defRoll = coreMath.rollD20() + this.defence;
	        	System.out.println("You roll to defend!");
	        	System.out.println("You rolled " + defRoll);
	        	
	        	if(defRoll >= 20){
	        		System.out.println("You deflect the enemy's attack back at him");
	        		enemy.takeDamage(this, nmeATK, defRoll);
	        	}
	        	else if (defRoll > rollVsDEF)
	        	{
	        		System.out.println("You manage to block all of the enemy's damage");
	        	}
	        	else
	        	{
	        		System.out.print("You are unable to block the damage");
		        	if(damage > 0)															// if [damage > 0] deal damage to health.
			        { 						
			        	this.health -= damage;
			        	System.out.println("You lose " + damage + " health.");
			        }
	        	}
	        }
	        else if(rollVsDEF > this.defence)											// if their roll beats your defenses
	        {
	        	if(damage > 0)															// if [damage > 0] deal damage to health.
		        { 						
		        	this.health -= damage;
		        	System.out.println("You lose " + damage + " health.");
		        }
	        }
	        
	        if(this.health <= 0){
	        	this.alive = false;														// if the damage would kill, set alive to false.
	        	System.out.println("RIP. You have died.");
	        }
	    }
	    
	    // this determines what happens on a player's turn
	    public void turn(Character target){
	    	Scanner scanner = new Scanner( System.in );
	    	// print("use and item? y / n")
	        // if yes
	        // print("select item")
	        //     get selection
	        //     use item
	        // else
	        int action_points = 2;
            while(action_points > 0){
            	System.out.println("attack, defend, or item? ");				// get selection
            	String choice = scanner.nextLine();
            	choice.toLowerCase();
                if (choice.equals("attack"))									// if attack Attack()
                {				
                	this.Attack(target);
                    action_points -= 2;
                }
                else if (choice.equals("defend"))								// if defend Defend()
                {		
                	this.Defend();
                	action_points -= 2;
                }
                else if (choice.equals("item"))								// if you want to use an item
                {
                	this.showInventory();
                	System.out.println("Type the name of the item you'd like to use or type \"back\"");
                	choice = scanner.nextLine();
                	if(!choice.equals("back"))
                	{
	                	for (Item i : this.inventory) {
	                	    if (i.name.equals(choice)) 
	                	    {
	                	    	System.out.println("Would you like to use or throw " + choice);
	                	    	choice = scanner.nextLine();
	                	    	if(choice.equals("use"))
	                	    	{
	                	    		this.use(i);
	                	    		action_points -= 1;
	                	    	}
	                	    	else if(choice.equals("throw"))
	                	    	{
	                	    		this.throwItem(i, target);
	                	    		action_points -= 1;
	                	    	}
	                	        break;
	                	    }
	                	}
                	}
                }
                else if(choice.equals("stats"))
                {
                	this.statSumm();
                }
                else
                {
                	System.out.println("That is not a valid choice. Please select one of the following:\n" +
                					   "-attack\n-defend\n-item\n-stats");
                }
            }
	    }
	    
	    // Attacks target entity
	    @Override
	    public void Attack(Character target){
	    	this.isDefending = false;
	    	
	    	System.out.println("rolling a D20 vs your opponents Defense");
	    	int rollVsDEF = coreMath.rollD20();												// roll vs enemy def
	    	System.out.println("You rolled a " + rollVsDEF);
	    	
	    	int hit = this.weaponEquipped.active() + this.attack;
	    	System.out.println("You strike for "+ hit + " damage with your " + this.weaponEquipped.name);
	    	target.takeDamage(this, hit, rollVsDEF);
	    }
	    
	    // sets defend value to true
	    public void Defend(){
	    	this.isDefending = true;
	    	
	    }
	    
		// The Character creation method
		public Player createPlayer(){
			
			Player player = new Player();
			profession p = profession.knight;
			boolean professionNotSelected = true;
			
			System.out.println("Hello traveler.. what is your name?");			//Name your character
			String name = keyboard.nextLine();
			
			while(professionNotSelected)										//Select your class
			{
				System.out.println("What class would you like to play?");		
				professionDialogue();
				String choice = keyboard.nextLine();
				choice = choice.toLowerCase();
				try{
					p = profession.valueOf(choice);
					professionNotSelected = false;
				}catch(IllegalArgumentException e) {
		            
		        }
			}
			switch(p){
			case alchemist:
			    player = new Alchemist(name); 
			    break;
			case barbarian:
			    player = new Barbarian(name);
			    break;
			case drunkard:
			    player = new Drunkard(name);
			    break;
			case knight:
			    player = new Knight(name);
			    break;
			case priest:
			    player = new Priest(name);
			    break;
			case ranger:
			    player = new Ranger(name);
			    break;
			case rogue: 
			    player = new Rogue(name);
			    break;
			case scribe:
			    player = new Scribe(name);
			    break;
			default:
				System.out.println("I hit the default case");
				break;
			}
			return player;
		}
		
		public void professionDialogue()
		{
			System.out.println("\nAlchemist	--  Master of Matter (not finished)");
			System.out.println("Barbarian  	--  The Savage Smasher");
			System.out.println("Drunkard   	-- The Unweildy Hero (not finished)");
			System.out.println("Knight  	--  Guardian of Honor");
			System.out.println("Priest 		-- The Holy Devotee (not finished)");
			System.out.println("Ranger 		--  Wilderness Expert");
			System.out.println("Rogue  		--  The Nimble Shadow");
			System.out.println("Scribe  	--  Caller of Elements");
			
		}
	    
}
