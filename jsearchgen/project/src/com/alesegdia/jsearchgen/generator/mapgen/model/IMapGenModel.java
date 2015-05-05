package com.alesegdia.jsearchgen.generator.mapgen.model;

import java.util.List;

import com.alesegdia.jsearchgen.room.RoomInstance;

public interface IMapGenModel {
	
	public IMapGenSolution CreateFirstSolution(List<RoomInstance> remaining_rooms);
	
	public void AttachRoomToSolution(RoomInstance selected_room, IMapGenSolution partial_solution);

	public boolean InsertRandomFeasibleRoom(IMapGenSolution partial_solution);

}
