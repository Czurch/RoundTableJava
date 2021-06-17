package com.czurch.rtl.mechanics.Worldbuilding;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.czurch.rtl.mechanics.Worldbuilding.MapGen;
import com.czurch.rtl.mechanics.Worldbuilding.Tile.*;

public class WorldMap 
{	
	public static int WORLD_HEIGHT = 512;
	public static int WORLD_WIDTH = 512;
	public static int EQUATOR = WORLD_HEIGHT/2;
	public static int TROPIC = (EQUATOR)/100 * 30;
	public static int SUB_TROPIC = (EQUATOR)/100 * 50;
	public static int TEMPERATE = (EQUATOR)/100 * 80;
	public static int FRIGID = EQUATOR;
	
	//COLORS
	public static int GRASSLAND_COLOR 	= 0x1CE468;
	public static int FOREST_COLOR		= 0x006326;
	public static int RAINFOREST_COLOR	= 0x40B46C;
	public static int MOUNTAIN_COLOR	= 0x632300;
	public static int SWAMP_COLOR		= 0x352319;
	public static int DESERT_COLOR		= 0xECA076;
	public static int OCEAN_COLOR 		= 0x5564FF;
	public static int TUNDRA_COLOR		= 0xCDD5C5;
	public static int JUNGLE_COLOR		= 0x3A5534;
	public static int MIRE_COLOR		= 0xA78774;
	public static int TREASURE_COLOR    = 0xFFF600;
	
	Tile[][] world_map = new Tile[(WORLD_WIDTH)][(WORLD_HEIGHT)];
	
	public byte resource_map[][];
	// Resource Map Catalog
	// 0 = wild
	// 1 = farm
	// 2 = lumber mill
	// 3 = quarry
	// 4 = village
	// 5 = town
	// 6 = road
	
	public WorldMap()
	{
		generate_tileset();
		//SimplexTerrain(2);
		FractalTerrain();
		//initialize all water and mountain tiles
		determine_water_n_mountains();
		//draw rivers towards oceans
		create_rivers();
		//seed and grow biomes
		create_climates();
	}
	
	// FUNCTION: generate_tileset
	// INFO: 	 creates instances of tiles at each coordinate
	public void generate_tileset()
	{
		for(int x = 0; x < WORLD_WIDTH; x++)
		{			
			for(int y = 0; y < WORLD_HEIGHT; y++)
			{
				this.world_map[x][y] = new Tile(x,y,safety_rating.peaceful, biome.empty);
			}
		}
		determine_neighbors();
	}
	
