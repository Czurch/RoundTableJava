package com.czurch.rtl.mechanics.Items;
import com.czurch.rtl.mechanics.Character;
import com.czurch.rtl.mechanics.Items.Effect.effectors;

public abstract class Item {
	public String name;
	protected int value,
	weight;
	
	public Effect[] activeEffects = new Effect[5];
	public Effect[] passiveEffects = new Effect[5];
	
	Item(){
		name = "placeholder";
		value = 0;
		weight = 0;
	}
	
	Item(String nom, int val, int wght, Effect[] ACTeffectors, Effect[] PSVeffectors)
	{
		name = nom;
		value = val;
		weight = wght;
		activeEffects = ACTeffectors;
		passiveEffects = PSVeffectors;
	}
	
	public void Throw(Character target)
	{
		target.takeDamage(weight);
	}
	
	public void Active(Character user)
	{
		for(int i = 0; i >=5; i++)
		{
			Effect e = activeEffects[i];
			if(e != null)
			{
				user.addEffect(e, e.time);
			}
		}
	}
	
	public void Passive()
	{
		
	}
}
