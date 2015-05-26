package com.alesegdia.jsearchgen.proxy;

import java.util.List;

import com.alesegdia.jsearchgen.algo.mapgen.IRandomModel;
import com.alesegdia.jsearchgen.core.data.DoorPairEntry;
import com.alesegdia.jsearchgen.core.data.RoomInstance;

public interface IMapGenRoomSelectProxy {

	public List<DoorPairEntry> GetDoorPairList();
	public List<RoomInstance> GetRooms();

}
