package com.alesegdia.jsearchgen.mapgen.representation;

import java.util.List;

import com.alesegdia.jsearchgen.core.room.RoomInstance;

public interface IRandomModel {

	public IMapGenSolution CreateFirstSolution(List<RoomInstance> remaining_rooms, int width, int height);
	public IMapGenSolution CreateFirstSolution(List<RoomInstance> remaining_rooms);
	public boolean InsertRandomFeasibleRoom(IMapGenSolution partial_solution);
	
}
