package com.alesegdia.jsearchgen.algo.mapgen;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.algo.common.ISolver;
import com.alesegdia.jsearchgen.core.data.RoomInstance;
import com.alesegdia.jsearchgen.core.util.RNG;

public class RandomSolver implements ISolver {

	List<RoomInstance> startRoomList = new LinkedList<RoomInstance>();
	private IRandomModel randomModel;

	public RandomSolver ( IRandomModel first_solution ) {
		this.randomModel = first_solution;
	}

	@Override
	public void Solve()
	{
		while( !randomModel.IsComplete() )
		{
			if( !randomModel.InsertRandomFeasibleRoom() )
			{
				System.err.println("ERROR: can't build a complete solution from this partial solution and this list of remaining rooms: ");
				break;
			}
		}
	}

}
