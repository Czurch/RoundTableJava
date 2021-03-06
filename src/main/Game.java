package main;

import states.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{

	static int FRAME_RATE = 60;
	public static final int SCREEN_WIDTH = 1920;
	public static final int SCREEN_HEIGHT = 1080;
	public static final String gamename = "RoundTable Lite";
	public static final int menu = 0;
	public static final int map = 1;
	public static final int dungeon = 2;
	public static final int inventory = 3;
	
	public Game(String gamename)
	{
		super(gamename);
		this.addState(new Menu(menu));
		this.addState(new Map(map));
		//this.addState(new Dungeon(dungeon));
		//this.addState(new Inventory(inventory));
		
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc, this);
		this.getState(map).init(gc, this);
		//this.getState(dungeon).init(gc, this);
		//this.getState(inventory).init(gc, this);
		this.enterState(map);
	}
	
	public static void main(String[] args)
	{
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
			appgc.setTargetFrameRate(FRAME_RATE);
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
		
	}

}
