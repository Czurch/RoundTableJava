package com.czurch.rtl.mechanics;

import com.czurch.rtl.mechanics.Items.Item;
import com.czurch.rtl.mechanics.Items.Weapon;
import com.czurch.rtl.mechanics.Items.WeaponList;
import com.czurch.rtl.mechanics.Player.profession;

public class Character extends GameObject{
	public WeaponList catalog = new WeaponList();
	
	//Base stats for player
	protected String name;
	protected profession job;
    public int health 
    ,maxHealth
    ,attack
    ,defence
    ,armor
    ,initiative
    ,initMod;
    public boolean alive;
    protected Weapon weaponEquipped;
    public Item inventory[] = new Item[20];
    
    public Character()
    {
    	for(int i = 0; i < inventory.length; i++)
    	{
    		inventory[i] = null;
    	}
    }
    
    
    public Character(int x, int y, int rot, int sc, int sp, int dir)
    {
    	super(x, y, rot, sc, sp, dir);
    }
    
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
    
    public void showInventory(){
    	for(int i = 0; i < inventory.length; i++)
    	{
    		if(inventory[i] != null){
    			System.out.println("-"+ i + " " + inventory[i].name + "\n");
    		}
    	}
    }
}
