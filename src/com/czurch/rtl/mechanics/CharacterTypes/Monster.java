package com.czurch.rtl.mechanics.CharacterTypes;

import com.czurch.rtl.mechanics.Character;

public class Monster extends Character {
	
	int tier;
	
	Monster(String nam, int hp, int atk, int def, int armr, int init, int tier, 
			int str, int con, int agil, int intel, int arc, int wit, int charis)
	{
		super(str, con, agil, intel, arc, wit, charis);
		health 		= hp;
		maxHealth	= hp;
		attack 		= atk;
		defence 	= def;
		armor 		= armr;
		initiative 	= 0;
		initMod 	= init;
		alive 		= true;
		this.tier 	= tier;
	}
}
