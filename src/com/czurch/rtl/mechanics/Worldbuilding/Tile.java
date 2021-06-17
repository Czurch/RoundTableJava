package com.czurch.rtl.mechanics.Worldbuilding;

public class Tile {
    public enum safety_rating{
    	peaceful,
    	calm,
    	wild,
    	unsafe,
    	dangerous,
    	treacherous
    }
	
    public enum biome{
    	grassland,
    	forest,
    	rainforest,
    	mountain,
    	swamp,
    	desert,
    	ocean,
    	tundra,
    	jungle,
    	mire,
    	water,
    	empty
    }
    
    byte resource;
	// Resource Map Catalog
	// 0 = wild
	// 1 = farm
	// 2 = lumber
	// 3 = stone
    
	public int x,y;
	public int id;
	public float weight;
	public safety_rating rating;
	public biome terrain;
	public Tile n_neighbor;
	public Tile ne_neighbor;
	public Tile nw_neighbor;
	public Tile s_neighbor;
	public Tile se_neighbor;
	public Tile sw_neighbor;
	public Tile e_neighbor;
	public Tile w_neighbor;
	
	public Tile(int ex, int why, safety_rating rat, biome t){
		x = ex;
		y = why;
		rating = rat;
		terrain = t;
	}
	
	public void setNeighbors(Tile north, Tile south, Tile east, Tile west)
	{
		n_neighbor = north;
		s_neighbor = south;
		e_neighbor = east;
		w_neighbor = west;
	}
	public void setNeighbors(Tile north, Tile northeast, Tile northwest, Tile south, 
							 Tile southeast, Tile southwest, Tile east, Tile west)
	{
		n_neighbor = north;
		ne_neighbor = northeast;
		nw_neighbor = northwest;
		s_neighbor = south;
		se_neighbor = southeast;
		sw_neighbor = southwest;
		e_neighbor = east;
		w_neighbor = west;
	}
}
