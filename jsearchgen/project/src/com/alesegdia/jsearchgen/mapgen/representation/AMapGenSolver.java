package com.alesegdia.jsearchgen.mapgen.representation;

import java.util.List;

import com.alesegdia.jsearchgen.core.room.RoomInstance;

public abstract class AMapGenSolver<SolutionClass, ModelClass> {

	public SolutionClass Generate(List<RoomInstance> initial_room_list, ModelClass problem_model) throws Exception
	{
		throw new Exception("NOT IMPLEMENTED");
	}
}
