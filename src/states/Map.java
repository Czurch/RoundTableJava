package states;

import main.Game;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.state.*;
import org.newdawn.slick.util.BufferedImageUtil;

import Level.Level;
import block.Block;

import java.awt.image.*;

import mechanics.Player;
import mechanics.Worldbuilding.Cave;
import mechanics.Worldbuilding.Cave.Coord;

public class Map extends BasicGameState {

	int SIZE 		= 	100;
	int TILE_SIZE 	= 	32;
	int map_width 	= 	100;
	int map_height 	= 	100;
	Level level;
	SpriteSheet sheet;
	Player p;
	
	
	Image tileset 		= null;
	Image mapImage 		= null;
	Image noTile 		= null;
	Image groundTile 	= null;
	Image wallTile 		= null;
	Image playerSprite 	= null;
	Block currentBlock 	= null;
	Player player		= null;
	int p_pos_X, p_pos_Y;
	
	MapRenderer renderer;
    
	public Map(int state){}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		renderer = new MapRenderer(gc, SIZE, TILE_SIZE);
		
		
		tileset 		= new Image("res/DungeonCrawl_ProjectUtumnoTileset.png");
		noTile 			= tileset.getSubImage(0,0,32,32);
		groundTile 		=  tileset.getSubImage(0,448,32,32);
		wallTile 		= tileset.getSubImage(704, 416, 32, 32);
		playerSprite 	= tileset.getSubImage(32, 992, 32, 32);
		Cave splunk 	= new Cave(map_width, map_height, 45);
		
		level 			= 	new Level(map_width, map_height);
		level.getObstacleMap(splunk.map);
		currentBlock = level.tiles[p_pos_X][p_pos_Y];
		System.out.println("Obstacle map initialized");
		
		p_pos_X 		= 14;
		p_pos_Y 		= 3;
		player 			= new Player(p_pos_X*TILE_SIZE, p_pos_Y*TILE_SIZE, 0, 1, 0, 0);
		
        mapImage = new Image(SIZE * TILE_SIZE, SIZE* TILE_SIZE);
        Graphics g = mapImage.getGraphics();
        
        //draw the map onto our new image using the image graphics
        renderer.run(g, false);
        
        //flush the graphics!
        g.flush();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//mapImage.draw(p_pos_X, p_pos_Y);
		level.render(g, -(p_pos_X * TILE_SIZE) + (Game.SCREEN_WIDTH/2) , -(p_pos_Y * TILE_SIZE) + (Game.SCREEN_HEIGHT/2));
		player.renderAt(g, currentBlock.getX(), currentBlock.getY());
		g.drawString("Player X: " + p_pos_X + "  Y: "+ p_pos_Y, 0, 32);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_W))
		{
			if(p_pos_Y != 0)
			{
				p_pos_Y -= 1;
				if(level.tiles[p_pos_X][p_pos_Y].canPass(player))
					currentBlock = level.tiles[p_pos_X][p_pos_Y];
				else
					p_pos_Y += 1;
			}
			
		}
		if(input.isKeyDown(Input.KEY_S))
		{
			if(p_pos_Y < SIZE)
			{
				p_pos_Y += 1;
				if(level.tiles[p_pos_X][p_pos_Y].canPass(player))
					currentBlock = level.tiles[p_pos_X][p_pos_Y];
				else
					p_pos_Y -= 1;
			}
		}
		if(input.isKeyDown(Input.KEY_A))
		{
			if(p_pos_X != 0)
			{
				p_pos_X -= 1;
				if(level.tiles[p_pos_X][p_pos_Y].canPass(player))
					currentBlock = level.tiles[p_pos_X][p_pos_Y];
				else
					p_pos_X += 1;
			}
		}
		if(input.isKeyDown(Input.KEY_D))
		{
			if(p_pos_X < SIZE)
			{
				p_pos_X += 1;
				if(level.tiles[p_pos_X][p_pos_Y].canPass(player))
					currentBlock = level.tiles[p_pos_X][p_pos_Y];
				else
					p_pos_X -= 1;
			}
		}
	}

	@Override
	public int getID() {
		return 1;
	}

	
}
