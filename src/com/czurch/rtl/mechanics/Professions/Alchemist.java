package com.czurch.rtl.mechanics.Professions;
import com.czurch.rtl.mechanics.*;
import com.czurch.rtl.mechanics.Player.profession;

public class Alchemist extends Player{
	int spellsUsed = 0;
	int maxSpells = 2;
	
	public Alchemist(String nam){
		super(9,12,10,15,14,11);
		name = nam;
		job = profession.alchemist;
    	maxHealth = constitution + coreMath.randomNumberBetween(1, 6);
    	health = maxHealth;
    	attack = 0;
    	defence = 0;
    	initiative = 0;
    	initMod = 0;
    	alive = true;
    	isDefending = false;
    	weaponEquipped = catalog.weaponMap.get("rapier");
	}
	
	//add in spell based on stone held
}
