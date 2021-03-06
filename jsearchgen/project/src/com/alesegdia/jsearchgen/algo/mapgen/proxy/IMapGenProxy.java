package com.alesegdia.jsearchgen.algo.mapgen.proxy;

import java.util.List;

import com.alesegdia.jsearchgen.core.data.DoorPairEntry;
import com.alesegdia.jsearchgen.core.data.RoomInstance;

public interface IMapGenProxy {

	public List<DoorPairEntry> GetDoorPairList();
	public List<RoomInstance> GetRooms();

}
