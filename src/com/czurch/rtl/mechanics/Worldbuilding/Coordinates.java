package com.czurch.rtl.mechanics.Worldbuilding;

public class Coordinates {
	int x, y;
	
	public Coordinates(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void set(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
}
