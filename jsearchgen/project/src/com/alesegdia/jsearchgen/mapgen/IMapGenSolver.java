package com.alesegdia.jsearchgen.mapgen;

import java.util.List;

import com.alesegdia.jsearchgen.core.room.RoomInstance;

public interface IMapGenSolver {
	
	public IMapGenSolution CreateFirstSolution(List<RoomInstance> remaining_rooms, int width, int height);
	public IMapGenSolution CreateFirstSolution(List<RoomInstance> remaining_rooms);
	
	public boolean InsertRandomFeasibleRoom(IMapGenSolution partial_solution);

	public IMapGenSolution Generate( List<RoomInstance> initial_room_list );
	
}
