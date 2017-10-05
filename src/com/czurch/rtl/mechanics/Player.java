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
			super(x, y, rot, sc, sp, dir);
			Image tileset;
			try {
				tileset = new Image("res/DungeonCrawl_ProjectUtumnoTileset.png");
				this.sprite = tileset.getSubImage(32, 992, 32, 32);
			} catch (SlickException e) {
				e.printStackTrace();
			}
	    	
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
			super(x, y, rot, sc, sp, dir);
			Image tileset;
			try {
				tileset = new Image("res/DungeonCrawl_ProjectUtumnoTileset.png");
				this.sprite = tileset.getSubImage(32, 992, 32, 32);
			} catch (SlickException e) {
				e.printStackTrace();
			}
	    	
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
	   
	    
	    //Prints the summary of a players stats
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
	    
	   //Causes player to lose health on attack
	    @Override
	    public void takeDamage(Character e, int nmeATK){
	    	int damage = nmeATK;
	        if(this.isDefending)					//if player is defending
	        {
	        	int defRoll = coreMath.rollD20() + this.defence;
	        	if(defRoll >= 20){
	        		System.out.println("You deflect the enemy's attack back at him");
	        		e.takeDamage(this, nmeATK);
	        	}else if(defRoll > 10+nmeATK){
	        		System.out.println("You manage to block all of the enemy's damage");
	        	}else{
	        		damage = nmeATK - this.defence;
	        		System.out.println("You were unable to block the damage and lose " + damage + " health.");
	        	}
	        }
	        else if(damage > 0){								//if [damage > 0] deal damage to health. 						
	        	this.health -= damage;
	        	System.out.println("You lose " + damage + " health.");
	        }
	        if(this.health <= 0){
	        	//if the damage would kill, set alive to false.
	        	this.alive = false;
	        	System.out.println("RIP. You have died.");
	        }
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
            	choice.toLowerCase();
                if (choice.equals("attack")){				// if attack Attack()
                	this.Attack(target);
                    noChoice = false;
                }else if (choice.equals("defend")){		// if defend Defend()
                	this.Defend();
                    noChoice = false;
                }else if (choice.equals("item")){
                	this.showInventory();
                	noChoice = false;
                }else{
                	System.out.println("That is not a valid choice. Please select one of the following:\n" +
                					   "-attack\n-defend\n-item");
                    noChoice = true;
                }
            }
	    }
	    
	    //Attacks target entity
	    public void Attack(Enemy target){
	    	int hit = this.weaponEquipped.active();
	    	this.isDefending = false;
	    	System.out.println("You strike for "+ hit + " damage with your " + this.weaponEquipped.name);
	    	target.takeDamage(this, hit);
	    }
	    
	    //sets defend value to true
	    public void Defend(){
	    	this.isDefending = true;
	    	
	    }
	    
		//The Character creation method
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
			    player = new Ranger(this.name);
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
