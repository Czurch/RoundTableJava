package com.czurch.rtl.mechanics.Items;

import java.util.Iterator;

import com.czurch.rtl.mechanics.Character;

public class Gem extends Item {

	boolean isHeld;
	
	public Gem()
	{
		super();
		isHeld = false;
	}
	
	public Gem(String nom, int val, int wght)
	{
		super(nom, val, wght);
		isHeld = false;
	}
	
	@Override
	public void Passive(Character user)
	{
		if(isHeld == false)
		{
			System.out.println("Holding the " + this.name + ", you feel it's power course through you.");
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
			System.out.println("The power of the " + this.name + " is gone.");
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
