package com.alesegdia.jsearchgen.mapgen.solver;

import java.util.List;

import com.alesegdia.jsearchgen.core.room.RoomInstance;
import com.alesegdia.jsearchgen.mapgen.problem.IRandomProblemModel;
import com.alesegdia.jsearchgen.mapgen.solution.IMapGenSolution;

public interface IMapGenSolver {

	public IMapGenSolution Generate(List<RoomInstance> initial_room_list, IRandomProblemModel problem_model);
}
