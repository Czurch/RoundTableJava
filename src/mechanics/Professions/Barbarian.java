package mechanics.Professions;
import mechanics.*;
import mechanics.Items.WeaponList;


/*
 * The Barbarian
 * 
 * The Savage Smasher
*/

public class Barbarian extends Player{
	WeaponList catalog = new WeaponList();
	
	//creates an instance of a Barbarian    
	public Barbarian(String nam){
		name = nam;
		job = profession.barbarian;
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
	
	@Override 
    public void Attack(Enemy e){
		isDefending = false;
        int roll_1 = coreMath.rollD6();
        int roll_2 = coreMath.rollD6();
        if(roll_1 == roll_2){
        	System.out.println("BARBARIAN SMASH deals " + (roll_1 + roll_2) + " damage.");
            e.takeDamage(this, (roll_1 + roll_2));
        }else{
        	System.out.println("You deal " + roll_1 + " damage.");
            e.takeDamage(this, roll_1);
        }
    }
}
