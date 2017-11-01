package com.czurch.rtl.mechanics;

import com.czurch.rtl.mechanics.Items.Effect;
import com.czurch.rtl.mechanics.Items.Effect.effectors;
import com.czurch.rtl.mechanics.Items.Item;
import com.czurch.rtl.mechanics.Items.Weapon;
import com.czurch.rtl.mechanics.Items.WeaponList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public List<Item> inventory = new ArrayList<Item>();
    int carry_capacity = 16;
    public List<Effect> effects_list = new ArrayList<Effect>();
    
    public Character()
    {
    	
    }
    
    
    public Character(int x, int y, int rot, int sc, int sp, int dir)
    {
    	super(x, y, rot, sc, sp, dir);
    }
    
    //Attacks target entity
    public void Attack(Character target){
    	int hit = this.weaponEquipped.active();
    	System.out.println(this.name + " strikes for "+ hit + " damage with it's " + this.weaponEquipped.name);
    	target.takeDamage(hit);
    }

	//Causes enemy to lose health on attack
    public void takeDamage(int nmeATK){
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
    
    public void pickUp(Item i)
    {
    	if(inventory.size() <= carry_capacity)
    	{
    		inventory.add(i);
    
    	}
    }
    
    public void addEffect(Effect x, int time)
    {
    	effects_list.add(x);
    	this.applyEffect(x);
    }
    
    public void applyEffect(Effect x)
    {
    	switch(x.effect)
    	{
    	case HEALING:
    		health += x.mod;
    		break;
    	case VIGOR:
    		maxHealth += x.mod;
    		break;
    	case LETHAL:
    		attack += x.mod;
    		break;
    	case FORTITUDE:
    		defence += x.mod;
    		break;
    	case ARMOR:
    		armor += x.mod;
    		break;
    	case HASTE:
    		initMod += x.mod;
    		break;
    	case DEATH:
    		alive = false;
    		break;
    	case MIGHT:
    		break;
    	case AGILITY:
    		break;
    	case CONSTITUTION:
    		break;
    	case CLAIRVOYANCE:
    		break;
    	case MEDITATION:
    		break;
    	case KEEN_SENSES:
    		break;
    	}
    	
    	//subtract one from time value on the effect
    	x.time -= 1;
    	if(x.time == 0)
    	{ 
    		effects_list.remove(x);
    	}
    }
    
    //iterates through current status effects and applies their effects
    public void applyAllEffects()
    {
    	for(Iterator<Effect> i = effects_list.iterator(); i.hasNext();){
    		Effect x = i.next();
    		this.applyEffect(x);
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
    	for(Iterator<Item> i = inventory.iterator(); i.hasNext();)
    	{
    		Item x = i.next();
    		if(x != null){
    			System.out.println("- " + x.name + "\n");
    		}
    	}
    }
}
