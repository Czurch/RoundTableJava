package com.czurch.rtl.mechanics.Items;

import com.czurch.rtl.mechanics.coreMath;

public class Weapon extends Item{
	public enum weaponType{
		two_handed,
		one_handed,
		ranged,
		shield
	}
	
	public String name;
	protected weaponType type;
	protected int 	minDmg,
					maxDmg;
	
	public Weapon(String nom, weaponType typ, int min, int max, int val){
		this.name = nom;
		this.type = typ;
		this.minDmg = min;
		this.maxDmg = max;
		this.value = val;
	}
	
	public int active() {
		return coreMath.randomNumberBetween(minDmg, maxDmg);
	}

	public void passive() {
		
	}

}
