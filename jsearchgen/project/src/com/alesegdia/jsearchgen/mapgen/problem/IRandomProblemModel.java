package com.alesegdia.jsearchgen.mapgen.problem;

import java.util.List;

import com.alesegdia.jsearchgen.core.room.RoomInstance;
import com.alesegdia.jsearchgen.mapgen.solution.IMapGenSolution;

public interface IRandomProblemModel {

	public IMapGenSolution CreateFirstSolution(List<RoomInstance> remaining_rooms, int width, int height);
	public IMapGenSolution CreateFirstSolution(List<RoomInstance> remaining_rooms);
	public boolean InsertRandomFeasibleRoom(IMapGenSolution partial_solution);
	
}
