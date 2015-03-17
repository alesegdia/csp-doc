package com.alesegdia.jsearchgen.representation.grid;

import com.alesegdia.jsearchgen.map.RoomInstance;
import com.alesegdia.jsearchgen.map.TileMap;
import com.alesegdia.jsearchgen.map.TileType;
import com.alesegdia.jsearchgen.representation.ISolution;

public class GridSolution implements ISolution {

	TileMap tilemap;
	
	GridSolution( int rows, int cols )
	{
		tilemap = new TileMap(rows, cols, TileType.FREE);
	}
	
	GridSolution( GridSolution other )
	{
		tilemap = new TileMap(other.tilemap);
	}

	@Override
	public boolean IsPossibleAddition(RoomInstance room) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void Render() {
		// TODO Auto-generated method stub
		
	}
	
}
