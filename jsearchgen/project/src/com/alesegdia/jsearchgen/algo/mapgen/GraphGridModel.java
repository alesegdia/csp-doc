package com.alesegdia.jsearchgen.algo.mapgen;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.core.data.Door;
import com.alesegdia.jsearchgen.core.data.DoorPairEntry;
import com.alesegdia.jsearchgen.core.data.RoomInstance;
import com.alesegdia.jsearchgen.core.map.TileMap;
import com.alesegdia.jsearchgen.core.map.TileType;
import com.alesegdia.jsearchgen.core.map.render.TileMapRenderer;
import com.alesegdia.jsearchgen.core.util.RNG;
import com.alesegdia.jsearchgen.core.util.Vec2;

/**
 * Class that will represent a solution as a list of rooms inside,
 * and a TileMap representing
 *
 */
public class GraphGridModel implements IRandomModel, IMapGenModel {

	private static final int SOLUTION_WIDTH = 100;
	private static final int SOLUTION_HEIGHT = 100;
	/**
	 * TileMap representing the entire map as a Matrix2D, with proper rooms placed
	 */
	public TileMap tilemap;
	public List<Door> opened = new LinkedList<Door>();
	public List<Door> closed = new LinkedList<Door>();
	public List<RoomInstance> remaining_rooms = new LinkedList<RoomInstance>();
	public List<RoomInstance> added_rooms = new LinkedList<RoomInstance>();
	public List<DoorPairEntry> added_dpes = new LinkedList<DoorPairEntry>();
	public List<DoorPairEntry> all_feasible_dpes = new LinkedList<DoorPairEntry>();

	public GraphGridModel( int rows, int cols )
	{
		tilemap = new TileMap(rows, cols, TileType.FREE);
	}

	GraphGridModel( GraphGridModel other )
	{
		tilemap = new TileMap(other.tilemap);
	}

	public GraphGridModel(List<RoomInstance> remaining_rooms, int width, int height) {
		this(height, width);
		this.remaining_rooms = remaining_rooms;
		try {
			int room_index = RNG.rng.nextInt(0, remaining_rooms.size()-1);
			RoomInstance selected = remaining_rooms.get(room_index);
			remaining_rooms.remove(selected);
			selected.globalPosition.Set(30, 10);
			AttachRoom(selected, 30, 10);
		} catch(IndexOutOfBoundsException e) {
			System.err.println("remaining_rooms list empty!");
		}
		// TODO Auto-generated constructor stub
	}

	public GraphGridModel(List<RoomInstance> remaining_rooms) {
		this(remaining_rooms, SOLUTION_WIDTH, SOLUTION_HEIGHT);
	}

	
	
	public void AttachRoom(RoomInstance room, int r, int c)
	{
		//System.out.println("Attach at r:" + r + ", c:" + c);
		this.tilemap.Apply(room.prefab.map, r, c);
		room.globalPosition.x = c;
		room.globalPosition.y = r;
		opened.addAll(room.doors);
		added_rooms.add(room);
	}

	public void Render() {
		tilemap.Render();
		System.out.println("Opened doors: " + this.opened);
		System.out.println("Closed doors: " + this.closed);
	}

	@Override
	public boolean InsertRandomFeasibleRoom() {
		// precompute if needed 
		// extract feasible doors for each room
		List<DoorPairEntry> feasible_door_pairs = new LinkedList<DoorPairEntry>();
		for( Iterator<RoomInstance> it = remaining_rooms.iterator(); it.hasNext(); )
		{
			RoomInstance room = it.next();
			List<DoorPairEntry> l = this.GetFeasibleDoorsForRoom(room);
			feasible_door_pairs.addAll(l);
			all_feasible_dpes.addAll(l);
		}

		if( !feasible_door_pairs.isEmpty() )
		{
			int door_index = RNG.rng.nextInt(feasible_door_pairs.size());
			DoorPairEntry fde = feasible_door_pairs.get(door_index);
			Door door = fde.other_door;
			this.AttachRoom(fde.other_door.ri_owner, fde.relativeToSolutionMap.x, fde.relativeToSolutionMap.y);
			Connect(fde.other_door, fde.this_door);
			this.tilemap.Set(door.GetGlobalPosition().y, door.GetGlobalPosition().x, 2);
			this.remaining_rooms.remove(door.ri_owner);
			this.added_dpes.add(fde);
			return true;
		}
		else return false;
	}

