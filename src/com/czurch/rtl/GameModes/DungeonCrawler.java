package com.czurch.rtl.GameModes;
import java.util.Scanner;

import com.czurch.rtl.mechanics.Player;
import com.czurch.rtl.mechanics.Professions.*;
import com.czurch.rtl.mechanics.Enemy;
import com.czurch.rtl.mechanics.EnemyList;
import com.czurch.rtl.mechanics.Fight;

import com.czurch.rtl.mechanics.Worldbuilding.Tile.biome;

public class DungeonCrawler {
	static Scanner keyboard = new Scanner( System.in );
	
	int totalEncounters = 0;
	
	public DungeonCrawler(){
		Player player = new Player();
		player.createPlayer();						//create Player
		player.statSumm();							//show a summary of their stats
		EnemyList catalog = new EnemyList();		//load the catalog
		
		introduction(); 							//offers an introduction to the dungeon crawler game mode
		
		while(player.isAlive()){					//continue fighting while player lives
			totalEncounters++;
			Enemy jimbob = catalog.randomByLocation(biome.grassland);				//Create new enemy
			jimbob.randomizeHitPoints();
			Fight f = new Fight(player, jimbob);	//Fight the enemy
			player.statSumm();
		}
	}
	
	
	public void introduction()
	{
		System.out.println("You enter the gates of the abandoned city..");
		System.out.println("Beyond you lies what seems like a limitless expanse of dark alleys and crumbling ruins.\n" + 
				"Surely, great evils await in the heart of the city.. But the rewards are sure to be even greater.");
	}

}
