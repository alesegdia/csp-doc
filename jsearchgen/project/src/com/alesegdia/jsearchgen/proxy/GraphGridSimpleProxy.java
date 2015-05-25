package com.alesegdia.jsearchgen.proxy;

import java.util.List;

import com.alesegdia.jsearchgen.algo.mapgen.GraphGridSolution;
import com.alesegdia.jsearchgen.algo.mapgen.IMapGenSolution;
import com.alesegdia.jsearchgen.core.data.DoorPairEntry;
import com.alesegdia.jsearchgen.core.data.RoomInstance;

public class GraphGridSimpleProxy implements IMapGenPathBuildProxy {

	GraphGridSolution ggs;

	public GraphGridSimpleProxy( GraphGridSolution ggs ) {
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

	@Override
	public IMapGenSolution GetSolution() {
		return ggs;
	}

}
