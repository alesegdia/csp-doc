package com.alesegdia.jsearchgen.pathbuild.model.doorgraph;

import java.util.List;

import com.alesegdia.jsearchgen.core.room.DoorPairEntry;
import com.alesegdia.jsearchgen.core.util.UpperMatrix2D;

public class RoomGraphInstance {

	public RoomGraphModel rgd = null;
	public UpperMatrix2D<List<DoorPairEntry>> activeLinks = null;

	public RoomGraphInstance( RoomGraphModel rgd ) {
		this.rgd = rgd;
		int sz = rgd.rooms.size();
		activeLinks = new UpperMatrix2D<List<DoorPairEntry>>(sz, sz, null);
	}

}
