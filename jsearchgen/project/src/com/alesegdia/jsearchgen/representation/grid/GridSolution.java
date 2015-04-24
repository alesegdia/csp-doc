package com.alesegdia.jsearchgen.representation.grid;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.map.Door;
import com.alesegdia.jsearchgen.map.RoomInstance;
import com.alesegdia.jsearchgen.map.TileMap;
import com.alesegdia.jsearchgen.map.TileType;
import com.alesegdia.jsearchgen.map.canvas.MapRenderer;
import com.alesegdia.jsearchgen.representation.ISolution;
import com.alesegdia.jsearchgen.util.RNG;
import com.alesegdia.jsearchgen.util.Vec2;

/**
 * Class that will represent a solution as a list of rooms inside,
 * and a TileMap representing 
 *
 */
public class GridSolution implements ISolution {

	/**
	 * TileMap representing the entire map as a Matrix2D, with proper rooms placed
	 */
	public TileMap tilemap;
	public List<Door> opened = new LinkedList<Door>();
	public List<Door> closed = new LinkedList<Door>();
	public List<RoomInstance> remaining_rooms = new LinkedList<RoomInstance>();
	public List<RoomInstance> added_rooms = new LinkedList<RoomInstance>();
	
	public GridSolution( int rows, int cols )
	{
		tilemap = new TileMap(rows, cols, TileType.FREE);
	}
	
	GridSolution( GridSolution other )
	{
		tilemap = new TileMap(other.tilemap);
	}

	public void AttachRoom(RoomInstance room, int r, int c)
	{
		System.out.println("Attach at r:" + r + ", c:" + c);
		this.tilemap.Apply(room.prefab.map, r, c);
		room.globalPosition.x = c;
		room.globalPosition.y = r;
//		for(Door door : room.doors)
//		{
//			System.out.println("Door: " + door);
//			System.out.println("Door global pos: " + door.GetGlobalPosition());
//			System.out.println("Room global pos: " + door.ri_owner.globalPosition);
//		}
		opened.addAll(room.doors);
		added_rooms.add(room);
	}

	@Override
	public void Render() {
		tilemap.Render();
		System.out.println("Opened doors: " + this.opened);
		System.out.println("Closed doors: " + this.closed);
	}

	@Override
	public boolean IsComplete() {
		return remaining_rooms.isEmpty();
	}
	
	class FeasibleDoorEntry {
		Door other_door;
		Vec2 relativeToSolutionMap;
		public Door this_door;
	}

	public boolean AttachRandomFeasibleRoom() {
		// extract feasible doors for each room
		List<FeasibleDoorEntry> feasible_doors = new LinkedList<FeasibleDoorEntry>();
		for( Iterator<RoomInstance> it = remaining_rooms.iterator(); it.hasNext(); )
		{
			RoomInstance room = it.next();
			feasible_doors.addAll(this.GetFeasibleDoorsForRoom(room));
		}
		
		if( !feasible_doors.isEmpty() )
		{
			System.out.println("FEASIBLE DOORS: " + feasible_doors);
			int door_index = RNG.rng.nextInt(feasible_doors.size());
			FeasibleDoorEntry fde = feasible_doors.get(door_index);
			Door door = fde.other_door;
			this.AttachRoom(fde.other_door.ri_owner, fde.relativeToSolutionMap.x, fde.relativeToSolutionMap.y);
			System.out.println("Room name: " + door.ri_owner.name);
			System.out.println("Other door: " + fde.other_door);
			System.out.println("This door: " + fde.this_door);
			this.tilemap.Set(door.GetGlobalPosition().y, door.GetGlobalPosition().x, 2);
			this.remaining_rooms.remove(door.ri_owner);
			return true;
		}
		else return false;
	}

	/**
	 * Check all other room doors against all opened doors in this solution.
	 */
	private List<FeasibleDoorEntry> GetFeasibleDoorsForRoom(RoomInstance room) {
		List<FeasibleDoorEntry> feasible_entries = new LinkedList<FeasibleDoorEntry>();
		for( Iterator<Door> it1 = room.doors.iterator(); it1.hasNext(); )
		{
			Door door_other = it1.next();
			for( Iterator<Door> it2 = this.opened.iterator(); it2.hasNext(); )
			{
				Door door_this = it2.next();
				Vec2 relative_to_this_map = IsPossibleDoorCombination( door_other, door_this );
				if( relative_to_this_map != null )
				{
					FeasibleDoorEntry fde = new FeasibleDoorEntry();
					fde.relativeToSolutionMap = relative_to_this_map;
					fde.other_door = door_other;
					fde.this_door = door_this;
					feasible_entries.add(fde);
				}
			}
		}
		return feasible_entries;
	}
	
