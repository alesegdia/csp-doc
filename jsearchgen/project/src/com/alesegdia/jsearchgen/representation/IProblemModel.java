package com.alesegdia.jsearchgen.representation;

import java.util.List;

import com.alesegdia.jsearchgen.map.RoomInstance;

public interface IProblemModel {
	
	public ISolution CreateEmptySolution();
	
	public void AttachRoomToSolution(RoomInstance selected_room, ISolution partial_solution);

	public RoomInstance InsertFeasibleRoom(List<RoomInstance> remaining_rooms, ISolution partial_solution);

}
