package com.alesegdia.jsearchgen.mapgen.representation;

import java.util.List;

import com.alesegdia.jsearchgen.core.room.RoomInstance;

public interface IMapGenSolver {

	public IMapGenSolution Generate(List<RoomInstance> initial_room_list, IRandomModel problem_model);
}
