package com.alesegdia.jsearchgen.map;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.util.RNG;

/**
 * Glue between the physical map representation of a Room and its Door(s).
 */
public class RoomInstance {

	List<Door> doors = new LinkedList<Door>();
	RoomPrefab prefab;
	
	/** Holds the map. Doesn't need to copy since this map won't change,
	 * we will place doors by adding them properly to the list
	 * @param map physical map that this doors identifies with
	 */
	public RoomInstance(RoomPrefab prefab)
	{
		this.prefab = prefab;
	}
	
	/** Adds a door to this room. Not accessible from the outside.
	 * @param x relative to room x coordinate
	 * @param y relative to room y coordinate 
	 */
	private void AddDoor( int x, int y, Door.Type type )
	{
		Door door = new Door();
		door.owner = this;
		door.roomIndex = doors.size();
		door.localPosition.Set(x,y);
		door.type = type;
	}
	
	/** Note that this may be a heavy function for CPU performance
	 * @param rng RNG class object used for random generation
	 * @param num_doors number of doors to be generated
	 */
	public void GenerateRandomDoors(RNG rng, int num_doors)
	{
		List<RoomPrefab.PotentialDoorEntry> possible_doors = new ArrayList<RoomPrefab.PotentialDoorEntry>(this.prefab.potentialDoors);
		while(num_doors > 0 && possible_doors.size() > 0)
		{
			int selected = rng.nextInt(possible_doors.size());
			RoomPrefab.PotentialDoorEntry pde = possible_doors.get(selected);
			this.AddDoor(pde.localPosition.x, pde.localPosition.y, pde.type);
			possible_doors.remove(selected);
			num_doors--;
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
