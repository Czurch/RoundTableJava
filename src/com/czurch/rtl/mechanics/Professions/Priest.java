package com.czurch.rtl.mechanics.Professions;
import com.czurch.rtl.mechanics.*;

/*
 * The Priest
 * 
 * His holy devotion comes before all else.
*/

public class Priest extends Player{

	int prayersSaid = 0;		//tracks the number of times prayers are used
	int MaxPrayers = 2;			//max prayers the priest may use
	
	public Priest(String nam){
		super(10,12,13,12,14,13);
		name = nam;
		job = profession.priest;
    	maxHealth = constitution + coreMath.randomNumberBetween(1, 6);
    	health = maxHealth;
    	attack = 0;
    	defence = 0;
    	initiative = 0;
    	initMod = 0;
    	alive = true;
    	isDefending = false;
    	weaponEquipped = catalog.weaponMap.get("nun chuka");
	}
	
	public void PrayerOfBlessing(Player target){
		int healAmount = coreMath.rollD6();
		System.out.println("Your god has granted " + 
							target.name + 
							" with " +
							healAmount +
							" healing");		
		target.heal(healAmount);
	}
}
