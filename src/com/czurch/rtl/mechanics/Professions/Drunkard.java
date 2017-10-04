package com.czurch.rtl.mechanics.Professions;
import com.czurch.rtl.mechanics.Player;


/*
 * The Drunkard
 * 
 * A Hearty Rioter
*/

public class Drunkard extends Player{

	public Drunkard(String nam){
		name = nam;
		job = profession.drunkard;
    	health = 20;
    	maxHealth = 20;
    	attack = 1;
    	defence = 1;
    	initiative = 0;
    	initMod = 0;
    	alive = true;
    	isDefending = false;
    	weaponEquipped = catalog.weaponMap.get("cudgel");
	}
}
