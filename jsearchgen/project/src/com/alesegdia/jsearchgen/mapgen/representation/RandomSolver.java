package com.alesegdia.jsearchgen.mapgen.representation;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.core.room.RoomInstance;
import com.alesegdia.jsearchgen.core.util.RNG;

public class RandomSolver implements IMapGenSolver{

	List<RoomInstance> startRoomList = new LinkedList<RoomInstance>();
	RNG rng = new RNG();

	public RandomSolver ( long seed ) {
		rng.setSeed(seed);
	}

	@Override
	public IMapGenSolution Generate( List<RoomInstance> initial_room_list, IRandomModel problem_model )
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
