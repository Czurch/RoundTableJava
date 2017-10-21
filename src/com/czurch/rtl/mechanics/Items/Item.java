package com.czurch.rtl.mechanics.Items;
import com.czurch.rtl.mechanics.Character;
import com.czurch.rtl.mechanics.Items.Effect.effectors;

public abstract class Item {
	public String name;
	protected int value,
	weight;
	
	public effectors[] activeEffects = new effectors[5];
	public effectors[] passiveEffects = new effectors[5];
	
	Item(){
		name = "placeholder";
		value = 0;
		weight = 0;
	}
	
	Item(String nom, int val, int wght, effectors[] ACTeffectors, effectors[] PSVeffectors)
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
		//user.applyEffect()
	}
	
	public void Passive()
	{
		
	}
}
