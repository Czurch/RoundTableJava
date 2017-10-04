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
		name = nam;
		job = profession.rogue;
    	health = 20;
    	maxHealth = 20;
    	attack = 0;
    	defence = 0;
    	initiative = 0;
    	initMod = 1;
    	alive = true;
    	isDefending = false;
	}
	
	@Override
   //Causes player to lose health on attack
	public void takeDamage(Character e, int nmeATK){
		int mitigation = coreMath.rollD6();
		System.out.println("You're rogue skills allow you to mitigate " + mitigation + " damage.");
    	int damage = (nmeATK - mitigation);
        if(this.isDefending)					//if player is defending
        {
        	int defRoll = coreMath.rollD20() + this.defence;
        	if(defRoll >= 20){
        		System.out.println("You deflect the enemy's attack back at him!");
        		e.takeDamage(this, nmeATK);
        	}else if(defRoll > 10+nmeATK){
        		System.out.println("You manage to block all of the enemy's damage!");
        	}else{
        		damage = nmeATK - this.defence;
        		System.out.println("You were unable to block the damage and lose " + damage + " health.");
        	}
        }
        else if(damage > 0){								//if [damage > 0%] deal damage to health. 						
        	this.health -= damage;
        	System.out.println("You lose " + damage + " health.");
        }
        else if(this.health <= 0){
        	this.health -= damage;					//if the damage would kill, set alive to false.
        	this.alive = false;
        	System.out.println("RIP. You have died.");
        }
	}
}
