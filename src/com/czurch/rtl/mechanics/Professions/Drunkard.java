package com.czurch.rtl.mechanics.Professions;
import com.czurch.rtl.mechanics.Player;
import com.czurch.rtl.mechanics.coreMath;


/*
 * The Drunkard
 * 
 * A Hearty Rioter
*/

public class Drunkard extends Player{

	public Drunkard(String nam){
		super(12,11,15,9,13,13);
		name = nam;
		job = profession.drunkard;
    	maxHealth = constitution + coreMath.randomNumberBetween(1, 6);
    	health = maxHealth;
    	attack = 1;
    	defence = 1;
    	initiative = 0;
    	initMod = 0;
    	alive = true;
    	isDefending = false;
    	weaponEquipped = catalog.weaponMap.get("cudgel");
	}
}
