package com.alesegdia.jsearchgen.util;

public class Vec2 {

	public Vec2(int x, int y) {
		Set(x,y);
	}

	public void Set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int x;
	public int y;
	
	public String toString()
	{
		return "(" + this.x + "," + this.y + ")";
	}
	
}
