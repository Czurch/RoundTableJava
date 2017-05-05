package Level;

import java.util.ArrayList;
import java.util.List;

import block.Block;
import mechanics.GameObject;


public class Level 
{
	int width, height;
	Block tiles[][];
	
	public List<GameObject> objectsInLevel;
	
	public Level(int w, int h)
	{
		tiles = new Block[width][height];
		objectsInLevel = new ArrayList<GameObject>();
	}
	
	public void getObstacleMap(int map[][])
	{
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				tiles[x][y] = new Block(map[x][y]);
				
			}
		}
	}
	
	public void render()
	{
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				tiles[x][y].render();
			}
		}
	}
	
}
