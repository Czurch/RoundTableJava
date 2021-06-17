package com.czurch.rtl.GameModes;
import java.io.IOException;
import java.util.Scanner;

import com.czurch.rtl.mechanics.Player;
import com.czurch.rtl.mechanics.coreMath;
import com.czurch.rtl.mechanics.CharacterTypes.Human;
import com.czurch.rtl.mechanics.Items.Item;
import com.czurch.rtl.mechanics.Items.ItemList;
import com.czurch.rtl.mechanics.Professions.*;
import com.czurch.rtl.mechanics.Enemy;
import com.czurch.rtl.mechanics.EnemyList;
import com.czurch.rtl.mechanics.Fight;

import com.czurch.rtl.mechanics.Worldbuilding.Tile.biome;

public class DungeonCrawler {
	static Scanner keyboard = new Scanner( System.in );
	
	int totalEncounters = 0;
	
	public DungeonCrawler() throws IOException{
		ItemList item_catalog 	= new ItemList();				//load the item catalog
		EnemyList enemy_catalog = new EnemyList();				//load the enemy catalog
		Player player 			= new Player();
		player 					= player.createPlayer();		//create Player
		player.statSumm();										//show a summary of their stats
		
		introduction(); 							//offers an introduction to the dungeon crawler game mode
		
		/*
		 * DungeonCrawler will most likely act as a place to test out new changes to combat.
		 * the following should be added to spice up the mode:
		 * 
		 * -randomize the biome every couple of rounds
		 * -increase the difficulty of enemies as the player progresses
		 */
		
		
		
		
		while(player.isAlive()){					// continue fighting while player lives
			totalEncounters++;
			//Enemy jimbob = enemy_catalog.randomByLocation(biome.grassland);				// Create new enemy
			Human jimbob = new Human(item_catalog, coreMath.randomNumberBetween(1, 3));		// Create a random Human
			//jimbob.randomizeHitPoints();
			enemyIntro(jimbob);
			
			Fight f = new Fight(player, jimbob);	// create a new Fight containing player and enemy 
			player.statSumm();
		}
		
		// print end game stats 
	}
	
	
	public void introduction()
	{
		System.out.println("You enter the gates of the abandoned city..");
		System.out.println("Beyond you lies what seems like an endless expanse of dark alleys and crumbling ruins.\n" + 
				"Surely, great evils await in the heart of the city.. But the rewards are sure to be even greater.\n\n");
	}
	
	public void enemyIntro(Human e)
	{
		switch(coreMath.randomNumberBetween(1, 10))
		{
		case 1:
			System.out.println("Suddenly. A " + e.name + " leaps out of the shadows!");
			break;
		case 2:
			System.out.println("A " + e.name + " emerges. Prepare yourself.");			
			break;
		case 3:
			System.out.println("A " + e.name + " has crossed your path. Get ready to fight.");
			break;
		case 4:
			System.out.println("Watch out! A " + e.name + " has appeared!");
			break;
		case 5:
			System.out.println("A " + e.name + " approaches. It is hostile.");
			break;
		case 6:
			System.out.println("Dear god.. it's a " + e.name);
			break;
		case 7:
			System.out.println("You reach a dead end and realize a " + e.name + " has been following you. You have no choice but to fight.");
			break;
		case 8:
			System.out.println("Beside you some rubble moves. A " + e.name + " jumps out!");
			break;
		case 9:
			System.out.println("Prepare yourself. A " + e.name + " has engaged you.");
			break;
		case 10:
			System.out.println("You notice the " + e.name + " just before it strikes. But it's prepared to fight.");
			break;
		}
	}

}
