package mechanics;

import mechanics.Player.profession;
import mechanics.Items.Weapon;
import mechanics.Items.Weapon.combat_options;


public class Character {
	//Base stats for player
	protected String name;
	protected profession job;
    protected int health 
    ,maxHealth
    ,attack
    ,defence
    ,armor
    ,initiative
    ,initMod;
    protected boolean alive;
    
    //Combat variables
    protected combat_options combat_choice;
    
    //Item variables
    protected Weapon weaponEquipped;
    
    //FUNTION:  combatChoice
    //Determines the players choice of action during a combat turn
    public void combatChoice(){
    	int randy = (int)(Math.random() * 6) + 1;
    	switch(randy)
    	{
	    	case 1:
	    		combat_choice = combat_options.lunge;
	    	break;
	    	case 2:
	    		combat_choice = combat_options.slash; 
	    	break;
	    	case 3:
	    		combat_choice = combat_options.slam;
	    	break;
	    	case 4:
	    		combat_choice = combat_options.block;
	    	break;
	    	case 5:
	    		combat_choice = combat_options.feint;
	    	break;
	    	case 6:
	    		combat_choice = combat_options.parry;
	    	break;
    	}
    }
    
    
    //FUNCTION: Attack
    //Attacks target entity
    public void Attack(Character target){
    	int hit = this.weaponEquipped.active();
    	System.out.println(this.name + " strikes for "+ hit + " damage with it's " + this.weaponEquipped.name);
    	target.takeDamage(this, hit);
    }

    //FUNCTION: takeDamage
	//Causes character to lose health on attack
    public void takeDamage(Character nme, int nmeATK){
        int damage = (nmeATK - this.defence);
        if(damage > 0){										
        	this.health -= damage;
        	System.out.println(this.name + " loses " + damage + " health.");
        }
        if(this.health <= 0){
        	this.health -= damage;					//if the damage would kill, set alive to false
        	this.alive = false;
        	System.out.println(this.name + " has died.");
        }
    }
    
    /*-----------------------------------
     *       GET / SET methods 
     * ----------------------------------
     * */
    //FUNCTION:  isAlive
    //Check to see if character is alive
    public boolean isAlive(){
    	return this.alive;
    }
    
    //FUNCTION:  heal
    //heals the character an amount
    public void heal(int amount){
    	this.health += amount;
    }
    
    //FUNCTION:  rollInit  
    //rolls an initiative for the character
    public void rollInit(){
    	this.initiative = coreMath.rollD6() + this.initMod;
    }
    
    //FUNCTION:  getName
    //return the character's name
    public String getName(){
    	return this.name; 
    }
}
