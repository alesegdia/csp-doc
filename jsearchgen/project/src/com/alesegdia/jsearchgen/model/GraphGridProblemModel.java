package com.alesegdia.jsearchgen.model;

import java.util.List;

import com.alesegdia.jsearchgen.map.TileMap;
import com.alesegdia.jsearchgen.map.TileType;
import com.alesegdia.jsearchgen.room.RoomInstance;
import com.alesegdia.jsearchgen.util.RNG;

public class GraphGridProblemModel implements IProblemModel {
	
	private static final int SOLUTION_WIDTH = 100;
	private static final int SOLUTION_HEIGHT = 60;
	

	@Override
	public void AttachRoomToSolution(RoomInstance selected_room, ISolution partial_solution) {
		
	}
	
	@Override
	public boolean InsertRandomFeasibleRoom(ISolution partial_solution) {
		GraphGridSolution gs = ((GraphGridSolution) partial_solution);
		return gs.AttachRandomFeasibleRoom();
	}

	@Override
	public ISolution CreateFirstSolution(List<RoomInstance> remaining_rooms) {
		GraphGridSolution gs = new GraphGridSolution( SOLUTION_HEIGHT, SOLUTION_WIDTH );
		try {
			int room_index = RNG.rng.nextInt(0, remaining_rooms.size()-1);
			//System.out.println("room_index: " + room_index);
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

}
