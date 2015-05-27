package com.alesegdia.jsearchgen.algo.mapgen.proxy;

import java.util.List;

import com.alesegdia.jsearchgen.algo.mapgen.GraphGridModel;
import com.alesegdia.jsearchgen.algo.mapgen.IRandomModel;
import com.alesegdia.jsearchgen.core.data.DoorPairEntry;
import com.alesegdia.jsearchgen.core.data.RoomInstance;

public class GraphGridSimpleProxy implements IMapGenProxy {

	GraphGridModel ggs;

	public GraphGridSimpleProxy( GraphGridModel ggs ) {
		this.ggs = ggs;
	}

	@Override
	public List<DoorPairEntry> GetDoorPairList() {
		return ggs.added_dpes;
	}

	@Override
	public List<RoomInstance> GetRooms() {
		return ggs.added_rooms;
	}

}
