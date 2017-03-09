package mechanics.Professions;
import mechanics.*;
import mechanics.Items.WeaponList;

/*
 * The Knight
 * 
 * A Knight's purpose is to protect honor.
 * In whatever way he sees fit.
*/

public class Knight extends Player {
	WeaponList catalog = new WeaponList();
	
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
    	weaponEquipped = catalog.weaponMap.get("short sword");
	}
	
}
