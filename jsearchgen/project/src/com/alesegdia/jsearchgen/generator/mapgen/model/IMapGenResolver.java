package com.alesegdia.jsearchgen.generator.mapgen.model;

import java.util.List;

import com.alesegdia.jsearchgen.core.map.room.RoomInstance;

public interface IMapGenResolver {
	
	public IMapGenSolution CreateFirstSolution(List<RoomInstance> remaining_rooms);
	
	public void AttachRoomToSolution(RoomInstance selected_room, IMapGenSolution partial_solution);

	public boolean InsertRandomFeasibleRoom(IMapGenSolution partial_solution);

}
