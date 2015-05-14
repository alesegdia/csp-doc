package com.alesegdia.jsearchgen.core.room;

import com.alesegdia.jsearchgen.core.util.Vec2;

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

	public RoomInstance connected_room;
	public RoomInstance ri_owner;
	public Door connected_door;
	public int roomIndex;				// index in room doors array
	public Vec2 localPosition;	// relative position to room
	int id;						// unique id
	public Type type;
	
	public String toString()
	{
		//return "[ local: " + localPosition.toString() + ", global: " + this.GetGlobalPosition().toString() + " ]";
		//return "<room " + ri_owner.id + " pos " + GetGlobalPosition().toString() + ">";
		return "<" + ri_owner.id + connected_door.ri_owner.id + ">";
	}

	public Vec2 GetGlobalPosition() {
		return this.localPosition.Add(this.ri_owner.globalPosition);
	}
	
}
