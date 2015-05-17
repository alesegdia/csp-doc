package com.alesegdia.jsearchgen.mapgen;

import java.util.List;

import com.alesegdia.jsearchgen.core.room.RoomInstance;
import com.alesegdia.jsearchgen.core.util.RNG;

public class GraphGridProblemModel implements IMapGenProblemModel {

	private static final int SOLUTION_WIDTH = 100;
	private static final int SOLUTION_HEIGHT = 60;

	@Override
	public IMapGenSolution CreateFirstSolution(	List<RoomInstance> remaining_rooms, int width, int height) {
		GraphGridSolution gs = new GraphGridSolution( height, width );
		try {
			int room_index = RNG.rng.nextInt(0, remaining_rooms.size()-1);
			RoomInstance selected = remaining_rooms.get(room_index);
			remaining_rooms.remove(selected);
			selected.globalPosition.Set(30, 10);
			gs.AttachRoom(selected, 30, 10);
			gs.remaining_rooms = remaining_rooms;
		} catch(IndexOutOfBoundsException e) {
			System.err.println("remaining_rooms list empty!");
		}
		return gs;
	}

	@Override
	public IMapGenSolution CreateFirstSolution(	List<RoomInstance> remaining_rooms) {
		return CreateFirstSolution(remaining_rooms, SOLUTION_WIDTH, SOLUTION_HEIGHT);
	}

	@Override
	public boolean InsertRandomFeasibleRoom(IMapGenSolution partial_solution) {
		GraphGridSolution gs = ((GraphGridSolution) partial_solution);
		return gs.AttachRandomFeasibleRoom();
	}

}
