package com.alesegdia.jsearchgen.proxy;

import java.util.List;

import com.alesegdia.jsearchgen.core.room.DoorPairEntry;
import com.alesegdia.jsearchgen.core.room.RoomInstance;
import com.alesegdia.jsearchgen.mapgen.representation.IRandomSolution;

public interface IMapGenPathBuildProxy {

	public List<DoorPairEntry> GetDoorPairList();
	public List<RoomInstance> GetRooms();
	public IRandomSolution GetSolution();

}
