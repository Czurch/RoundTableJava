package com.czurch.rtl.GameModes;
import java.util.Scanner;

import com.czurch.rtl.mechanics.Player;
import com.czurch.rtl.mechanics.coreMath;
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
		player = player.createPlayer();						//create Player
		player.statSumm();							//show a summary of their stats
		EnemyList catalog = new EnemyList();		//load the catalog
		
		introduction(); 							//offers an introduction to the dungeon crawler game mode
		
		while(player.isAlive()){					//continue fighting while player lives
			totalEncounters++;
			Enemy jimbob = catalog.randomByLocation(biome.grassland);				//Create new enemy
			jimbob.randomizeHitPoints();
			enemyIntro(jimbob);
			
			Fight f = new Fight(player, jimbob);	//Fight the enemy
			player.statSumm();
		}
	}
	
	
	public void introduction()
	{
		System.out.println("You enter the gates of the abandoned city..");
		System.out.println("Beyond you lies what seems like an endless expanse of dark alleys and crumbling ruins.\n" + 
				"Surely, great evils await in the heart of the city.. But the rewards are sure to be even greater.\n\n");
	}
	
	public void enemyIntro(Enemy e)
	{
		switch(coreMath.randomNumberBetween(1, 10))
		{
		case 1:
			System.out.println("Suddenly. A " + e.getName() + " leaps out of the shadows!");
			break;
		case 2:
			System.out.println("A " + e.getName() + " emerges. Prepare yourself.");			
			break;
		case 3:
			System.out.println("A " + e.getName() + " has crossed your path. Get ready to fight.");
			break;
		case 4:
			System.out.println("Watch out! A " + e.getName() + " has appeared!");
			break;
		case 5:
			System.out.println("A " + e.getName() + " approaches. It is hostile.");
			break;
		case 6:
			System.out.println("Dear god.. it's a " + e.getName());
			break;
		case 7:
			System.out.println("You reach a dead end and realize a " + e.getName() + " has been following you. You have no choice but to fight.");
			break;
		case 8:
			System.out.println("Beside you some rubble moves. A " + e.getName() + " jumps out!");
			break;
		case 9:
			System.out.println("Prepare yourself. A " + e.getName() + " has engaged you.");
			break;
		case 10:
			System.out.println("You notice the " + e.getName() + " just before it strikes. But it's prepared to fight.");
			break;
		}
	}

}
