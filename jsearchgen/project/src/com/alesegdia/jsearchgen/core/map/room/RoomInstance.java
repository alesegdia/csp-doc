package com.alesegdia.jsearchgen.core.map.room;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.core.map.MapRenderer;
import com.alesegdia.jsearchgen.core.map.TileMap;
import com.alesegdia.jsearchgen.core.map.TileType;
import com.alesegdia.jsearchgen.core.map.room.RoomPrefab.PotentialDoorEntry;
import com.alesegdia.jsearchgen.core.util.RNG;
import com.alesegdia.jsearchgen.core.util.Vec2;

/**
 * Glue between the physical map representation of a Room, Door(s) and the position it has.
 */
public class RoomInstance {

	public List<Door> doors = new LinkedList<Door>();
	public RoomPrefab prefab;
	public Vec2 globalPosition = new Vec2(0,0);
	public String name;
	
	/** Holds the map. Doesn't need to copy since this map won't change,
	 * we will place doors by adding them properly to the list
	 * @param map physical map that this doors identifies with
	 */
	public RoomInstance(RoomPrefab prefab)
	{
		this.prefab = prefab;
	}
	
	/** Adds a door to this room. Just for internal purposes.
	 * @param x relative to room x coordinate
	 * @param y relative to room y coordinate 
	 */
	public void AddDoor( int x, int y, Door.Type type )
	{
		Door door = new Door();
		door.ri_owner = this;
		door.roomIndex = doors.size();
		door.localPosition.Set(x,y);
		door.type = type;
		this.doors.add(door);
	}

	/** Note that this may be a heavy function for CPU performance
	 * 	because of list handling.
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
		doors.get(door_index).connected_room = connected_to;
	}
	
	public TileMap CreateTileMapWithDoors( )
	{
		TileMap tm = new TileMap(this.prefab.map);

		for( Door door : doors )
		{
			if( door.type == Door.Type.HORIZONTAL) {
				tm.Set(door.localPosition.y, door.localPosition.x, TileType.DOORL);
			} else {
				tm.Set(door.localPosition.y, door.localPosition.x, TileType.DOORH);
			}
		}
		
		return tm;
	}

	public void RenderCanvas() {
		(new MapRenderer(this.CreateTileMapWithDoors())).Show();
	}
}
