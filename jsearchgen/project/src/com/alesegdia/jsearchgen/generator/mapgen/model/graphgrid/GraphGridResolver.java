package com.alesegdia.jsearchgen.generator.mapgen.model;

import java.util.List;

import com.alesegdia.jsearchgen.core.map.room.RoomInstance;
import com.alesegdia.jsearchgen.core.util.RNG;

public class GraphGridModel implements IMapGenModel {
	
	private static final int SOLUTION_WIDTH = 100;
	private static final int SOLUTION_HEIGHT = 60;
	

	@Override
	public void AttachRoomToSolution(RoomInstance selected_room, IMapGenSolution partial_solution) {
		
	}
	
	@Override
	public boolean InsertRandomFeasibleRoom(IMapGenSolution partial_solution) {
		GraphGridSolution gs = ((GraphGridSolution) partial_solution);
		return gs.AttachRandomFeasibleRoom();
	}

	@Override
	public IMapGenSolution CreateFirstSolution(List<RoomInstance> remaining_rooms) {
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
