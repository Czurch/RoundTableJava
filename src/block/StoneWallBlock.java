package block;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class StoneWallBlock extends Block{
	public StoneWallBlock(int id)
	{
		super(id);
	}
	
	public StoneWallBlock(int x, int y, int rot, int sc, int sp, int dir){
		super(1, x, y, rot, sc, sp, dir);
	}
	
	public void init(){
		Image tileset;
		try {
			tileset = new Image("res/DungeonCrawl_ProjectUtumnoTileset.png");
			this.sprite = tileset.getSubImage(704, 416, 32, 32);
		} catch (SlickException e) {
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
