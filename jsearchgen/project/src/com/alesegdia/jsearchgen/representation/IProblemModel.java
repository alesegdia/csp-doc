package com.alesegdia.jsearchgen.representation;

import java.util.List;

import com.alesegdia.jsearchgen.map.RoomInstance;

public interface IProblemModel {
	
	public ISolution CreateFirstSolution(List<RoomInstance> remaining_rooms);
	
	public void AttachRoomToSolution(RoomInstance selected_room, ISolution partial_solution);

	public boolean InsertRandomFeasibleRoom(ISolution partial_solution);

}
