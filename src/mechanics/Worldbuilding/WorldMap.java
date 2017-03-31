package mechanics.Worldbuilding;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import mechanics.Worldbuilding.Tile.*;

public class WorldMap 
{	
	public static int WORLD_HEIGHT = 512;
	public static int WORLD_WIDTH = 512;
	Tile[][] world_map = new Tile[(WORLD_HEIGHT+1)][(WORLD_WIDTH+1)];
	private int equator;
	
	public WorldMap()
	{
		determine_equator();
		generate_tileset();
		//SimplexTerrain(2);
		FractalTerrain();
	}
	
	public void determine_equator()
	{
		equator = WORLD_HEIGHT/2;
	}
	
	// FUNCTION: generate_tileset
	// INFO: 	 creates instances of tiles at each coordinate
	public void generate_tileset()
	{
		for(int x = 0; x < WORLD_WIDTH; x++)
		{			
			for(int y = 0; y < WORLD_HEIGHT; y++)
			{
				this.world_map[x][y] = new Tile(x,y,safety_rating.peaceful, terrain.plains);
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
						this.world_map[x][y].setNeighbors(world_map[x][y+1], world_map[y_neighbor_calc(x)][y], world_map[x+1][y], world_map[WORLD_WIDTH][y]);
					}
					else if(y == WORLD_HEIGHT)
					{
						this.world_map[x][y].setNeighbors(world_map[y_neighbor_calc(x)][y+1], world_map[x][y-1], world_map[x+1][y], world_map[WORLD_WIDTH][y]);
					}
					else
					{
						this.world_map[x][y].setNeighbors(world_map[x][y+1], world_map[x][y-1], world_map[x+1][y], world_map[WORLD_WIDTH][y]);
					}
				}
				else if(x == WORLD_WIDTH)
				{
					if(y == 0)
					{
						this.world_map[x][y].setNeighbors(world_map[x][y+1], world_map[y_neighbor_calc(x)][y], world_map[0][y], world_map[x-1][y]);
					}
					else if(y == WORLD_HEIGHT)
					{
						this.world_map[x][y].setNeighbors(world_map[y_neighbor_calc(x)][y], world_map[x][y-1], world_map[0][y], world_map[x-1][y]);
					}
					else
					{
						this.world_map[x][y].setNeighbors(world_map[x][y+1], world_map[x][y-1], world_map[0][y], world_map[x-1][y]);
					}
					
				}
				else
				{
					if(y == 0)
					{
						this.world_map[x][y].setNeighbors(world_map[x][y+1], world_map[y_neighbor_calc(x)][y], world_map[x+1][y], world_map[x-1][y]);
					}
					else if(y == WORLD_HEIGHT)
					{
						this.world_map[x][y].setNeighbors(world_map[y_neighbor_calc(x)][y], world_map[x][y-1], world_map[x+1][y], world_map[x-1][y]);
					}
					else
					{
						this.world_map[x][y].setNeighbors(world_map[x][y+1], world_map[x][y-1], world_map[x+1][y], world_map[x-1][y]);
					}
				}
			}// end y for loop
		}// end x for loop	
	}// end determine_neighbors 
	
	// FUNCTION: y_neigbor_calc
	// INFO:	 jumps exactly halfway around the world
	public int y_neighbor_calc(int x)
	{
		return (x + (WORLD_WIDTH/2)) % WORLD_WIDTH;
	}
	
	// FUNCTION: display_map_privitive
	// INFO:	 prints out a primitive version of the map
	public void display_map_primitive()
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
	
	// FUNCTION: SimplexTerrain
	// INFO:	 ramdomly varies the terrain on the world
	public void SimplexTerrain(int growthFactor)
	{
		SimplexNoise sng = new SimplexNoise();
		float[][] osn = sng.generateOctavedSimplexNoise(WORLD_WIDTH, WORLD_HEIGHT, 16, 0.5f, 0.002f);
		
		for(int x = 0; x < WORLD_WIDTH; x++)
		{	
			for(int y = 0; y < WORLD_HEIGHT; y++)
			{
				this.world_map[x][y].weight = 0;
				for(int growth = 0; growth < growthFactor; growth++)
				{
					this.world_map[x][y].weight += osn[x][y];
				}
			}
		}
	}
	
	// FUNCTION: FractalTerrain
	// INFO:	 ramdomly varies the terrain on the world
	public void FractalTerrain()
	{
		MapGen generation = new MapGen(WORLD_WIDTH, WORLD_HEIGHT, 64);
		for (int y = 0; y < WORLD_HEIGHT; y++) 
		{
			for (int x = 0; x < WORLD_WIDTH; x++) 
			{
				int i = x + y * WORLD_WIDTH;
				world_map[x][y].weight = (float)generation.sample(x,y);
			}
		}
	}
	
}