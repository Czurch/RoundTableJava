package com.czurch.rtl.mechanics.Professions;
import com.czurch.rtl.mechanics.*;
import com.czurch.rtl.mechanics.Character;

/*
 * The Scribe
 * 
 * Arcane powers of the elements are revealed
 * through his research.
*/

public class Scribe extends Player{
	
	//The MIN / MAX for elemental damage on attacks
	protected int maxElementDmg = 2;		
	protected int minElementDmg = 1;
	
	//CONSTRUCTOR
	public Scribe(String nam){
		super(12,9,13,15,14,9);
		name = nam;
		job = profession.scribe;
		weaponEquipped = catalog.weaponMap.get("quarter staff");
		
    	maxHealth = constitution + coreMath.randomNumberBetween(1, 6);
    	health = maxHealth;
    	attack = 0;
    	defence = 0;
    	initiative = 0;
    	initMod = 0;
    	
    	alive = true;
    	isDefending = false;
	}
	
	@Override
	public void Attack(Character target)
	{
    	System.out.println("rolling a D20 vs your opponents Defense");
    	int rollVsDEF = coreMath.rollD20();												// roll vs enemy def
    	System.out.println("You rolled a " + rollVsDEF);
    	
    	int hit = this.weaponEquipped.active();
		int elementalDmg = coreMath.randomNumberBetween(minElementDmg, maxElementDmg); 	//Scribe gets elemental damage on attacks
		hit = hit + elementalDmg;
		target.takeDamage(this, hit, rollVsDEF);
	}
}
