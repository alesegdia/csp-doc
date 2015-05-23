package com.alesegdia.jsearchgen.mapgen.representation;

import java.util.List;

import com.alesegdia.jsearchgen.core.room.RoomInstance;

public interface IRandomModel {

	public IRandomSolution CreateFirstSolution(List<RoomInstance> remaining_rooms, int width, int height);
	public IRandomSolution CreateFirstSolution(List<RoomInstance> remaining_rooms);
	public boolean InsertRandomFeasibleRoom(IRandomSolution partial_solution);
	
}
