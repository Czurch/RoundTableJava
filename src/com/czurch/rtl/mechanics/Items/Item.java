package com.czurch.rtl.mechanics.Items;

public abstract class Item {
	public String name;
	protected int value,
	weight;
	
	Item(){
		name = "placeholder";
		value = 0;
		weight = 0;
	}
	
	Item(String nom, int val, int wght)
	{
		name = nom;
		value = val;
		weight = wght;
	}
}
