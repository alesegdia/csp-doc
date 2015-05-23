package com.alesegdia.jsearchgen.proxy;

import java.util.List;

import com.alesegdia.jsearchgen.core.room.DoorPairEntry;
import com.alesegdia.jsearchgen.core.room.RoomInstance;
import com.alesegdia.jsearchgen.mapgen.representation.GraphGridSolution;
import com.alesegdia.jsearchgen.mapgen.representation.IRandomSolution;

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
	public IRandomSolution GetSolution() {
		return ggs;
	}

}
