package com.czurch.rtl.mechanics.Items;

import com.czurch.rtl.mechanics.*;
import com.czurch.rtl.mechanics.Character;

public class Consumable extends Item {
	Character user;
	int heal,
	hp, 
	atk,
	def,
	amr,
	init; 
	boolean dead;
	
	public Consumable(String nom, int val, int wght, int heal, int hp, int atk, int def, int amr, int init, boolean dead){
		this.heal = heal;
		this.hp = hp; 
		this.atk = atk;
		this.def = def;
		this.amr = amr;
		this.init = init; 
		this.dead = dead;
	}
	
	public void active(int place, Character usr)
	{
		user = usr;
	    user.health += heal;
	    user.maxHealth += hp;
	    user.attack += atk;
	    user.defence += def;
	    user.armor += amr;
	    user.initMod += init;
	    user.alive = dead; 
	    user.inventory[place] = null;		//remove the item from the inventory
	}

	public void passive()
	{
		
	}
}
