package states;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.state.*;
import org.newdawn.slick.util.BufferedImageUtil;

import Level.Level;

import java.awt.image.*;

import mechanics.Player;
import mechanics.Worldbuilding.Cave;
import mechanics.Worldbuilding.Cave.Coord;

public class Map extends BasicGameState {

	int SIZE = 100;
	int TILE_SIZE = 32;
	int map_width = 100;
	int map_height = 100;
	Level level = new Level(map_width, map_height);
	SpriteSheet sheet;
	Player p;
	
	
	Image tileset = null;
	Image mapImage = null;
	Image noTile = null;
	Image groundTile = null;
	Image wallTile = null;
	Image playerSprite = null;
	int p_pos_X, p_pos_Y;
	
	MapRenderer renderer;
	
    public class MapRenderer implements Runnable {
        private GameContainer container;
        
        public MapRenderer(GameContainer container) {
            this.container = container;
        }
        
        public void run() {
            run(container.getGraphics(), false);
        }
        
        public void run(Graphics g, boolean embedded) {
            //if we are using the 'embedded' mode, start/end use
            if (embedded)
                sheet.startUse();
            
            for (int i=0; i<SIZE; i++) {
                for (int j=0; j<SIZE; j++) {
                    //Here you would usually figure out which tile to render,
                    //i.e. grass, water, bridge, etc.
                    
                    //if we are using 'embedded' mode, render from the sheet
                    if (embedded)
                        sheet.renderInUse(i*TILE_SIZE, j*TILE_SIZE, 0, 0);
                    
                    //otherwise just render the image to the graphics context
                    else
                    	g.drawImage(groundTile, i*TILE_SIZE, j*TILE_SIZE);
                }
            }
            if (embedded)
                sheet.endUse();
        }
    } 
    
	public Map(int state){}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		renderer = new MapRenderer(gc);
		
		tileset = new Image("res/DungeonCrawl_ProjectUtumnoTileset.png");
		noTile = tileset.getSubImage(0,0,32,32);
		groundTile =  tileset.getSubImage(0,448,32,32);
		wallTile = tileset.getSubImage(704, 416, 32, 32);
		playerSprite = tileset.getSubImage(32, 992, 32, 32);
		Player p = new Player();
		Cave splunk = new Cave(map_width, map_height, 45);
		level.getObstacleMap(splunk);
		
		p_pos_X = 32;
		p_pos_Y = 32;
		
		
        mapImage = new Image(SIZE * TILE_SIZE, SIZE* TILE_SIZE);
        Graphics g = mapImage.getGraphics();
        
        //draw the map onto our new image using the image graphics
        renderer.run(g, false);
        
        //flush the graphics!
        g.flush();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		mapImage.draw(p_pos_X, p_pos_Y);
		playerSprite.draw(320,110);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_W))
		{
			p_pos_Y += 1;
		}
		if(input.isKeyDown(Input.KEY_S))
		{
			p_pos_Y -= 1;
		}
		if(input.isKeyDown(Input.KEY_A))
		{
			p_pos_X += 1;
		}
		if(input.isKeyDown(Input.KEY_D))
		{
			p_pos_X -= 1;
		}
	}

	@Override
	public int getID() {
		return 1;
	}

	
}
