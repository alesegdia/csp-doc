package com.alesegdia.jsearchgen.mapgen.solver;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.core.room.RoomInstance;
import com.alesegdia.jsearchgen.core.util.RNG;
import com.alesegdia.jsearchgen.mapgen.problem.IRandomProblemModel;
import com.alesegdia.jsearchgen.mapgen.solution.IMapGenSolution;

public class RandomRoomSolver implements IMapGenSolver{

	List<RoomInstance> startRoomList = new LinkedList<RoomInstance>();
	RNG rng = new RNG();

	public RandomRoomSolver ( long seed ) {
		rng.setSeed(seed);
	}

	@Override
	public IMapGenSolution Generate( List<RoomInstance> initial_room_list, IRandomProblemModel problem_model )
	{
		IMapGenSolution partial_solution = problem_model.CreateFirstSolution(initial_room_list);

		while( !partial_solution.IsComplete() )
		{
			if( !problem_model.InsertRandomFeasibleRoom( partial_solution ) )
			{
				System.err.println("ERROR: can't build a complete solution from this partial solution and this list of remaining rooms: ");
				break;
			}
		}

		return partial_solution;
	}

}
