package com.alesegdia.jsearchgen.map;

import com.alesegdia.jsearchgen.util.Vec2;

public class Door {

	public enum Type {
		HORIZONTAL,
		VERTICAL
	}
	
	static int nextRoomID = 0;
	
	public Door() {
		this.id = nextRoomID++;
		this.localPosition = new Vec2(0,0);
	}
	
	public RoomInstance connectedTo;
	public RoomInstance owner;
	public int roomIndex;				// index in room doors array
	public Vec2 localPosition;	// relative position to room
	int id;						// unique id
	public Type type;
	
	public String toString()
	{
		return localPosition.toString();
	}

	public Vec2 GetGlobalPosition() {
		return this.localPosition.Add(this.owner.globalPosition);
	}
	
}
