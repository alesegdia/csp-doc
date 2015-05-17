package com.alesegdia.jsearchgen.mapgen;

import java.util.List;

import com.alesegdia.jsearchgen.core.room.RoomInstance;

public interface IMapGenSolver {

	public IMapGenSolution Generate( List<RoomInstance> initial_room_list, IMapGenProblemModel problem_model );

}
