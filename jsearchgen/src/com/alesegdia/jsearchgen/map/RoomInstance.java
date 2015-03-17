package com.alesegdia.jsearchgen.map;

import java.util.LinkedList;
import java.util.List;

/**
 * Glue between the physical map representation of a Room and its Door(s).
 */
public class RoomInstance {

	List<Door> doors = new LinkedList<Door>();
	TileMap map;
	
	/** Holds the map. Doesn't need to copy since this map won't change,
	 * we will place doors by adding them properly to the list
	 * @param map physical map that this doors identifies with
	 */
	public RoomInstance(RoomPrefab prefab)
	{
		this.map = prefab.map;
	}
	
	/** Adds a door to this room
	 * @param x relative to room x coordinate
	 * @param y relative to room y coordinate 
	 */
	public void AddDoor( int x, int y )
	{
		Door door = new Door();
		door.owner = this;
		door.roomIndex = doors.size();
		door.localPosition.Set(x,y);
	}
	
	public void GenerateRandomDoors(int num_doors)
	{
		while(num_doors > 0)
		{
			// cache wall tiles at TileMap?
			for( int row = 0; row < this.map.rows; row++ )
			{
				for( int col = 0; col < this.map.cols; col++ )
				{
					
				}
			}
		}
	}
	
	/** Sets a door link to another room, selecting door by index at room door list
	 * @param door_index door index
	 * @param connected_to room connected to this door
	 */
	public void ConnectDoorByIndex( int door_index, RoomInstance connected_to )
	{
		doors.get(door_index).connectedTo = connected_to;
	}
	
}
