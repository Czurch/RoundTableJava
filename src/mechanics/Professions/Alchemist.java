package mechanics.Professions;
import mechanics.*;
import mechanics.Items.WeaponList;
/*
 * The Alchemist
 * 
 * His technology is so advanced
 * that others often perceive it 
 * as magic.
*/
import mechanics.Player.profession;

public class Alchemist extends Player{
	WeaponList catalog = new WeaponList();
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
    	weaponEquipped = catalog.weaponMap.get("bow staff");
	}
	
	//add in spell based on stone held
}
