package com.alesegdia.jsearchgen.representation.grid;

import com.alesegdia.jsearchgen.map.Room;
import com.alesegdia.jsearchgen.representation.IProblemModel;
import com.alesegdia.jsearchgen.representation.ISolution;

public class GridProblemModel implements IProblemModel {
	
	private static final int SOLUTION_WIDTH = 64;
	private static final int SOLUTION_HEIGHT = 64;
	
	@Override
	public ISolution CreateEmptySolution() {
		// TODO Auto-generated method stub
		GridSolution gs = new GridSolution( SOLUTION_WIDTH, SOLUTION_HEIGHT );
		return gs;
	}

	@Override
	public Room CreateEmptyRoom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void AttachRoomToSolution(Room selected_room, ISolution partial_solution) {
		// TODO Auto-generated method stub
		
	}

}
