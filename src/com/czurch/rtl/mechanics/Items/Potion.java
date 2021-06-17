package com.czurch.rtl.mechanics.Items;

import java.util.Iterator;

import com.czurch.rtl.mechanics.Character;

/*
 * Potions are a unique type of item
 * they can be activated like a general item
 * but they can also be thrown at enemies to apply the effect shown.
 */

public class Potion extends Item{

	public Potion()
	{
		super();
	}
	
	public Potion(String nom, int val, int wght)
	{
		super(nom, val, wght);
	}
	
	@Override
	public void Throw(Character user, Character target)
	{
		for(Iterator<Effect> i = activeEffects.iterator(); i.hasNext();)						// iterate through item's effects
    	{
    		Effect e = i.next();
    		if(e != null){
    			target.applyEffect(e);															// apply those effects to the target
    			System.out.println("The contents of the potion splash onto " + target.name);
    			// <--  insert dialogue explaining what stats were affected 
    		}
    	}
		user.remove(this);
	}
}
