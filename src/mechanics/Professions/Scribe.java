package mechanics.Professions;
import mechanics.*;

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
		name = nam;
		job = profession.scribe;
    	health = 20;
    	maxHealth = 20;
    	attack = 0;
    	defence = 0;
    	initiative = 0;
    	initMod = 0;
    	alive = true;
    	isDefending = false;
	}
	
	@Override
	public void Attack(Enemy target){
		int elementalDmg = coreMath.randomNumberBetween(1, maxElementDmg); 	//Scribe gets elemental damage on attacks
		int damage = coreMath.rollD6() + elementalDmg;
		target.takeDamage(this, damage);
	}
}