	static boolean first = false;
	private Vec2 checkThis(Door door_other, Door door_this, int dr, int dc, Door.Type doortype)
	{
		if( door_other.type == door_this.type )
		{
			Vec2 this_global = door_this.GetGlobalPosition();
			if( door_other.type == doortype )
			{
				if( this.tilemap.Get(this_global.y + dr, this_global.x + dc) == TileType.FREE )
				{
					Vec2 tmp = new Vec2(this_global.y + dr - door_other.localPosition.y, this_global.x + dc - door_other.localPosition.x);
					if( !this.tilemap.CollideWith(door_other.ri_owner.prefab.map, tmp.x, tmp.y) )
					{
							TileMap tm = new TileMap(this.tilemap);
						tm.Apply(door_other.ri_owner.prefab.map, tmp.y, tmp.x);
						//(new MapRenderer(tm)).Show();

						return tmp;
					}
				}
			}
		}
		return null;
	}
	
	private Vec2 IsPossibleDoorCombination(Door door_other, Door door_this) {
		Vec2 pos = null;
		
						  pos = checkThis(door_other, door_this,   1,   0,   Door.Type.HORIZONTAL);
		if( pos == null ) pos = checkThis(door_other, door_this,  -1,   0,   Door.Type.HORIZONTAL);
		if( pos == null ) pos = checkThis(door_other, door_this,   0,   1,   Door.Type.VERTICAL);
		if( pos == null ) pos = checkThis(door_other, door_this,   0,  -1,   Door.Type.VERTICAL);

		return pos;
	}
	
	/*
	private Vec2 IsPossibleDoorCombination(Door door_other, Door door_this) {
		Vec2 pos = null;
		if( door_other.type == door_this.type )
		{
			// we can move door_other but not door_this
			Vec2 this_global = door_this.GetGlobalPosition();
			System.out.println("IsPosDorCom: " + this_global);
			if( door_other.type == Door.Type.VERTICAL )
			{
				System.out.println("ver: " + door_other + ", " + door_this);
				// desplazar other izq. o der.
				if( this.tilemap.Get(this_global.x, this_global.y+1) == TileType.FREE )
				{
					// desplazar a la derecha door_other y comprobar mapa
					Vec2 tmp = new Vec2(this_global.x, this_global.y+1);
					if( !this.tilemap.CollideWith(door_other.ri_owner.prefab.map, tmp.x, tmp.y) )
					{
						pos = tmp;
					}
				}
				else if( this.tilemap.Get(this_global.x, this_global.y-1) == TileType.FREE )
				{
					// desplazar a la izquierda door_other y comprobar mapa
					Vec2 tmp = new Vec2(this_global.x, this_global.y-1);
					if( !this.tilemap.CollideWith(door_other.ri_owner.prefab.map, tmp.x, tmp.y) )
					{
						pos = tmp;
					}
				}
			}
			else if( door_other.type == Door.Type.HORIZONTAL )
			{
				// desplazar other arr. o aba.
				if( this.tilemap.Get(this_global.x+1, this_global.y) == TileType.FREE )
				{
					// desplazar abajo door_other y comprobar mapa
					Vec2 tmp = new Vec2(this_global.x+1,this_global.y);
					if( !this.tilemap.CollideWith(door_other.ri_owner.prefab.map, tmp.x, tmp.y) )
					{
						pos = tmp;
					}
				}
				else if( this.tilemap.Get(this_global.x-1, this_global.y) == TileType.FREE )
				{
					// desplazar arriba door_other y comprobar mapa
					Vec2 tmp = new Vec2(this_global.x-1, this_global.y);
					if( !this.tilemap.CollideWith(door_other.ri_owner.prefab.map, tmp.x, tmp.y) )
					{
						pos = tmp;
					}
				}
			}
		}
		return pos;
	}*/

	@Override
	public void RenderCanvas() {
		(new MapRenderer(new TileMap(this.CreateTileMapWithDoors()))).Show();
	}

	public TileMap CreateTileMapWithDoors( )
	{
		TileMap tm = new TileMap(this.tilemap);

		for( Door door : this.closed )
		{
			if( door.type == Door.Type.HORIZONTAL) {
				tm.Set(door.GetGlobalPosition().y, door.GetGlobalPosition().x, TileType.DOOR);
			} else {
				tm.Set(door.GetGlobalPosition().y, door.GetGlobalPosition().x, TileType.DOOR);
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

		//for( PotentialDoorEntry pde : this.prefab.potentialDoors )
		return tm;
	}


}
