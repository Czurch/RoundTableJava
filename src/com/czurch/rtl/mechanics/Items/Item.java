package com.czurch.rtl.mechanics.Items;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.czurch.rtl.mechanics.Character;
import com.czurch.rtl.mechanics.coreMath;
import com.czurch.rtl.mechanics.Items.Effect.effectors;

public class Item {
	public String name;
	public String description;
	public int value,
	weight;
	
	public List<Effect> activeEffects = new ArrayList<Effect>();
	public List<Effect> passiveEffects = new ArrayList<Effect>();
	public boolean 		passiveApplied;
	
	Item(){
		name = "placeholder";
		value = 0;
		weight = 0;
		passiveApplied = false;
	}
	
	Item(String nom, int val, int wght)
	{
		name = nom;
		value = val;
		weight = wght;
		passiveApplied = false;
	}

	public void Throw(Character user, Character target)
	{
    	System.out.println("rolling a D20 vs your opponents Defense");
    	int rollVsDEF = coreMath.rollD20();												// roll vs enemy def
    	System.out.println("You rolled a " + rollVsDEF);
    	
		target.takeDamage(user, weight, rollVsDEF);
		user.remove(this);
	}
	
	public void Active(Character user)
	{
		for(Iterator<Effect> i = activeEffects.iterator(); i.hasNext();)
    	{
    		Effect e = i.next();
    		if(e != null){
    			user.addEffect(e);
    		}
    	}
		user.remove(this);
	}
	
	public void Passive(Character user)
	{
		if(passiveApplied = false)
		{
			for(Iterator<Effect> i = passiveEffects.iterator(); i.hasNext();)
	    	{
	    		Effect e = i.next();
	    		if(e != null){
	    			user.addBuff(e);
	    			passiveApplied = true;
	    		}
	    	}
		}
		else
		{
			for(Iterator<Effect> i = passiveEffects.iterator(); i.hasNext();)
	    	{
	    		Effect e = i.next();
	    		if(e != null){
	    			user.removeBuff(e);
	    			passiveApplied = false;
	    		}
	    	}
		}
	}
	
	public void addActive(effectors effectToApply ,int modifier, int time)
	{
		activeEffects.add(new Effect(effectToApply ,modifier,time));
	}
	
	public void addPassive(effectors effectToApply ,int modifier, int time)
	{
		passiveEffects.add(new Effect(effectToApply, modifier, time));
	}
}
