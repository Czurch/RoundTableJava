package com.czurch.rtl.mechanics.Professions;
import com.czurch.rtl.mechanics.*;
import com.czurch.rtl.mechanics.Player.profession;

public class Alchemist extends Player{
	int spellsUsed = 0;
	int maxSpells = 2;
	
	public Alchemist(String nam){
		name = nam;
		job = profession.alchemist;
    	health = 20;
    	maxHealth = 20;
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