	private void Connect(Door other_door, Door this_door) {
		other_door.connected_room 	= this_door.ri_owner;
		this_door.connected_room 	= other_door.ri_owner;
		other_door.connected_door = this_door;
		this_door.connected_door = other_door;
	}

	/**
	 * Check all other room doors against all opened doors in this solution.
	 */
	private List<DoorPairEntry> GetFeasibleDoorsForRoom(RoomInstance room) {
		List<DoorPairEntry> feasible_entries = new LinkedList<DoorPairEntry>();
		for( Iterator<Door> it1 = room.doors.iterator(); it1.hasNext(); )
		{
			Door door_other = it1.next();
			for( Iterator<Door> it2 = this.opened.iterator(); it2.hasNext(); )
			{
				Door door_this = it2.next();
				Vec2 relative_to_this_map = IsPossibleDoorCombination( door_other, door_this );
				if( relative_to_this_map != null )
				{
					DoorPairEntry fde = new DoorPairEntry();
					fde.relativeToSolutionMap = relative_to_this_map;
					fde.other_door = door_other;
					fde.this_door = door_this;
					feasible_entries.add(fde);
				}
			}
		}
		return feasible_entries;
	}

	private Vec2 CheckInsert(Door door_other, Door door_this, int dr, int dc, Door.Type doortype, boolean apply)
	{
		if( door_other.type == door_this.type )
		{
			Vec2 this_global = door_this.GetGlobalPosition();
			if( door_other.type == doortype )
			{
				if( !apply || this.tilemap.Get(this_global.y + dr, this_global.x + dc) == TileType.FREE )
				{
					Vec2 tmp = new Vec2(this_global.y + dr - door_other.localPosition.y, this_global.x + dc - door_other.localPosition.x);
					if( !apply ) return tmp;
					else if( !this.tilemap.CollideWith(door_other.ri_owner.prefab.map, tmp.x, tmp.y) )
					{
						TileMap tm = new TileMap(this.tilemap);
						tm.Apply(door_other.ri_owner.prefab.map, tmp.y, tmp.x);

						return tmp;
					}
				}
			}
		}
		return null;
	}

	public Vec2 IsPossibleDoorCombination(Door door_other, Door door_this, boolean apply) {
		Vec2 pos = null;  pos = CheckInsert(door_other, door_this,   1,   0,   Door.Type.HORIZONTAL,  apply);
		if( pos == null ) pos = CheckInsert(door_other, door_this,  -1,   0,   Door.Type.HORIZONTAL,  apply);
		if( pos == null ) pos = CheckInsert(door_other, door_this,   0,   1,   Door.Type.VERTICAL, 	  apply);
		if( pos == null ) pos = CheckInsert(door_other, door_this,   0,  -1,   Door.Type.VERTICAL,    apply);

		return pos;
	}

	public Vec2 IsPossibleDoorCombination(Door door_other, Door door_this) {
		return IsPossibleDoorCombination(door_other, door_this, true);
	}

	public void RenderCanvas() {
		(new TileMapRenderer(new TileMap(this.CreateTileMapWithDoors()))).Show();
	}

	public TileMap CreateTileMapWithDoors( )
	{
		TileMap tm = new TileMap(this.tilemap);

		for( Door door : this.closed )
		{
			if( door.type == Door.Type.HORIZONTAL) {
				tm.Set(door.GetGlobalPosition().y, door.GetGlobalPosition().x, TileType.DOOR);
				tm.Set(door.connected_door.GetGlobalPosition().y, door.connected_door.GetGlobalPosition().x, TileType.DOOR);
			} else {
				tm.Set(door.GetGlobalPosition().y, door.GetGlobalPosition().x, TileType.DOOR);
				tm.Set(door.connected_door.GetGlobalPosition().y, door.connected_door.GetGlobalPosition().x, TileType.DOOR);
			}
		}
		for( Door door : this.opened )
		{
			if( door.type == Door.Type.HORIZONTAL) {
				tm.Set(door.GetGlobalPosition().y, door.GetGlobalPosition().x, TileType.DOORL);
			} else {
				tm.Set(door.GetGlobalPosition().y, door.GetGlobalPosition().x, TileType.DOORH);
			}
		}

		return tm;
	}

	@Override
	public List<RoomInstance> GetRemainingRooms() {
		return this.remaining_rooms;
	}

	@Override
	public boolean IsComplete() {
		return remaining_rooms.size() == 0;
	}

	@Override
	public List<RoomInstance> GetRooms() {
		return this.added_rooms;
	}


}
