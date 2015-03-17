package com.alesegdia.jsearchgen.representation;

import com.alesegdia.jsearchgen.map.Room;

public interface IProblemModel {
	
	public ISolution CreateEmptySolution();
	
	public Room CreateEmptyRoom();
	
	public void AttachRoomToSolution(Room selected_room, ISolution partial_solution);

}
