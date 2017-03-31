package mechanics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import mechanics.Items.Weapon;
import mechanics.Items.Weapon.weaponType;
import mechanics.Worldbuilding.Tile;
import mechanics.Worldbuilding.Tile.terrain;
import mechanics.Items.WeaponList;

public class EnemyList {
	List<String> keysAsArray;
	Random r = new Random();
	WeaponList weapon_catalog;
	
	public HashMap<terrain,HashMap<String,Enemy>> lookup = new HashMap<terrain,HashMap<String,Enemy>>();//each terrain maps to list of enemies
	public HashMap<String,Enemy> enemyMap = new HashMap<String,Enemy>();								//each enemy name is mapped to it's entry
	public HashMap<String,Enemy> plainsList = new HashMap<String,Enemy>();
	public HashMap<String,Enemy> forestList = new HashMap<String,Enemy>();
	public HashMap<String,Enemy> mountainList = new HashMap<String,Enemy>();
	public HashMap<String,Enemy> swampList = new HashMap<String,Enemy>();
	public HashMap<String,Enemy> desertList = new HashMap<String,Enemy>();
	public HashMap<String,Enemy> oceanList = new HashMap<String,Enemy>();
	public HashMap<String,Enemy> tundraList = new HashMap<String,Enemy>();
	public HashMap<String,Enemy> volcanoList = new HashMap<String,Enemy>();
	
	public EnemyList(){
		weapon_catalog = new WeaponList();
		//populate the directory
		lookup.put(terrain.plains, plainsList);
		lookup.put(terrain.forest, forestList);
		lookup.put(terrain.mountain, mountainList);
		lookup.put(terrain.swamp, swampList);
		lookup.put(terrain.desert, desertList);
		lookup.put(terrain.ocean, oceanList);
		lookup.put(terrain.tundra, tundraList);
		lookup.put(terrain.volcano, volcanoList);
		
		//populate each terrain
		
		//Plains List								  [name]      [hp]  [atk] [def][armr] [init]            [weapon]
		plainsList.put("filthy ruffian",new Enemy("Filthy Ruffian", 6, 	-2,    0,    0,    0,   weapon_catalog.weaponMap.get("dagger")));
		plainsList.put("coyote", 		new Enemy("Coyote",			4, 	-3,    0,    0,    0,   new Weapon("claws", weaponType.one_handed, 1, 3, 10)));
		plainsList.put("bandit",		new Enemy("Bandit",			8, 	-1,    0,    0,    0,   weapon_catalog.weaponMap.get("hand axe")));
		plainsList.put("wooden barrel", new Enemy("Wooden Barrel",	2, 	-5,    0,    1,    0,   new Weapon("stout body", weaponType.one_handed, -5, 1, 10)));
		plainsList.put("feral cat", 	new Enemy("Feral Cat",		3, 	-4,    0,    0,    0,   new Weapon("claws", weaponType.one_handed, 1, 2, 10)));
		plainsList.put("bison", 		new Enemy("Bison", 			12,	 0,    0,    0,    0,   new Weapon("horns", weaponType.one_handed, 3, 5, 10)));
		plainsList.put("ogre",  		new Enemy("Ogre", 			10,  0,    0,    0,    0,   weapon_catalog.weaponMap.get("cudgel")));
		plainsList.put("pack of dogs",  new Enemy("wild dog", 		8, 	 1,    0,    0,    0,   new Weapon("teeth", weaponType.one_handed, 2, 4, 10)));
		plainsList.put("worg",  		new Enemy("Cursed Warg", 	9,	-1,    0,    0,    0,   new Weapon("teeth", weaponType.one_handed, 2, 6, 10)));
		plainsList.put("chupacabro",  	new Enemy("Chupacabro",		6, 	-2,    0,    0,    0,   new Weapon("fangs", weaponType.one_handed, 3, 5, 10)));
		plainsList.put("giant",  		new Enemy("Giant",			12,  2,    0,    0,    0,   weapon_catalog.weaponMap.get("maul")));
		plainsList.put("shadow demon",  new Enemy("Shadow Demon",	8, 	 3,    0,    0,    0,   new Weapon("spectral claw", weaponType.one_handed, 3, 7, 10)));
		plainsList.put("elephant",new Enemy("Great Plains Elephant",16,  0,    0,    1,    0,   new Weapon("tusks", weaponType.one_handed, 4, 10, 10)));
		plainsList.put("goblin king",   new Enemy("Goblin King", 	14,  2,    0,    1,    0,   weapon_catalog.weaponMap.get("glaive")));
		plainsList.put("plainswalker",  new Enemy("The Plainswalker",12, 4,    0,    0,    4,   new Weapon("rapier", weaponType.one_handed, 2, 14, 10)));
		plainsList.putAll(enemyMap); 		//put all enemies in the enemyMap
	}
	
	//Randomly selects enemy from a location
	public Enemy randomByLocation(Tile.terrain area){
		HashMap<String,Enemy> map = lookup.get(area);						//select the correct area list
		keysAsArray = new ArrayList<String>(map.keySet());	//get the set of keys
		
		return map.get(keysAsArray.get(r.nextInt(keysAsArray.size())));	//choose a random enemy from that list
		
	}
	
	public Enemy selectByName(String nom){
		if(enemyMap.containsKey(nom)){ 
			return enemyMap.get(nom); 
		}else{ 
			return new Enemy(); 
		}
	}
}



