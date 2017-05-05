package mechanics;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class GameObject {

	public int x_pos;
	public int y_pos;
	protected int width, height;
	protected int rotation;
	protected int scale;
	protected double speed;
	protected double direction;
	protected Rectangle hit_box;
	protected Image sprite;
	
	
	public GameObject(){
	}
	
	public GameObject(int x, int y, int rot, int sc, int sp, int dir){
		x_pos 		=	x;
		y_pos 		=	y;
		rotation 	=	rot;
		scale 		= 	sc;
		speed 		= 	sp;
		direction 	= 	dir;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(sprite, x_pos, y_pos);
	}
	public void renderAt(Graphics g, int x, int y)
	{
		x_pos = x;
		y_pos = y;
		g.drawImage(sprite, x_pos, y_pos);
	}
	
	public void destroy(){
	}
	
	public void collision(GameObject go){
	}
	
	public void update(){
	}
}
