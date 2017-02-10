package mechanics.Professions;
import mechanics.*;

/*
 * The Knight
 * 
 * A Knight's purpose is to protect honor.
 * In whatever way he sees fit.
*/

public class Knight extends Player {

	public Knight(String nam){
		name = nam;
		job = profession.knight;
    	health = 20;
    	maxHealth = 20;
    	attack = 1;
    	defence = 1;
    	initiative = 0;
    	initMod = 0;
    	alive = true;
    	isDefending = false;
	}
	
}