	// FUNCTION: determine_neighbors
	// INFO: 	 sets the nearest neighbor for all Tiles on the world map
	public void determine_neighbors()
	{
		for(int x = 0; x < WORLD_WIDTH; x++)
		{			
			for(int y = 0; y < WORLD_HEIGHT; y++)
			{
				if(x == 0)
				{
					if(y == 0)
					{										
						this.world_map[x][y].setNeighbors(world_map[x][y+1],								//north
															world_map[x+1][y+1],							//northeast
															world_map[WORLD_WIDTH-1][y+1],					//northwest
															world_map[y_neighbor_calc(x)][y],				//south
															world_map[y_neighbor_calc(x+1)][y],				//southeast
															world_map[y_neighbor_calc(WORLD_WIDTH-1)][y],	//southwest
															world_map[x+1][y],								//east
															world_map[WORLD_WIDTH-1][y]);					//west
					}	
					else if(y == WORLD_HEIGHT-1)
					{
						this.world_map[x][y].setNeighbors(world_map[y_neighbor_calc(x)][y],
															world_map[y_neighbor_calc(x+1)][y], 
															world_map[y_neighbor_calc(WORLD_WIDTH-1)][y], 
															world_map[x][y-1],
															world_map[x+1][y-1], 
															world_map[WORLD_WIDTH-1][y-1], 
															world_map[x+1][y], 
															world_map[WORLD_WIDTH-1][y]);
					}
					else
					{
						this.world_map[x][y].setNeighbors(world_map[x][y+1],
															world_map[x+1][y+1],
															world_map[WORLD_WIDTH-1][y+1],
															world_map[x][y-1],
															world_map[x+1][y-1],
															world_map[WORLD_WIDTH-1][y-1],
															world_map[x+1][y],
															world_map[WORLD_WIDTH-1][y]);
					}
				}
				else if(x == WORLD_WIDTH-1)
				{
					if(y == 0)
					{
						this.world_map[x][y].setNeighbors(world_map[x][y+1],
															world_map[0][y+1],
															world_map[x-1][y+1],
															world_map[y_neighbor_calc(x)][y],
															world_map[y_neighbor_calc(0)][y],
															world_map[y_neighbor_calc(x-1)][y],
															world_map[0][y],
															world_map[x-1][y]);
					}
					else if(y == WORLD_HEIGHT-1)
					{
						this.world_map[x][y].setNeighbors(world_map[y_neighbor_calc(x)][y],
															world_map[y_neighbor_calc(0)][y],
															world_map[y_neighbor_calc(x-1)][y],
															world_map[x][y-1],
															world_map[0][y-1],
															world_map[x-1][y-1],
															world_map[0][y],
															world_map[x-1][y]);
					}
					else
					{
						this.world_map[x][y].setNeighbors(world_map[x][y+1],
															world_map[0][y+1],
															world_map[x-1][y+1],
															world_map[x][y-1],
															world_map[0][y-1],
															world_map[x-1][y-1],
															world_map[0][y],
															world_map[x-1][y]);
					}			
				}
				else
				{
					if(y == 0)
					{
						this.world_map[x][y].setNeighbors(world_map[x][y+1],
															world_map[x+1][y+1],
															world_map[x-1][y+1],
															world_map[y_neighbor_calc(x)][y],
															world_map[y_neighbor_calc(x+1)][y],
															world_map[y_neighbor_calc(x-1)][y],
															world_map[x+1][y],
															world_map[x-1][y]);
					}
					else if(y == WORLD_HEIGHT-1)
					{
						this.world_map[x][y].setNeighbors(world_map[y_neighbor_calc(x)][y],
															world_map[y_neighbor_calc(x+1)][y],
															world_map[y_neighbor_calc(x-1)][y],
															world_map[x][y-1],
															world_map[x+1][y-1],
															world_map[x-1][y-1],
															world_map[x+1][y],
															world_map[x-1][y]);
					}
					else
					{
						this.world_map[x][y].setNeighbors(world_map[x][y+1],
															world_map[x+1][y+1],
															world_map[x-1][y+1],
															world_map[x][y-1],
															world_map[x+1][y-1],
															world_map[x-1][y-1],
															world_map[x+1][y],
															world_map[x-1][y]);
					}
				}
			}// end y for loop
		}// end x for loop	
	}// end determine_neighbors 
	
	// FUNCTION: y_neigbor_calc
	// INFO:	 jumps exactly halfway around the world
	public int y_neighbor_calc(int x)
	{
		return (x + (WORLD_WIDTH/2)) % (WORLD_WIDTH-1);
	}
	
