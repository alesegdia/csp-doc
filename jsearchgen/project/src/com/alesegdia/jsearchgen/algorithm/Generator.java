package com.alesegdia.jsearchgen.algorithm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.map.RoomInstance;
import com.alesegdia.jsearchgen.representation.IProblemModel;
import com.alesegdia.jsearchgen.representation.ISolution;
import com.alesegdia.jsearchgen.util.RNG;

public class Generator {

	List<RoomInstance> startRoomList = new LinkedList<RoomInstance>();
	RNG rng = new RNG();

	public Generator ( long seed ) {
		rng.setSeed(seed);
	}

	public void SetStartRoomList(List<RoomInstance> room_list)
	{
		this.startRoomList = room_list;
	}

	List<RoomInstance> CloneRoomList( List<RoomInstance> cloned )
	{
		List<RoomInstance> clone = new LinkedList<RoomInstance>();
		Collections.copy(clone, cloned);
		return clone;
	}

	/*
	RoomInstance SelectRandomFeasibleRoom( List<RoomInstance> possible_rooms, ISolution partial_solution )
	{
		List<RoomInstance> feasible_rooms = new ArrayList<RoomInstance>();
		for( Iterator<RoomInstance> it = possible_rooms.iterator(); it.hasNext(); )
		{
			RoomInstance room = it.next();
			if( partial_solution.IsPossibleAddition(room) )
				feasible_rooms.add(room);
		}
		int room_index = rng.nextInt(feasible_rooms.size());
		RoomInstance selected = feasible_rooms.get(room_index);
		return selected;
	}
	*/

	public ISolution Generate( List<RoomInstance> initial_room_list, IProblemModel problem_model )
	{
		ISolution partial_solution = problem_model.CreateFirstSolution(initial_room_list);

		while( partial_solution.IsComplete() )
		{
			RoomInstance selected_room = problem_model.InsertRandomFeasibleRoom( partial_solution );
			if( selected_room == null )
			{
				System.err.println("ERROR: can't build a complete solution from this partial solution and this list of remaining rooms: ");
				partial_solution.Render();
				System.exit(1);
			}
		}

		return partial_solution;
	}

}
