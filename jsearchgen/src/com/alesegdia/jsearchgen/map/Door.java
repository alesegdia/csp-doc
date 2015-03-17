package com.alesegdia.jsearchgen.map;

import com.alesegdia.jsearchgen.util.Vec2;

public class Door {

	static int nextRoomID = 0;
	
	public Door() {
		this.id = nextRoomID++;
		this.localPosition = new Vec2(0,0);
	}
	
	public Room connectedTo;
	public Room owner;
	public int roomIndex;				// index in room doors array
	public Vec2 localPosition;	// relative position to room
	int id;						// unique id
	
}
