package com.alesegdia.jsearchgen.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.map.Room;
import com.alesegdia.jsearchgen.representation.IProblemModel;
import com.alesegdia.jsearchgen.representation.ISolution;
import com.alesegdia.jsearchgen.util.RNG;

public class Generator {

	List<Room> startRoomList = new LinkedList<Room>();
	RNG rng = new RNG();

	public Generator ( long seed ) {
		rng.setSeed(seed);
	}
	
	public void SetStartRoomList(List<Room> room_list)
	{
		this.startRoomList = room_list;
	}
	
	List<Room> CloneRoomList( List<Room> cloned )
	{
		List<Room> clone = new LinkedList<Room>();
		Collections.copy(clone, cloned);
		return clone;
	}
	
	Room SelectRandomFeasibleRoom( List<Room> possible_rooms, ISolution partial_solution )
	{
		List<Room> feasible_rooms = new ArrayList<Room>();
		for( Iterator<Room> it = possible_rooms.iterator(); it.hasNext(); )
		{
			Room room = it.next();
			if( partial_solution.IsPossibleAddition(room) )
				feasible_rooms.add(room);
		}
		int room_index = rng.nextInt(feasible_rooms.size());
		Room selected = feasible_rooms.get(room_index);
		return selected;
	}
	
	public ISolution Generate( List<Room> initial_room_list, IProblemModel problem_model )
	{
		List<Room> remaining_rooms = CloneRoomList(initial_room_list);
		ISolution partial_solution = problem_model.CreateEmptySolution();

		while( !remaining_rooms.isEmpty() )
		{
			Room selected_room = SelectRandomFeasibleRoom( remaining_rooms, partial_solution );
			remaining_rooms.remove(selected_room);
			problem_model.AttachRoomToSolution( selected_room, partial_solution );
		}
		
		return partial_solution;
	}

}
