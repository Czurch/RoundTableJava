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

public class Character{
	public WeaponList catalog = new WeaponList();
	
	//Base stats for player
	public String name;
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
    int carry_capacity = 16;
    public List<Item> inventory = new ArrayList<Item>();
    public List<Effect> effects_list = new ArrayList<Effect>();
    
    //Interfacing Stats
    public int strength
    ,constitution
    ,agility
    ,intelligence
    ,arcana
    ,wits
    ,charisma;
    
    public Character()
    {
    	
    }
    
    
    public Character(int str, int con, int agil, int intel, int arc, int wit, int charis)
    {
    	strength 		= str;
    	constitution 	= con;
    	agility 		= agil;
    	intelligence 	= intel;
    	arcana 			= arc;
    	wits 			= wit;
    	charisma		= charis;
    }
    
    //Attacks target entity
    public void Attack(Character target){
    	
    	System.out.println(this.name + " rolling a D20 vs Defense");
    	int rollVsDEF = coreMath.rollD20();												// roll vs enemy def
    	System.out.println(this.name + " rolled a " + rollVsDEF);
    	
    	int hit = this.weaponEquipped.active() + this.attack;
    	System.out.println(this.name + " strikes for "+ hit + " damage with it's " + this.weaponEquipped.name);
    	target.takeDamage(this, hit, rollVsDEF);
    }

	//Causes enemy to lose health on attack
    public void takeDamage(Character enemy, int nmeATK, int rollVsDEF){
        int damage = (nmeATK - this.armor);
        if(rollVsDEF > this.defence)
        {
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
    }
    
    public void pickUp(Item i)
    {
    	if(inventory.size() <= carry_capacity)
    	{
    		inventory.add(i);
    		i.Passive(this);
    	}
    	else
    	{
    		System.out.println("Cannot pick up " + i.name + ". There is no more room in the inventory.");
    	}
    }
    
    // drops item from inventory to ground 
    public void drop(Item i)
    {
    	inventory.remove(i);
    	i.Passive(this);
    }
    
    // removes specified item from play entirely
    public void remove(Item i)
    {
    	inventory.remove(i);
    	i.Passive(null);
    	i = null;
    }
    
    // applies an items active effects
    public void use(Item i)
    {
    	i.Active(this);
    }
    
    // Throw Item
    public void throwItem(Item i, Character target)
    {
    	i.Throw(this, target);
    	inventory.remove(i);
    }
    
    // add an effect to the effects_list
    public void addEffect(Effect x)
    {
    	effects_list.add(x);
    	this.applyEffect(x);
    }
    
    // applies a specified effect to the player
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
    			strength += x.mod;
    		break;
    	case AGILITY:
    			agility += x.mod;
    		break;
    	case CONSTITUTION:
    			constitution += x.mod;
    		break;
    	case CLAIRVOYANCE:
    			intelligence += x.mod;
    		break;
    	case MEDITATION:
    			arcana += x.mod;
    		break;
    	case KEEN_SENSES:
    			wits += x.mod;
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
    
    
    /*
     * Buff are slightly different from effects
     * buffs will last until they are told otherwise
     * e.g. Equipment buffs are added on Equip and removed on Unequip
     */
    
    
    public void addBuff(Effect e)
    {
    	this.applyEffect(e);
    }
    
    public void removeBuff(Effect e)
    {
    	e.mod = -(e.mod);
    	applyEffect(e);
    }
    
    
    // attempts to pick up all the items in another Character's inventory
    public void loot(Character chump)
    {
    	System.out.println("Looting!");

    	for(Iterator<Item> i = chump.inventory.iterator(); i.hasNext();)
    	{
    		Item x = i.next();
    		if(x != null){
    			System.out.println("-found " + x.name);
    			this.pickUp(x);
    		}
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
    
    public void showInventory(){
    	for(Iterator<Item> i = inventory.iterator(); i.hasNext();)
    	{
    		Item x = i.next();
    		if(x != null){
    			System.out.println("- " + x.name);
    		}
    	}
    }
}
