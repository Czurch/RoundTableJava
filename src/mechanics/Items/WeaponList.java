package mechanics.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import mechanics.Enemy;
import mechanics.Items.Weapon.weaponType;

public class WeaponList {
	List<String> keysAsArray;
	Random r = new Random();
	
	public HashMap<String,Weapon> weaponMap = new HashMap<String,Weapon>();
	
	public WeaponList(){
		//swords
		weaponMap.put("short sword", new Weapon("short sword", weaponType.one_handed, 3, 7, 10));
		weaponMap.put("dagger", 	new Weapon("dagger", weaponType.one_handed, 1, 4, 10));
		weaponMap.put("rapier", 	new Weapon("rapier", weaponType.one_handed, 1, 6, 10));
		weaponMap.put("great sword", new Weapon("great sword", weaponType.one_handed, 2, 13, 10));
		weaponMap.put("scimitar", new Weapon("scimitar", weaponType.one_handed, 2, 8, 10));
		weaponMap.put("long sword", new Weapon("long sword", weaponType.one_handed, 2, 9, 10));
		weaponMap.put("footmans sword", new Weapon("footmans sword", weaponType.one_handed, 2, 5, 10));
		//axes
		weaponMap.put("hatchet", new Weapon("hatchet", weaponType.one_handed, 3, 6, 10));
		weaponMap.put("hand axe", new Weapon("hand axe", weaponType.one_handed, 3, 6, 10));
		weaponMap.put("throwing axe", new Weapon("throwing axe", weaponType.one_handed, 1, 4, 10));
		weaponMap.put("felling axe", new Weapon("felling axe", weaponType.one_handed, 3, 8, 10));
		weaponMap.put("war axe", new Weapon("war axe", weaponType.one_handed, 3, 10, 10));
		weaponMap.put("great axe", new Weapon("great axe", weaponType.one_handed, 3, 14, 10));
		//blunt weapons
		weaponMap.put("small hammer", new Weapon("small hammer", weaponType.one_handed, 1,3, 10));
		weaponMap.put("cudgel", new Weapon("cudgel", weaponType.one_handed, 2, 5, 10));
		weaponMap.put("mace", new Weapon("mace", weaponType.one_handed, 1, 6, 10));
		weaponMap.put("morning star", new Weapon("great axe", weaponType.one_handed, 2, 7, 10));
		weaponMap.put("spike hammer", new Weapon("spike hammer", weaponType.one_handed, 2, 9, 10));
		weaponMap.put("maul", new Weapon("maul", weaponType.one_handed, 3, 17, 10));
		//flails
		weaponMap.put("nun chuk", new Weapon("nun chuka", weaponType.one_handed, 1, 5, 10));
		weaponMap.put("ball and chain", new Weapon("ball and chain", weaponType.one_handed, 2, 6, 10));
		weaponMap.put("footmans flail", new Weapon("footmans flail", weaponType.one_handed, 2, 9, 10));
		weaponMap.put("holy water sprinkler", new Weapon("holy water sprinkler", weaponType.one_handed, 3, 12, 10));
		weaponMap.put("great flail", new Weapon("great flail", weaponType.two_handed, 3, 18, 10));
		//staves
		weaponMap.put("bow staff", new Weapon("bow staff", weaponType.one_handed, 1, 5, 10));
		weaponMap.put("blade staff", new Weapon("blade staff", weaponType.one_handed, 2, 7, 10));
		weaponMap.put("quarter staff", new Weapon("quarter staff", weaponType.one_handed, 1, 6, 10));
		//polearms
		weaponMap.put("spear", new Weapon("spear", weaponType.one_handed, 1, 8, 10));
		weaponMap.put("lance", new Weapon("lance", weaponType.one_handed, 1, 10, 10));
		weaponMap.put("glaive", new Weapon("glaive", weaponType.one_handed, 1, 10, 10));
		weaponMap.put("scythe", new Weapon("scythe", weaponType.one_handed, 1, 10, 10));
		weaponMap.put("halberd", new Weapon("halberd", weaponType.one_handed, 1, 12, 10));
		weaponMap.put("bardiche", new Weapon("bardiche", weaponType.one_handed, 3, 13, 10));
		weaponMap.put("pole hammer", new Weapon("pole hammer", weaponType.one_handed, 3, 12, 10));
		//ranged
		weaponMap.put("hand sling", new Weapon("hand sling", weaponType.one_handed, 1, 2, 10));
		weaponMap.put("hand crossbow", new Weapon("hand crossbow", weaponType.one_handed, 1, 3, 10));
		weaponMap.put("light crossbow", new Weapon("light crossbow", weaponType.one_handed, 3, 14, 10));
		weaponMap.put("crossbow", new Weapon("crossbow", weaponType.one_handed, 2, 5, 10));
		weaponMap.put("heavy crossbow", new Weapon("heavy crossbow", weaponType.one_handed, 3, 14, 10));
		weaponMap.put("bow", new Weapon("bow", weaponType.one_handed, 1, 5, 10));
		weaponMap.put("long bow", new Weapon("long bow", weaponType.one_handed, 2, 7, 10));
		weaponMap.put("recurve bow", new Weapon("recurve bow", weaponType.one_handed, 1, 6, 10));
	}
}
