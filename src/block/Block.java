package block;

import java.util.List;

import mechanics.GameObject;

public class Block extends GameObject{
	int x, y;
	boolean isBlocking = false;

	public List<GameObject> objectsOnBlock;
	
	public static Block[] tiles = new Block[256];
	public static Block dirtFloor = new DirtFloorBlock(0);
	public static Block stoneWall = new StoneWallBlock(1);
	
	public final byte id;

	public Block(int id) {
		super();
		this.id = (byte) id;
		if (tiles[id] != null) throw new RuntimeException("Duplicate tile ids!");
		tiles[id] = this;
	}
	
	public Block(int id, int x, int y, int rot, int sc, int sp, int dir){
		super(x, y, rot, sc, sp, dir);
		this.id = (byte) id;
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
