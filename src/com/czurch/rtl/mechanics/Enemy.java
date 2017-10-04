package com.czurch.rtl.mechanics;

import com.czurch.rtl.mechanics.Items.Weapon;

public class Enemy extends Character{
		
		//determines difficulty of the enemy
		int difficulty;
		
	    public Enemy(){
	    	name = "Generic Enemy";
	    	health = 8;
	    	maxHealth = 8;
	    	attack = 0;
	    	defence = 0;
	    	armor = 0;
	    	initiative = 0;
	    	initMod = 0;
	    	alive = true;
	    	difficulty = 1;
	    }
	    
	    public Enemy(String nam, int HP, int ATK, int DEF, int ARMR, int INIT, Weapon weapon){
	    	name = nam;
	    	health = HP;
	    	maxHealth = HP;
	    	attack = ATK;
	    	defence = DEF;
	    	armor = ARMR;
	    	initiative = 0;
	    	initMod = INIT;
	    	alive = true;
	    	difficulty = 1;
	    	weaponEquipped = weapon;
	    }
	    
	    //Randomizes the base value of hitpoints
	    public void randomizeHitPoints(){
	    	int hp = coreMath.randomNumberBetween(4, 12);
	    	health = hp;
	    	maxHealth = hp;	
	    }
}

