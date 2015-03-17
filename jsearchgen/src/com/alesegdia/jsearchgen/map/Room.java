package com.alesegdia.jsearchgen.map;

import java.util.LinkedList;
import java.util.List;

public class Room {

	List<Door> doors = new LinkedList<Door>();
	TileMap map;
	
	public Room(TileMap map)
	{
		this.map = new TileMap(map);
	}
	
	public void AddDoor( int x, int y )
	{
		Door door = new Door();
		door.owner = this;
		door.roomIndex = doors.size();
		door.localPosition.Set(x,y);
	}
	
	public void ConnectDoorByIndex( int door_index, Room connected_to )
	{
		doors.get(door_index).connectedTo = connected_to;
	}
	
}
