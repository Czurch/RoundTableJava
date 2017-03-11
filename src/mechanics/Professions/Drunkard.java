package mechanics.Professions;
import mechanics.Player;
import mechanics.Items.WeaponList;


/*
 * The Drunkard
 * 
 * A Hearty Rioter
*/

public class Drunkard extends Player{
	WeaponList catalog = new WeaponList();

	public Drunkard(String nam){
		name = nam;
		job = profession.drunkard;
    	health = 20;
    	maxHealth = 20;
    	attack = 1;
    	defence = 1;
    	initiative = 0;
    	initMod = 0;
    	alive = true;
    	isDefending = false;
    	weaponEquipped = catalog.weaponMap.get("hand axe");
	}
}
