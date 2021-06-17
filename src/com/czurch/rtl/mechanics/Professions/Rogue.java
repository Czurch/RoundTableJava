package com.czurch.rtl.mechanics.Professions;
import com.czurch.rtl.mechanics.*;
import com.czurch.rtl.mechanics.Character;

/*
 * The Rogue
 * 
 * As untouchable as the shadows.
*/

public class Rogue extends Player{
	
	public Rogue(String nam){
		super(11,15,9,12,14,11);
		name = nam;
		job = profession.rogue;
    	maxHealth = constitution + coreMath.randomNumberBetween(1, 6);
    	health = maxHealth;
    	attack = 0;
    	defence = 0;
    	initiative = 0;
    	initMod = 1;
    	alive = true;
    	isDefending = false;
    	weaponEquipped = catalog.weaponMap.get("dagger");
	}
	
	// consider adding some cool mechanic to dodge damage as the rogue
}
