package com.alesegdia.jsearchgen.pathbuild;

import java.util.List;

import com.alesegdia.jsearchgen.core.room.RoomInstance;
import com.alesegdia.jsearchgen.core.util.RNG;
import com.alesegdia.jsearchgen.mapgen.IMapGenResolver;
import com.alesegdia.jsearchgen.mapgen.IMapGenSolution;

public class SearchPathResolver {
	RNG rng = new RNG();
	private List<RoomInstance> startRoomList;

	public SearchPathResolver(long seed) {
		rng.setSeed(seed);
	}
	
	public void SetStartRoomList(List<RoomInstance> room_list) {
		this.startRoomList = room_list;
	}
	
	public IMapGenSolution Generate( List<RoomInstance> initial_room_list, IMapGenResolver problem_model ) {
		IMapGenSolution partial = problem_model.CreateFirstSolution(initial_room_list);
		return null;
	}
}
