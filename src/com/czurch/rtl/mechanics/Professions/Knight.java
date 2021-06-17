package com.czurch.rtl.mechanics.Professions;
import com.czurch.rtl.mechanics.*;

/*
 * The Knight
 * 
 * A Knight's purpose is to protect honor.
 * In whatever way he sees fit.
*/

public class Knight extends Player {
	
	public Knight(String nam){
		super(14,10,14,10,12,13);
		name = nam;
		job = profession.knight;
    	maxHealth = constitution + coreMath.randomNumberBetween(1, 6);
    	health = maxHealth;
    	attack = 1;
    	defence = 1;
    	initiative = 0;
    	initMod = 0;
    	alive = true;
    	isDefending = false;
    	weaponEquipped = catalog.weaponMap.get("short sword");
	}
	
}
