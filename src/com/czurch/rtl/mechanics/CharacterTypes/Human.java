package com.czurch.rtl.mechanics.CharacterTypes;

import com.czurch.rtl.mechanics.Character;
import com.czurch.rtl.mechanics.coreMath;
import com.czurch.rtl.mechanics.Items.ItemList;
import com.czurch.rtl.mechanics.Items.Weapon;
import com.czurch.rtl.mechanics.Items.Weapon.weaponType;

public class Human extends Character {

	int tier;
	
	public Human(ItemList item_catalog, int t)
	{
		// we randomize this character's interface stats
		super(	coreMath.randomNumberBetween(1, 20),
				coreMath.randomNumberBetween(1, 20),
				coreMath.randomNumberBetween(1, 20),
				coreMath.randomNumberBetween(1, 20),
				coreMath.randomNumberBetween(1, 20),
				coreMath.randomNumberBetween(1, 20),
				coreMath.randomNumberBetween(1, 20));
		
		health 		= coreMath.randomNumberBetween(8, 12);
    	maxHealth	= health;
    	attack 		= 0;
    	defence 	= 0;
    	armor 		= 0;
    	initiative 	= 0;
    	initMod 	= 0;
    	alive 		= true;
    	tier 		= t;

		// give our human a random name
		name = HumanNameGen.nameGen();
		
		// give it a weapon
		weaponEquipped = new Weapon("bare hands", weaponType.two_handed, 1, 2, 0);
		
		// give it armor or clothes
		
		// give it some loot
		int x = coreMath.randomNumberBetween(tier, 5);
		for(int i = 0; i < x; i++)
		{
			this.pickUp(item_catalog.getRandomByTier(tier));
		}
	}
	
	public Human(String name)
	{
		// we randomize this character's interface stats
		super(	coreMath.randomNumberBetween(1, 20),
				coreMath.randomNumberBetween(1, 20),
				coreMath.randomNumberBetween(1, 20),
				coreMath.randomNumberBetween(1, 20),
				coreMath.randomNumberBetween(1, 20),
				coreMath.randomNumberBetween(1, 20),
				coreMath.randomNumberBetween(1, 20));
	}
	
	public void fight()
	{
		
	}
	
	public void talk()
	{
		
	}
	
}
