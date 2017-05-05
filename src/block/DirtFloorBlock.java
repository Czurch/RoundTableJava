package block;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class DirtFloorBlock extends Block{
	public DirtFloorBlock(int id)
	{
		super(id);
	}
	
	public DirtFloorBlock( int x, int y, int rot, int sc, int sp, int dir){
		super(0, x, y, rot, sc, sp, dir);
	}
	
	public void init(){
		Image tileset;
		try {
			tileset = new Image("res/DungeonCrawl_ProjectUtumnoTileset.png");
			this.sprite = tileset.getSubImage(0,448,32,32);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update() {
	}
	
	public boolean mayPass() {
		return true;
	}

	public void steppedOn() {
	}

	public boolean interact() {
		return false;
	}
}
