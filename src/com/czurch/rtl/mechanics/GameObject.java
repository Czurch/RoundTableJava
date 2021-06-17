package com.czurch.rtl.mechanics;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import com.czurch.rtl.Level.Level;
import com.czurch.rtl.mechanics.Worldbuilding.*;

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
	
	public void init(){
	}
	public void render(Graphics g)
	{
		g.drawImage(sprite, x_pos, y_pos);
	}
	public void renderAt(Graphics g, int x, int y)
	{
		x_pos = x;
		y_pos = y;
		//sprite.draw(x_pos, y_pos);
		g.drawImage(sprite, x_pos, y_pos);
	}
	
	public void destroy(){
	}
	
	// FUNCTION: move (Level level, int direction)		directions:			8	1	2
	// returns: 1 if successful 											  \	| /
	//			0 if fail									  				7 - P - 3
												//							  / | \
												//							6	5	4
	
	public int move(int x, int y, Level level, int direction)
	{
		switch(direction)
		{
		
		case 1:											// 1
			y -= 1;
			if(level.tiles[x][y].canPass(this))
			{
				return 1;
			}
			else
				return 0;
		case 2:											// 2
			x += 1;
			y -= 1;
			if(level.tiles[x][y].canPass(this))
			{
				return 1;
			}
			else
				return 0;
		case 3:											// 3
			x += 1;
			
			if(level.tiles[x][y].canPass(this))
			{
				return 1;
			}
			else
				return 0;
		case 4:											// 4
			x += 1;
			y += 1;
			if(level.tiles[x][y].canPass(this))
			{
				return 1;
			}
			else
				return 0;
		case 5:											// 5
			y += 1;
			
			if(level.tiles[x][y].canPass(this))
			{
				return 1;
			}
			else
				return 0;	
		case 6:											// 6
			x -= 1;
			y += 1;
			if(level.tiles[x][y].canPass(this))
			{
				return 1;
			}
			else
				return 0;
		case 7:											// 7
			x -= 1;

			if(level.tiles[x][y].canPass(this))
			{
				return 1;
			}
			else
				return 0;
		case 8:											// 8
			x -= 1;
			y -= 1;
			if(level.tiles[x][y].canPass(this))
			{
				return 1;
			}
			else
				return 0;
		default:
			System.out.println("No Direction Returned in GameObject move method!");
			return 0;
		}
		
	}
	
	public void collision(GameObject go){
	}
	
	public void update(){
	}
	
	public int getX()
	{
		return this.x_pos;
	}
	
	public int getY()
	{
		return this.y_pos;
	}
	
	public void setXY(int x, int y)
	{
		this.x_pos = x;
		this.y_pos = y;
	}
}
