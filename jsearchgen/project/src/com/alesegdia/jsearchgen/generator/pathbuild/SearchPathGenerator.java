package com.alesegdia.jsearchgen.generator.pathbuild;

import java.util.List;

import com.alesegdia.jsearchgen.core.util.RNG;
import com.alesegdia.jsearchgen.generator.mapgen.model.IMapGenModel;
import com.alesegdia.jsearchgen.generator.mapgen.model.IMapGenSolution;
import com.alesegdia.jsearchgen.room.RoomInstance;

public class SearchPathGenerator {
	RNG rng = new RNG();
	private List<RoomInstance> startRoomList;

	public SearchPathGenerator(long seed) {
		rng.setSeed(seed);
	}
	
	public void SetStartRoomList(List<RoomInstance> room_list) {
		this.startRoomList = room_list;
	}
	
	public IMapGenSolution Generate( List<RoomInstance> initial_room_list, IMapGenModel problem_model ) {
		IMapGenSolution partial = problem_model.CreateFirstSolution(initial_room_list);
		return null;
	}
}
