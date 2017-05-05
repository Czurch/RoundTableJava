package block;

import org.newdawn.slick.Image;

public class DirtFloorBlock extends Block{
	public DirtFloorBlock(int id)
	{
		super(id);
	}
	
	public DirtFloorBlock(int id, int x, int y, int rot, int sc, int sp, int dir){
		super(id, x, y, rot, sc, sp, dir);
	}
	
	public void init(Image tileset){
		this.sprite = tileset.getSubImage(0,448,32,32);
				
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
