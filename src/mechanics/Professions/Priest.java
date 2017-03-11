package mechanics.Professions;
import mechanics.*;
import mechanics.Items.WeaponList;

/*
 * The Priest
 * 
 * His holy devotion comes before all else.
*/

public class Priest extends Player{
	WeaponList catalog = new WeaponList();
	
	int prayersSaid = 0;		//tracks the number of times prayers are used
	int MaxPrayers = 2;			//max prayers the priest may use
	
	public Priest(String nam){
		name = nam;
		job = profession.priest;
    	health = 20;
    	maxHealth = 20;
    	attack = 0;
    	defence = 0;
    	initiative = 0;
    	initMod = 0;
    	alive = true;
    	isDefending = false;
    	weaponEquipped = catalog.weaponMap.get("mace");
	}
	
	public void PrayerOfBlessing(Player target){
		int healAmount = coreMath.rollD6();
		System.out.println("Your god has granted " + 
							target.getName() + 
							" with " +
							healAmount +
							" healing");		
		target.heal(healAmount);
	}
}
