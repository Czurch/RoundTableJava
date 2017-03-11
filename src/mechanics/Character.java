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
    public combat_options combatChoice(){
    	int randy = (int)(Math.random() * 6) + 1;
    	switch(randy)
    	{
	    	case 2:
	    		return combat_options.slash; 
	    	case 3:
	    		return combat_options.slam;
	    	case 4:
	    		return combat_options.block;
	    	case 5:
	    		return combat_options.feint;
	    	case 6:
	    		return combat_options.parry;
	    	default:
	    		return combat_options.lunge;
    	}
    }
    
    
    //FUNCTION: Attack
    //Attacks target entity
    public void Attack(Character target){
    	int hit = this.weaponEquipped.active();
    	if(target == this){
    		System.out.println(this.name + " strikes itself for "+ hit + " damage with it's " + this.weaponEquipped.name);
    	}else{
    		System.out.println(this.name + " strikes for "+ hit + " damage with it's " + this.weaponEquipped.name);
    	}
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
