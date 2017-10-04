package com.czurch.rtl.mechanics.Worldbuilding;
import java.util.ArrayList;
import java.util.List;

public class Civilization {
	int id;
	int poulation;
	int food;
	int lumber;
	int stone;
	int gold;
	
	// Tiles Claimed
	// Tiles Known
	
	List<Civilization> Allies = new ArrayList<Civilization>();
	List<Civilization> Enemies = new ArrayList<Civilization>();
	
	//Government types
	int social_score;
	// 0 = Isolationist
	// 1 = Willing to Ally
	// 2 = Integrationist
	int govt_type;
	// 0 = Oligarchy
	// 1 = Autocracy
	// 3 = Democracy
	
	
	public Civilization(int id){
		this.id = id;
		this.social_score = (int)(Math.random() * 3);
		this.govt_type = (int)(Math.random() * 3);
	}
	
}
