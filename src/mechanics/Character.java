package mechanics;

import mechanics.Player.profession;
import mechanics.Items.Weapon;

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
    protected Weapon weaponEquipped;
    
    
    //Attacks target entity
    public void Attack(Character target){
    	int hit = this.weaponEquipped.active();
    	System.out.println(this.name + " strikes for "+ hit + " damage with it's " + this.weaponEquipped.name);
    	target.takeDamage(this, hit);
    }

	//Causes enemy to lose health on attack
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
    //Check to see if character is alive
    public boolean isAlive(){
    	return this.alive;
    }
    
    //heals the character an amount
    public void heal(int amount){
    	this.health += amount;
    }
    
    //rolls an initiative for the character
    public void rollInit(){
    	this.initiative = coreMath.rollD6() + this.initMod;
    }
    
    //return the character's name
    public String getName(){
    	return this.name; 
    }
}
