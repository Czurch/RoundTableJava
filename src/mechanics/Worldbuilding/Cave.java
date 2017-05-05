package mechanics.Worldbuilding;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Cave {
	public int width;
	public int height;
	
	public String seed;
	public boolean useRandomSeed;
	
	public int randomFillPercent;
	
	public int[][] map;
	
	public Cave(int w, int h, int p)
	{
		width = w;
		height = h;
		randomFillPercent = p;
		useRandomSeed = true;
		GenerateMap();
	}
	
	public Cave(int w, int h, int p, String s)
	{
		width = w;
		height = h;
		randomFillPercent = p;
		seed = s;
		useRandomSeed = false;
		GenerateMap();
	}
	
	private void GenerateMap()
	{
		map = new int[width][height];
		RandomFillMap();
		
		for(int i = 0; i < 5; i++)
		{
			SmoothMap();
		}
		
		ProcessMap();
	}
	
	private void RandomFillMap()
	{
		if(useRandomSeed)
		{
			Random r = new Random(System.currentTimeMillis());
			r.nextLong();
			seed = r.toString();
		}
		
		Random psuedoRandom = new Random(seed.hashCode());
		
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				if(x == 0 || x == width-1 || y == 0 || y == height-1)
				{
					map[x][y] = 1;
				}else{
					if(psuedoRandom.nextInt(100) < randomFillPercent){
						map[x][y] = 1;
					}else{
						map[x][y] = 0;
					}
				}
			}
		}
	}
	
	private void SmoothMap()
	{
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				int neighbourWallTiles = GetSurroundingWallCount(x,y);
				
				if(neighbourWallTiles > 4)
					map[x][y] = 1;
				else if (neighbourWallTiles < 4)
					map[x][y] = 0;
			}
		}
	}
	
	private int GetSurroundingWallCount(int gx, int gy)
	{
		int wallCount = 0;
		for(int nx = gx - 1; nx <= gx + 1; nx++){
			for(int ny = gy - 1; ny <= gy + 1; ny++){
				if(IsinMapRange(nx, ny))
				{
					if(nx != gx || ny != gy)
					{
						wallCount += map[nx][ny];
					}
				}else{
					wallCount++;
				}
			}
		}
		return wallCount;
	}
	
	private void ProcessMap()
	{
		//will remove sections of wall smaller than the threshold
		List<List<Coord>> wallRegions = getRegions(1);
		int wallThresholdSize = 5;
		
		for (List<Coord> wallRegion : wallRegions)
		{
			if(wallRegion.size() < wallThresholdSize)
			{
				for(Coord tile : wallRegion)
				{
					map[tile.tileX][tile.tileY] = 0;	//turn those sections into empty space
				}
			}
		}
		
		//will remove rooms smaller than the threshold
		List<List<Coord>> roomRegions = getRegions(0);
		int roomThresholdSize = 5;
		
		for (List<Coord> roomRegion : roomRegions)
		{
			if(roomRegion.size() < roomThresholdSize)
			{
				for(Coord tile : roomRegion)
				{
					map[tile.tileX][tile.tileY] = 1;	//turn those sections into walls
				}
			}
		}
	}
	
	List<List<Coord>> getRegions(int tileType)
	{
		List<List<Coord>> regions = new ArrayList<List<Coord>>();
		int mapFlags[][] = new int[width][height];
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				if(mapFlags[x][y] == 0 && map[x][y] == tileType){
					List<Coord> newRegion = getRegionTiles(x,y);
					regions.add(newRegion);
					
					for (Coord tile : newRegion) {
					    mapFlags[tile.tileX][tile.tileY] = 1;
					}
				}
			}
		}
		
		return regions;
	}
	
	List<Coord> getRegionTiles(int startX, int startY)
	{
		List<Coord> tiles = new ArrayList<Coord>();
		int [][] mapFlags = new int[width][height];
		int tileType = map[startX][startY];
		
		Queue<Coord> queue = new LinkedList<Coord>();
		queue.add(new Coord(startX, startY));
		mapFlags[startX][startY] = 1;
		
		while(!queue.isEmpty())
		{
			Coord tile = queue.remove();
			tiles.add(tile);
			for(int x = 0; x < width; x++){
				for(int y = 0; y < height; y++){
					if(IsinMapRange(x,y) && (x == tile.tileX || y == tile.tileY)){
						if(mapFlags[x][y] == 0 && map[x][y] == tileType){
							mapFlags[x][y] = 1;
							queue.add(new Coord(x,y));
						}
					}
				}
			}
		}
		
		return tiles;
	}
	
	private boolean IsinMapRange(int x, int y)
	{
		return x >= 0 && x < width && y >= 0 && y < height;
	}
	
	public class Coord
	{
		int tileX;
		int tileY;
		public Coord(int x,int y)
		{
			tileX = x;
			tileY = y;
		}
	}
	
	public BufferedImage getCaveImage()
	{
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int i = x + y * width;
				if (map[x][y] == 0)
				{
					pixels[i] = 0xFFFFFF;
				}else{
					pixels[i] = 0x000000;
				}
			}
		}
		img.setRGB(0, 0, width, height, pixels, 0, width);
		return img;
	}
}
