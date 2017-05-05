package mechanics.Worldbuilding;

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
    	mire
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
	public Tile s_neighbor;
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
}
