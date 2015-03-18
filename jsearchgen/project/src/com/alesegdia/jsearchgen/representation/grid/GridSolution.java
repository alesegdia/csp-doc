package com.alesegdia.jsearchgen.representation.grid;

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
	TileMap tilemap;
	List<Door> opened = new LinkedList<Door>();
	List<Door> closed = new LinkedList<Door>();
	
	GridSolution( int rows, int cols )
	{
		tilemap = new TileMap(rows, cols, TileType.FREE);
	}
	
	GridSolution( GridSolution other )
	{
		tilemap = new TileMap(other.tilemap);
	}

	/**
	 * Check all other room doors against all opened doors in this solution.
	 */
	@Override
	public boolean IsPossibleAddition(RoomInstance room) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void Render() {
		tilemap.Render();
	}
	
}
