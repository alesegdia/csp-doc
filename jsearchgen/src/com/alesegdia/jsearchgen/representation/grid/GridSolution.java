package com.alesegdia.jsearchgen.representation.grid;

import com.alesegdia.jsearchgen.representation.IRoom;
import com.alesegdia.jsearchgen.representation.ISolution;

public class GridSolution implements ISolution {

	private int[] grid;
	private enum TileType {
		WALL, // muro
		DOOR, // puerta
		FREE, // hueco libre
		USED  // hueco libre perteneciente a una habitación
	}
	
	GridSolution( int rows, int cols )
	{
		grid = new int[rows * cols];
	}

	@Override
	public boolean IsPossibleAddition(IRoom room) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void Render() {
		// TODO Auto-generated method stub
		
	}
	
}
