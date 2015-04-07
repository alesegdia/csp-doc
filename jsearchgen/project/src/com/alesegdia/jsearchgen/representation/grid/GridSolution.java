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

	
	public void AttachRoom(RoomInstance room, int x, int y)
	{
		this.tilemap.Apply(room.prefab.map, y, x);
		room.globalPosition.x = x;
		room.globalPosition.y = y;
		for(Door door : room.doors)
		{
			System.out.println(door);
			System.out.println(door.ri_owner.globalPosition);
		}
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
		Door door;
		Vec2 relativeToSolutionMap;
	}

	public boolean AttachRandomFeasibleRoom() {
		// extract feasible doors for each room
		List<Door> feasible_doors = new LinkedList<Door>();
		for( Iterator<RoomInstance> it = remaining_rooms.iterator(); it.hasNext(); )
		{
			RoomInstance room = it.next();
			feasible_doors.addAll(this.GetFeasibleDoorsForRoom(room));
		}
		
		if( !feasible_doors.isEmpty() )
		{
			System.out.println("FEASIBLE DOORS: " + feasible_doors);
			int door_index = RNG.rng.nextInt(feasible_doors.size());
			Door door = feasible_doors.get(door_index);
			this.AttachRoom(door.ri_owner, door.GetGlobalPosition().x, door.GetGlobalPosition().y);
			System.out.println("Room name: " + door.ri_owner.name);
			System.out.println("Attached door: " + door);
			System.out.println("To door: " + door);
			this.remaining_rooms.remove(door.ri_owner);
			return true;
		}
		else return false;
	}

	/**
	 * Check all other room doors against all opened doors in this solution.
	 */
	private List<Door> GetFeasibleDoorsForRoom(RoomInstance room) {
		List<Door> feasible_entries = new LinkedList<Door>();
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
					fde.door = door_other;
					feasible_entries.add(fde.door);
				}
			}
		}
		return feasible_entries;
	}
	
	private Vec2 IsPossibleDoorCombination(Door door_other, Door door_this) {
		Vec2 pos = null;
		if( door_other.type == door_this.type )
		{
			// we can move door_other but not door_this
			Vec2 this_global = door_this.GetGlobalPosition();
			if( door_other.type == Door.Type.HORIZONTAL )
			{
				// desplazar other izq. o der.
				if( this.tilemap.Get(this_global.y, this_global.x+1) == TileType.FREE )
				{
					// desplazar a la derecha door_other y comprobar mapa
					Vec2 tmp = new Vec2(this_global.y+1, this_global.x);
					if( this.tilemap.CollideWith(door_other.ri_owner.prefab.map, tmp.x, tmp.y) )
					{
						pos = tmp;
					}
				}
				else if( this.tilemap.Get(this_global.y, this_global.x-1) == TileType.FREE )
				{
					// desplazar a la izquierda door_other y comprobar mapa
					Vec2 tmp = new Vec2(this_global.x-1, this_global.y);
					if( this.tilemap.CollideWith(door_other.ri_owner.prefab.map, tmp.y, tmp.x) )
					{
						pos = tmp;
					}
				}
			}
			else if( door_other.type == Door.Type.VERTICAL )
			{
				// desplazar other arr. o aba.
				if( this.tilemap.Get(this_global.y+1, this_global.x) == TileType.FREE )
				{
					// desplazar abajo door_other y comprobar mapa
					Vec2 tmp = new Vec2(this_global.x,this_global.y+1);
					if( this.tilemap.CollideWith(door_other.ri_owner.prefab.map, tmp.y, tmp.x) )
					{
						pos = tmp;
					}
				}
				else if( this.tilemap.Get(this_global.y-1, this_global.x) == TileType.FREE )
				{
					// desplazar arriba door_other y comprobar mapa
					Vec2 tmp = new Vec2(this_global.x, this_global.y-1);
					if( this.tilemap.CollideWith(door_other.ri_owner.prefab.map, tmp.y, tmp.x) )
					{
						pos = tmp;
					}
				}
			}
		}
		return pos;
	}

	@Override
	public void RenderCanvas() {
		(new MapRenderer(this.tilemap)).Show();
	}

	
}