	// FUNCTION: display_height_map
	// INFO:	 prints out a primitive version of the map
	public void display_height_map()
	{
		BufferedImage img = new BufferedImage(WORLD_WIDTH, WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		int[] pixels = new int[WORLD_WIDTH * WORLD_HEIGHT];
		for (int y = 0; y < WORLD_HEIGHT; y++) {
			for (int x = 0; x < WORLD_WIDTH; x++) {
				int i = x + y * WORLD_WIDTH;
				if(this.world_map[x][y].weight < 0.0)
				{
					if(this.world_map[x][y].weight < -0.5)
					{
						pixels[i] = 0x1212A5;		//deep water
					}else{
						pixels[i] = 0x2525F5;		//water
					}
				}
				else
				{
					if(this.world_map[x][y].weight > 0.5)
					{
						if(this.world_map[x][y].weight > 0.9){
							pixels[i] = 0xFFFFFF;		//snowy mountain
						}else{
							pixels[i] = 0x604040;		//elevated terrain
						}
					}else{
						pixels[i] = 0x208020;		//lowlands
					}
				}
			}
		}
		img.setRGB(0, 0, WORLD_WIDTH, WORLD_HEIGHT, pixels, 0, WORLD_WIDTH);
		JOptionPane.showMessageDialog(null, null, "Another", JOptionPane.YES_NO_OPTION, new ImageIcon(img.getScaledInstance(WORLD_WIDTH, WORLD_HEIGHT, Image.SCALE_AREA_AVERAGING)));
	}
	
	// FUNCTION: display_real_map
	// INFO:	 prints out a primitive version of the map
	public BufferedImage display_real_map()
	{
		BufferedImage img = new BufferedImage(WORLD_WIDTH, WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		int[] pixels = new int[WORLD_WIDTH * WORLD_HEIGHT];
		for (int x = 0; x < WORLD_WIDTH; x++) {
			for (int y = 0; y < WORLD_HEIGHT; y++) {
				int i = x + y * WORLD_WIDTH;
				if(this.world_map[x][y].terrain == biome.ocean)
				{				
					pixels[i] = OCEAN_COLOR;
				}
				else if(this.world_map[x][y].terrain == biome.water)
				{				
					pixels[i] = OCEAN_COLOR;
				}
				else if(this.world_map[x][y].terrain == biome.grassland)
				{
					pixels[i] = GRASSLAND_COLOR;
				}
				else if(this.world_map[x][y].terrain == biome.forest)
				{
					pixels[i] = FOREST_COLOR;
				}
				else if(this.world_map[x][y].terrain == biome.rainforest)
				{
					pixels[i] = RAINFOREST_COLOR;
				}
				else if(this.world_map[x][y].terrain == biome.mountain)
				{
					pixels[i] = MOUNTAIN_COLOR;
				}
				else if(this.world_map[x][y].terrain == biome.swamp)
				{
					pixels[i] = SWAMP_COLOR;
				}
				else if(this.world_map[x][y].terrain == biome.desert)
				{
					pixels[i] = DESERT_COLOR;
				}
				else if(this.world_map[x][y].terrain == biome.tundra)
				{
					pixels[i] = TUNDRA_COLOR;
				}
				else if(this.world_map[x][y].terrain == biome.mire)
				{
					pixels[i] = MIRE_COLOR;
				}
				else if(this.world_map[x][y].terrain == biome.jungle)
				{
					pixels[i] = JUNGLE_COLOR;
				}
				else
				{
					pixels[i] = TREASURE_COLOR;
				}
			}
		}
		img.setRGB(0, 0, WORLD_WIDTH, WORLD_HEIGHT, pixels, 0, WORLD_WIDTH);
		
		return img;
		//JOptionPane.showMessageDialog(null, null, "Another", JOptionPane.YES_OPTION, new ImageIcon(img.getScaledInstance(WORLD_WIDTH, WORLD_HEIGHT, Image.SCALE_AREA_AVERAGING)));
	}
	
	// FUNCTION: display_map_values
	// INFO:	 prints out the height value for each tile
	public void display_map_values()
	{
		for(int x = 0; x < WORLD_WIDTH; x++)
		{	
			System.out.println();
			for(int y = 0; y < WORLD_HEIGHT; y++)
			{	
				System.out.print(this.world_map[x][y].weight + " | ");
			}
		}
	}
	
	
	// FUNCTION: FractalTerrain
	// INFO:	 ramdomly varies the terrain on the world
	public void FractalTerrain()
	{
		MapGen generation1 = new MapGen(WORLD_WIDTH, WORLD_HEIGHT, 64);
		MapGen generation2 = new MapGen(WORLD_WIDTH, WORLD_HEIGHT, 64);
		for (int y = 0; y < WORLD_HEIGHT; y++) 
		{
			for (int x = 0; x < WORLD_WIDTH; x++) 
			{
				world_map[x][y].weight = (float) ((float)generation1.sample(x,y) - (generation2.sample(x,y)*0.5));
			}
		}
		for (int y = 0; y < WORLD_HEIGHT; y++) 
		{
			for (int x = 0; x < WORLD_WIDTH; x++) 
			{
				if(world_map[x][y].weight > 0.9)
					world_map[x][y].weight -= (Math.random() % 0.5);
			}
		}
	}
	
	// FUNCTION: create_climates
	// INFO:	 creates climates by seeding the world and allowing them to grow
	public void create_climates()
	{
		Random rand = new Random();
		boolean biomes_need_generation = true;
		
		//seed world with random biomes
		for(int i=0; i<2000; i++)
		{
			int temp_x = rand.nextInt(WORLD_WIDTH);
			int temp_y = rand.nextInt(WORLD_HEIGHT);
			while(world_map[temp_x][temp_y].terrain == biome.ocean ||
			      world_map[temp_x][temp_y].terrain == biome.mountain)
			{
				temp_x = rand.nextInt(WORLD_WIDTH);
				temp_y = rand.nextInt(WORLD_HEIGHT);
			}
				
			world_map[temp_x][temp_y].terrain = determine_biome(world_map[temp_x][temp_y].weight, Math.abs(EQUATOR - temp_y));
		}
		
		int total_retries = 0;
		while(biomes_need_generation)
		{
			total_retries++;
			//biomes_need_generation = false;
			
			Tile[][] nextGen = new Tile[(WORLD_HEIGHT)][(WORLD_WIDTH)];
			nextGen = world_map.clone();
			
			for (int y = 0; y < WORLD_HEIGHT; y++) 
			{
				for (int x = 0; x < WORLD_WIDTH; x++) 
				{
					Tile current_tile = world_map[x][y];
					Tile nextGen_tile = nextGen[x][y];
					if(current_tile.terrain != biome.empty &&
					   current_tile.terrain != biome.ocean &&
					   current_tile.terrain != biome.mountain)
					{
						if(current_tile.e_neighbor.terrain != biome.ocean &&
						   current_tile.e_neighbor.terrain != biome.mountain)
						{
							if((int)(Math.random() * 100) + 1 > 80)
								nextGen_tile.e_neighbor.terrain = current_tile.terrain;
						}
					    
						if(current_tile.w_neighbor.terrain != biome.ocean &&
						   current_tile.w_neighbor.terrain != biome.mountain)
						{
							if((int)(Math.random() * 100) + 1 > 80)
								nextGen_tile.w_neighbor.terrain = current_tile.terrain;							
						}
						
						if(current_tile.s_neighbor.terrain != biome.ocean &&
						   current_tile.s_neighbor.terrain != biome.mountain)
						{
							if((int)(Math.random() * 100) + 1 > 80)
								nextGen_tile.s_neighbor.terrain = current_tile.terrain;							
						}
						
						if(current_tile.n_neighbor.terrain != biome.ocean &&
						   current_tile.n_neighbor.terrain != biome.mountain)
						{
							if((int)(Math.random() * 100) + 1 > 80)
								nextGen_tile.n_neighbor.terrain = current_tile.terrain;							
						}
					}
					else
					{
						biomes_need_generation = true;
						if(total_retries > 100)
						{
							biomes_need_generation = false;
						}
						continue;
					}
				}
			}
			world_map = nextGen;
			//display_real_map();
		}
	}
	
	// FUNCTION: create_climates
	// INFO:	 creates climates and determines biome of each tile completely at random
	public void full_random_climates()
	{
		for (int y = 0; y < WORLD_HEIGHT; y++) 
		{
			for (int x = 0; x < WORLD_WIDTH; x++) 
			{
				world_map[x][y].terrain = determine_biome(world_map[x][y].weight, Math.abs(EQUATOR - y));
			}
		}
	}
	
	// FUNCTION: determine_water_n_mountains
	// INFO:	 sets all tiles below sea-level to water
	//           sets all tiles high enough to mountain
	public void determine_water_n_mountains()
	{
		//initialize all water and mountain tiles
		for (int y = 0; y < WORLD_HEIGHT; y++) 
		{
			for (int x = 0; x < WORLD_WIDTH; x++) 
			{
				if(world_map[x][y].weight <= 0) world_map[x][y].terrain = biome.ocean;			//   sea level -- < 0
				else if(world_map[x][y].weight > 0.9) world_map[x][y].terrain = biome.mountain;
				else world_map[x][y].terrain = biome.empty;
			}
		}
	}
	
	// FUNCTION: create_rivers
	// INFO:	 creates rivers from the mountains to the oceans
	public void create_rivers()
	{
		Random rand = new Random();
		Tile current_tile = new Tile(0,0, safety_rating.peaceful, biome.empty);
		Tile current_lowest = new Tile(0,0, safety_rating.peaceful, biome.empty);
		//seed world with 150 random river starts 
		for(int i=0; i<150; i++)
		{
			int temp_x = rand.nextInt(WORLD_WIDTH);
			int temp_y = rand.nextInt(WORLD_HEIGHT);
			while(world_map[temp_x][temp_y].weight > 0.97)
			{
				//continue randomizing until we find a mountain
				temp_x = rand.nextInt(WORLD_WIDTH);
				temp_y = rand.nextInt(WORLD_HEIGHT);
			}
			
			current_tile = world_map[temp_x][temp_y];
			
			for(int j=0; j<20; j++)
			{
				//get lowest neighbor
				if(current_tile.e_neighbor.terrain != biome.water ||
				   current_tile.e_neighbor.terrain == biome.ocean)
				{
					if(current_tile.e_neighbor.terrain == biome.ocean)
						break;
				    else
						current_lowest = current_tile.e_neighbor;
				}
				else
				{
					current_lowest = current_tile.w_neighbor;
				}
				
				if(current_tile.ne_neighbor.weight < current_lowest.weight &&
				   (current_tile.ne_neighbor.terrain != biome.water ||
				   current_tile.ne_neighbor.terrain == biome.ocean))
				{
					if(current_tile.ne_neighbor.terrain == biome.ocean)
						break;
					else
						current_lowest = current_tile.ne_neighbor;
				}
				if(current_tile.nw_neighbor.weight < current_lowest.weight &&
				   (current_tile.nw_neighbor.terrain != biome.water ||
				   current_tile.nw_neighbor.terrain == biome.ocean))
				{
					if(current_tile.nw_neighbor.terrain == biome.ocean)
						break;
					else
						current_lowest = current_tile.nw_neighbor;
				}
				if(current_tile.se_neighbor.weight < current_lowest.weight &&
				   (current_tile.se_neighbor.terrain != biome.water ||
				   current_tile.se_neighbor.terrain == biome.ocean))
				{
					if(current_tile.se_neighbor.terrain == biome.ocean)
						break;
					else
						current_lowest = current_tile.se_neighbor;
				}
				if(current_tile.sw_neighbor.weight < current_lowest.weight &&
				   (current_tile.sw_neighbor.terrain != biome.water ||
				   current_tile.sw_neighbor.terrain == biome.ocean))
				{
					if(current_tile.sw_neighbor.terrain == biome.ocean)
						break;
					else
						current_lowest = current_tile.sw_neighbor;
				}
				if(current_tile.w_neighbor.weight < current_lowest.weight &&
				   (current_tile.w_neighbor.terrain != biome.water ||
				   current_tile.w_neighbor.terrain == biome.ocean))
				{
					if(current_tile.w_neighbor.terrain == biome.ocean)
						break;
					else
						current_lowest = current_tile.w_neighbor;
				}
				if(current_tile.n_neighbor.weight < current_lowest.weight &&
				   (current_tile.n_neighbor.terrain != biome.water ||
				   current_tile.n_neighbor.terrain == biome.ocean))
				{
					if(current_tile.n_neighbor.terrain == biome.ocean)
						break;
					else
						current_lowest = current_tile.n_neighbor;
				}
				if(current_tile.s_neighbor.weight < current_lowest.weight &&
				   (current_tile.s_neighbor.terrain != biome.water ||
				   current_tile.s_neighbor.terrain == biome.ocean))
				{
					if(current_tile.s_neighbor.terrain == biome.ocean)
						break;
					else
						current_lowest = current_tile.s_neighbor;
				}
				
				current_tile = current_lowest;
				current_tile.terrain = biome.water;
			}
			
		}
	}
	
	// FUNCTION: create_climates
	// INFO:	 creates climates and determines biome of each tile
	public Tile.biome determine_biome(double weight, int lattitude)
	{
		WeightedTileSelector selector = new WeightedTileSelector();
		// TROPICAL BIOMES
		if(lattitude <= TROPIC)
		{
				 if(weight <= 0) return biome.ocean;			//   sea level -- < 0
			else if(weight <= 0.15){							//   lowlands -- < 0.15	
				selector.add(biome.swamp, 2);
				selector.add(biome.desert, 1);
				selector.add(biome.grassland, 1);
				selector.add(biome.rainforest, 2);
				return selector.getRandom();
			}else if(weight <= 0.4){							//   elevated -- < 0.4
				selector.add(biome.swamp, 1);
				selector.add(biome.desert, 2);
				selector.add(biome.grassland, 2);
				selector.add(biome.rainforest, 2);
				return selector.getRandom();
			}else if(weight <= 0.87){							//   foothills -- < 0.7
				selector.add(biome.desert, 1);
				selector.add(biome.grassland, 1);
				selector.add(biome.rainforest, 1);
				return selector.getRandom();
			}else{												//   high elevation -- greater than 0.7
				selector.add(biome.mountain, 4);
				selector.add(biome.grassland, 1);
				selector.add(biome.rainforest, 1);
				return selector.getRandom();
			}
		}
		//SUB-TROPICAL BIOMES
		else if(lattitude <= SUB_TROPIC)
		{
				 if(weight <= 0) return biome.ocean;			//   sea level -- < 0
			else if(weight <= 0.15){							//   lowlands -- < 0.15	
				selector.add(biome.swamp, 2);
				selector.add(biome.desert, 1);
				selector.add(biome.grassland, 1);
				selector.add(biome.forest, 1);
				return selector.getRandom();
			}else if(weight <= 0.4){							//   elevated -- < 0.4
				selector.add(biome.swamp, 2);
				selector.add(biome.forest, 1);
				selector.add(biome.desert, 1);
				selector.add(biome.grassland, 2);
				return selector.getRandom();
			}else if(weight <= 0.87){							//   foothills -- < 0.7
				selector.add(biome.forest, 1);
				selector.add(biome.grassland, 1);
				selector.add(biome.rainforest, 2);
				return selector.getRandom();
			}else{												//   high elevation -- greater than 0.7
				selector.add(biome.mountain, 4);
				selector.add(biome.forest, 1);
				selector.add(biome.rainforest, 1);
				return selector.getRandom();
			}
		}
		//TEMPERATE BIOMES
		else if(lattitude <= TEMPERATE)
		{
				 if(weight <= 0) return biome.ocean;			//   sea level -- < 0
			else if(weight <= 0.15){							//   lowlands -- < 0.15	
				selector.add(biome.swamp, 2);
				selector.add(biome.grassland, 1);
				selector.add(biome.forest, 1);
				return selector.getRandom();
			}else if(weight <= 0.4){							//   elevated -- < 0.4
				selector.add(biome.swamp, 1);
				selector.add(biome.forest, 2);
				selector.add(biome.desert, 1);
				selector.add(biome.grassland, 2);
				return selector.getRandom();
			}else if(weight <= 0.87){							//   foothills -- < 0.7
				selector.add(biome.forest, 1);
				selector.add(biome.grassland, 1);
				selector.add(biome.desert, 1);
				selector.add(biome.rainforest, 2);
				return selector.getRandom();
			}else{												//   high elevation -- greater than 0.7
				selector.add(biome.mountain, 4);
				selector.add(biome.forest, 1);
				selector.add(biome.rainforest, 1);
				return selector.getRandom();
			}
		}
		//FRIGID BIOMES
		else
		{
				 if(weight <= 0) return biome.ocean;			//   sea level -- < 0
			else if(weight <= 0.15){							//   lowlands -- < 0.15	
				selector.add(biome.mire, 2);
				selector.add(biome.tundra, 1);
				selector.add(biome.forest, 1);
				return selector.getRandom();
			}else if(weight <= 0.4){							//   elevated -- < 0.4
				selector.add(biome.mire, 1);
				selector.add(biome.forest, 2);
				selector.add(biome.tundra, 2);
				selector.add(biome.grassland, 1);
				return selector.getRandom();
			}else if(weight <= 0.87){							//   foothills -- < 0.7
				selector.add(biome.forest, 2);
				selector.add(biome.tundra, 2);
				return selector.getRandom();
			}else{												//   high elevation -- greater than 0.7
				selector.add(biome.mountain, 5);
				selector.add(biome.forest, 2);
				selector.add(biome.tundra, 2);
				return selector.getRandom();
			}
		}
	}
	
	public Tile.biome weighted_biome_selector(List<WeightedTile> items)
	{
		Random rand = new Random();
		int totalSum = 0;
		
		for(WeightedTile tile : items)
		{
			totalSum = totalSum + tile.relativeProb;
		}
		
		int index = rand.nextInt(totalSum);
		int sum = 0;
		int i = 0;
		while(sum < index)
		{
			sum = sum + items.get(i++).relativeProb;
		}
		return items.get(Math.max(0, i-1)).biom;
	}
	
	public class WeightedTile{
		Tile.biome biom;
		int relativeProb;
		
		public WeightedTile(Tile.biome bm, int w)
		{
			this.biom = bm;
			this.relativeProb = w;
		}
	}
	
	public class WeightedTileSelector
	{
		Random rand = new Random();
		int totalSum = 0;
		List<WeightedTile> items;
		
		public WeightedTileSelector()
		{
			items = new ArrayList<WeightedTile>();
		}
		
		
		public void add(Tile.biome b, int w)
		{
			items.add(new WeightedTile(b, w));
			totalSum = totalSum + w;
		}
		
		public Tile.biome getRandom()
		{
			int index = rand.nextInt(totalSum);
			int sum = 0;
			int i = 0;
			while(sum < index)
			{
				sum = sum + items.get(i++).relativeProb;
			}
			return items.get(Math.max(0, i-1)).biom;
		}
	}
}
