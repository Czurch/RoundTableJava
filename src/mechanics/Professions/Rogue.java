package mechanics.Professions;
import mechanics.*;

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
	public void takeDamage(int nmeATK){
		int mitigation = coreMath.rollD6();
    	int damage = (nmeATK - defence) - mitigation;
    	if(damage > 0)										
    		health -= damage;
    	if(health <= 0)
    		health -= damage;					//if the damage would kill, set alive to false
    	alive = false;
	}
}
