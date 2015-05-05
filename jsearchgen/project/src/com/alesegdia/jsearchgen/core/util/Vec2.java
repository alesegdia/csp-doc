package com.alesegdia.jsearchgen.core.util;

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

	public Vec2 Add(Vec2 globalPosition) {
		return new Vec2( this.x + globalPosition.x, this.y + globalPosition.y );
	}
	
}
