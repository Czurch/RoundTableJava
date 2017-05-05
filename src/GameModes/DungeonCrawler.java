package GameModes;
import java.util.Scanner;
import mechanics.*;
import mechanics.Player.profession;
import mechanics.Professions.*;
import mechanics.Worldbuilding.Tile.biome;
import mechanics.Worldbuilding.Tile.terrain;

public class DungeonCrawler {
	static Scanner keyboard = new Scanner( System.in );
	
	int totalEncounters = 0;
	
	public DungeonCrawler(){
		Player player = createPlayer();				//create Player
		player.statSumm();							//show a summary of their stats
		EnemyList catalog = new EnemyList();		//load the catalog
		
		while(player.isAlive()){					//continue fighting while player lives
			totalEncounters++;
			Enemy jimbob = catalog.randomByLocation(biome.grassland);				//Create new enemy
			jimbob.randomizeHitPoints();
			Fight f = new Fight(player, jimbob);	//Fight the enemy
			player.statSumm();
		}
	}
	
	//The Character creation method
	static public Player createPlayer(){
		Player player = new Player();
		System.out.println("Hello traveler.. what is your name?");			//Name your character
		String name = keyboard.nextLine();
		System.out.println("What class would you like to play?");			//Select your class
		String choice = keyboard.nextLine();
		choice = choice.toLowerCase();
		profession p = profession.valueOf(choice);
		switch(p){
		case alchemist:
		    player = new Alchemist(name); 
		    break;
		case barbarian:
		    player = new Barbarian(name);
		    break;
		case drunkard:
		    player = new Drunkard(name);
		    break;
		case knight:
		    player = new Knight(name);
		    break;
		case priest:
		    player = new Priest(name);
		    break;
		case ranger:
		    player = new Ranger(name);
		    break;
		case rogue: 
		    player = new Rogue(name);
		    break;
		case scribe:
		    player = new Scribe(name);
		    break;
		default:
			player = new Player();
			break;
		}
		return player;
	}
	

}
