import GameModes.DungeonCrawler;
import mechanics.*;
import mechanics.Worldbuilding.WorldMap;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WorldMap terra = new WorldMap();
		terra.display_map_primitive();
		DungeonCrawler game = new DungeonCrawler();
		System.out.println("End");
	}

}
