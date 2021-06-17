package com.czurch.rtl.mechanics.Professions;
import com.czurch.rtl.mechanics.*;
import com.czurch.rtl.mechanics.Character;


/*
 * The Barbarian
 * 
 * The Savage Smasher
*/

public class Barbarian extends Player{
	
	//creates an instance of a Barbarian    
	public Barbarian(String nam){
		super(15,13,14,9,11,10);
		name = nam;
		job = profession.barbarian;
    	maxHealth = constitution + coreMath.randomNumberBetween(1, 6);
    	health = maxHealth;
    	attack = 0;
    	defence = 0;
    	initiative = 0;
    	initMod = 0;
    	alive = true;
    	isDefending = false;
    	weaponEquipped = catalog.weaponMap.get("hatchet");
	}
	
	@Override 
    public void Attack(Character enemy){
		isDefending = false;
    	System.out.println("rolling a D20 vs your opponents Defense");
    	int rollVsDEF = coreMath.rollD20();												// roll vs enemy def
    	System.out.println("You rolled a " + rollVsDEF);
    	
    	int hit = this.weaponEquipped.active();
    	
        if(rollVsDEF >= 20){
        	hit = (int) (hit * 1.5);
        	System.out.println("BARBARIAN SMASH deals " + hit + " damage.");
            enemy.takeDamage(this, hit, rollVsDEF);
        }else{
        	System.out.println("You deal " + hit + " damage.");
            enemy.takeDamage(this, hit, rollVsDEF);
        }
    }
}
