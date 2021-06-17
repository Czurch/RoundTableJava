package com.czurch.rtl.mechanics.Professions;
import com.czurch.rtl.mechanics.*;

/*
 * The Ranger
 * 
 * A master of perception and ranged attacks.
*/

public class Ranger extends Player{

	public Ranger(String nam){
		super(13,14,11,11,14,9);
		name = nam;
		job = profession.ranger;
    	maxHealth = constitution + coreMath.randomNumberBetween(1, 6);
    	health = maxHealth;
    	attack = 1;
    	defence = 0;
    	initiative = 0;
    	initMod = 3;
    	alive = true;
    	isDefending = false;
    	weaponEquipped = catalog.weaponMap.get("long bow");
	}
	
}

