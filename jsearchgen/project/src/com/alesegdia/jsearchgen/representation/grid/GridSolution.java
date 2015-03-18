package com.alesegdia.jsearchgen.representation.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.map.Door;
import com.alesegdia.jsearchgen.map.RoomInstance;
import com.alesegdia.jsearchgen.map.TileMap;
import com.alesegdia.jsearchgen.map.TileType;
import com.alesegdia.jsearchgen.representation.ISolution;

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
		this.tilemap.Apply(room.prefab.map, x, y);
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
	
	class FeasibleRoomEntry {
		Door in, out;
	}

	public void AttachRandomFeasibleRoom() {
		// extract feasible doors for each room
		List<Door> feasible_doors = new LinkedList<Door>();
		for( Iterator<RoomInstance> it = remaining_rooms.iterator(); it.hasNext(); )
		{
			RoomInstance room = it.next();
			feasible_doors.addAll(this.GetFeasibleDoorsForRoom(room));
		}
		
		// pickup one and attach
	}

	/**
	 * Check all other room doors against all opened doors in this solution.
	 */
	private List<Door> GetFeasibleDoorsForRoom(RoomInstance room) {
		List<Door> feasible_entries = new LinkedList<Door>();
		for( Iterator<Door> it1 = room.doors.iterator(); it1.hasNext(); )
		{
			for( Iterator<Door> it2 = this.opened.iterator(); it2.hasNext(); )
			{
				
			}
		}
		return feasible_entries;
	}

	
}
