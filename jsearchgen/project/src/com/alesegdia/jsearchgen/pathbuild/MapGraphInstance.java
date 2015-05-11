package com.alesegdia.jsearchgen.pathbuild;

import java.util.List;

import com.alesegdia.jsearchgen.core.room.DoorPairEntry;
import com.alesegdia.jsearchgen.core.util.UpperMatrix2D;

public class MapGraphInstance {

	public MapGraphModel rgd = null;
	public UpperMatrix2D<List<DoorPairEntry>> activeLinks = null;

	public MapGraphInstance( MapGraphModel rgd ) {
		this.rgd = rgd;
		int sz = rgd.rooms.size();
		activeLinks = new UpperMatrix2D<List<DoorPairEntry>>(sz, sz, null);
	}

}
