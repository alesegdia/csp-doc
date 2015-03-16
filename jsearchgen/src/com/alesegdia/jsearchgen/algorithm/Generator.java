package com.alesegdia.jsearchgen.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.alesegdia.jsearchgen.representation.IProblemModel;
import com.alesegdia.jsearchgen.representation.IRoom;
import com.alesegdia.jsearchgen.representation.ISolution;
import com.alesegdia.jsearchgen.util.RNG;

public class Generator {

	List<IRoom> startRoomList = new LinkedList<IRoom>();
	RNG rng = new RNG();

	public Generator ( long seed ) {
		rng.setSeed(seed);
	}
	
	public void SetStartRoomList(List<IRoom> room_list)
	{
		this.startRoomList = room_list;
	}
	
	List<IRoom> CloneRoomList( List<IRoom> cloned )
	{
		List<IRoom> clone = new LinkedList<IRoom>();
		Collections.copy(clone, cloned);
		return clone;
	}
	
	IRoom SelectRandomFeasibleRoom( List<IRoom> possible_rooms, ISolution partial_solution )
	{
		List<IRoom> feasible_rooms = new ArrayList<IRoom>();
		for( Iterator<IRoom> it = possible_rooms.iterator(); it.hasNext(); )
		{
			IRoom room = it.next();
			if( partial_solution.IsPossibleAddition(room) )
				feasible_rooms.add(room);
		}
		int room_index = rng.nextInt(feasible_rooms.size());
		IRoom selected = feasible_rooms.get(room_index);
		return selected;
	}
	
	public ISolution Generate( List<IRoom> initial_room_list, IProblemModel problem_model )
	{
		List<IRoom> remaining_rooms = CloneRoomList(initial_room_list);
		ISolution partial_solution = problem_model.CreateEmptySolution();

		while( !remaining_rooms.isEmpty() )
		{
			IRoom selected_room = SelectRandomFeasibleRoom( remaining_rooms, partial_solution );
			remaining_rooms.remove(selected_room);
			problem_model.AttachRoomToSolution( selected_room, partial_solution );
		}
		
		return partial_solution;
	}

}
