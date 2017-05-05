package Level;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;

import block.*;
import mechanics.GameObject;


public class Level 
{
	int SIZE = 100;
	int TILE_SIZE = 32;
	int width, height;
	Block tiles[][];
	
	public List<GameObject> objectsInLevel;
	
	public Level(int w, int h)
	{
		this.width = w;
		this.height = h;
		tiles = new Block[width][height];
		objectsInLevel = new ArrayList<GameObject>();
	}
	
	public void getObstacleMap(int map[][])
	{
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				if(map[x][y] == 0)
				{
					tiles[x][y] = new DirtFloorBlock(x*TILE_SIZE, y*TILE_SIZE, 0, 1, 0, 0);
				}
				else if(map[x][y] == 1)
				{
					tiles[x][y] = new StoneWallBlock(x*TILE_SIZE, y*TILE_SIZE, 0, 1, 0, 0);
				}
			}
		}
	}
	
	public void render(Graphics g, int dx, int dy)
	{
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				tiles[x][y].renderAt(g,x*TILE_SIZE + dx,  y*TILE_SIZE + dy);
			}
		}
	}
	
}
