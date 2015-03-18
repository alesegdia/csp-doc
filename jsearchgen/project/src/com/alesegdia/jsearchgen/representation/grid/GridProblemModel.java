package com.alesegdia.jsearchgen.representation.grid;

import java.util.List;

import com.alesegdia.jsearchgen.map.RoomInstance;
import com.alesegdia.jsearchgen.representation.IProblemModel;
import com.alesegdia.jsearchgen.representation.ISolution;
import com.alesegdia.jsearchgen.util.RNG;

public class GridProblemModel implements IProblemModel {
	
	private static final int SOLUTION_WIDTH = 64;
	private static final int SOLUTION_HEIGHT = 64;
	

	@Override
	public void AttachRoomToSolution(RoomInstance selected_room, ISolution partial_solution) {
		
	}

	@Override
	public RoomInstance InsertRandomFeasibleRoom(ISolution partial_solution) {
		GridSolution gs = (GridSolution) partial_solution;
		gs.AttachRandomFeasibleRoom();
		return null;
	}

	@Override
	public ISolution CreateFirstSolution(List<RoomInstance> remaining_rooms) {
		// TODO Auto-generated method stub
		GridSolution gs = new GridSolution( SOLUTION_WIDTH, SOLUTION_HEIGHT );
		int room_index = RNG.rng.nextInt(0, remaining_rooms.size());
		RoomInstance selected = remaining_rooms.get(room_index);
		remaining_rooms.remove(selected);
		gs.AttachRoom(selected, 32, 32);
		return gs;
	}

}
