package com.alesegdia.jsearchgen.algo.mapgen;

import java.util.List;

import com.alesegdia.jsearchgen.core.data.RoomInstance;

public interface IRandomModel extends IMapGenModel{
	
	List<RoomInstance> GetRemainingRooms();
	public boolean InsertRandomFeasibleRoom();
	public boolean IsComplete();

}
