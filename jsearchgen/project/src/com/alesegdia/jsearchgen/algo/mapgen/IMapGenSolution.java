package com.alesegdia.jsearchgen.algo.mapgen;

import java.util.List;

import com.alesegdia.jsearchgen.core.data.RoomInstance;

public interface IMapGenSolution {
	public void RenderCanvas();
	public void Render();
	List<RoomInstance> GetRemainingRooms();
	public boolean InsertRandomFeasibleRoom();
	public boolean IsComplete();

}
