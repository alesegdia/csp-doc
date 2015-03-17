package com.alesegdia.jsearchgen.representation;

import com.alesegdia.jsearchgen.map.RoomInstance;

public interface IProblemModel {
	
	public ISolution CreateEmptySolution();
	
	public RoomInstance CreateEmptyRoom();
	
	public void AttachRoomToSolution(RoomInstance selected_room, ISolution partial_solution);

}
