package mechanics.Professions;
import mechanics.*;
import mechanics.Items.WeaponList;

/*
 * The Ranger
 * 
 * A master of perception and ranged attacks.
*/

public class Ranger extends Player{
	WeaponList catalog = new WeaponList();

	public Ranger(String nam){
		name = nam;
		job = profession.ranger;
    	health = 20;
    	maxHealth = 20;
    	attack = 1;
    	defence = 0;
    	initiative = 0;
    	initMod = 3;
    	alive = true;
    	isDefending = false;
    	weaponEquipped = catalog.weaponMap.get("bow");
	}
	
}

