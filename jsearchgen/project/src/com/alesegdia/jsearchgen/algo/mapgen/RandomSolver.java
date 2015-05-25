package com.alesegdia.jsearchgen.algo.mapgen;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.core.data.RoomInstance;
import com.alesegdia.jsearchgen.core.util.RNG;

public class RandomSolver implements IMapGenSolver {

	List<RoomInstance> startRoomList = new LinkedList<RoomInstance>();
	private IMapGenSolution firstSolution;

	public RandomSolver ( IMapGenSolution first_solution ) {
		this.firstSolution = first_solution;
	}

	@Override
	public IMapGenSolution Solve()
	{
		IMapGenSolution partial_solution = firstSolution;
		while( !partial_solution.IsComplete() )
		{
			if( !partial_solution.InsertRandomFeasibleRoom() )
			{
				System.err.println("ERROR: can't build a complete solution from this partial solution and this list of remaining rooms: ");
				break;
			}
		}

		return partial_solution;
	}

}
