package com.czurch.rtl.mechanics.Items;

import java.util.Iterator;

import com.czurch.rtl.mechanics.Character;

/*
 * Equipment adds buffs as it's Active
 * when active effects are applied 
 * they are applied permanently until
 * the user "unequips the item" by using Active again
 */

public class Equipment extends Item {
	
	public boolean isEquipped;

	public Equipment()
	{
		super();
		isEquipped = false;
	}
	
	public Equipment(String nom, int val, int wght)
	{
		super(nom, val, wght);
		isEquipped = false;
	}
	
	@Override
	public void Active(Character user)
	{
		if(isEquipped == false)
		{
			System.out.println("Equipping " + this.name);
			for(Iterator<Effect> i = activeEffects.iterator(); i.hasNext();)
	    	{
	    		Effect e = i.next();
	    		if(e != null){
	    			user.addBuff(e);
	    		}
	    	}
		}
		else
		{
			System.out.println("Unequipping " + this.name);
			for(Iterator<Effect> i = activeEffects.iterator(); i.hasNext();)
	    	{
	    		Effect e = i.next();
	    		if(e != null){
	    			user.removeBuff(e);
	    		}
	    	}
		}
	}	
}
