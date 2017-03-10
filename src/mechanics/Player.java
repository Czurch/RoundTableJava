package mechanics;
import  java.util.Scanner;

import mechanics.Items.Weapon;
import mechanics.Items.Weapon.combat_options;

public class Player extends Character{
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

	    public Player(){
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
	    
	    public Player(String nam, int HP, int ATK, int DEF, int ARMR, int INIT, Weapon startWeapon){
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
	        	this.health -= damage;					//if the damage would kill, set alive to false.
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
                if (choice.toLowerCase().equals("attack")){				// if attack Attack()
                	this.Attack(target);
                    noChoice = false;
                }else if (choice.toLowerCase().equals("defend")){		// if defend Defend()
                	this.Defend();
                    noChoice = false;
                }else{
                	System.out.println("nochoice");
                    noChoice = true;
                }
            }
	    }
	    
	    //FUNTION:  combatChoice
	    //Determines the players choice of action during a combat turn
	    @Override
	    public void combatChoice(){
	    	Scanner scanner = new Scanner( System.in );
	    	System.out.println("How will you attack?");
	    	
	    	String choice = scanner.nextLine();
	    	
	    	switch(choice.toLowerCase())
	    	{
		    	case "lunge":
		    	case "l":
		    		combat_choice = combat_options.lunge;
		    	break;
		    	case "slash":
		    		combat_choice = combat_options.slash; 
		    	break;
		    	case "slam":
		    		combat_choice = combat_options.slam;
		    	break;
		    	case "block":
		    	case "defend":
		    	case "d":
		    		combat_choice = combat_options.block;
		    	break;
		    	case "feint":
		    	case "f":
		    		combat_choice = combat_options.feint;
		    	break;
		    	case "parry":
		    	case "p":
		    		combat_choice = combat_options.parry;
		    	break;
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
	    
}