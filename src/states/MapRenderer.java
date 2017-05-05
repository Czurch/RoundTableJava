package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;

import block.Block;
import block.DirtFloorBlock;

public class MapRenderer implements Runnable {
    private GameContainer container;
    private SpriteSheet sheet;
    Block groundTile;
    int SIZE;
    int TILE_SIZE;
    
    public MapRenderer(GameContainer container, int SIZE, int TILE_SIZE) {
    	this.SIZE 		= SIZE;
    	this.TILE_SIZE 	= TILE_SIZE;
        this.container 	= container;
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
                	groundTile = new DirtFloorBlock(i*TILE_SIZE, j*TILE_SIZE, 0, 1, 0, 0);
            }
        }
        if (embedded)
            sheet.endUse();
    }
} 