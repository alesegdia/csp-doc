package com.alesegdia.jsearchgen.pathbuild;

import java.util.BitSet;
import java.util.List;

import com.alesegdia.jsearchgen.core.room.DoorPairEntry;
import com.alesegdia.jsearchgen.core.util.UpperMatrix2D;

public class MapGraphInstance {

	public MapGraphModel mgm = null;
	public UpperMatrix2D<BitSet> activeLinks = null;

	public MapGraphInstance( MapGraphModel mgm ) {
		this.mgm = mgm;
		int sz = mgm.rooms.size();
		activeLinks = new UpperMatrix2D<BitSet>(sz, sz, null);
		for( int i = 0; i < mgm.rooms.size(); i++ ) {
			for( int j = i+1; j < mgm.rooms.size(); j++ ) {
				List<DoorPairEntry> list = mgm.possibleLinksUpperMatrix.Get(i, j);
				// si existen conexiones entre estas dos puertas
				if( list != null ) {
					BitSet bs = new BitSet(list.size());
					bs.set(0, bs.size(), false);
					activeLinks.Set( i, j, bs );
				}
			}
		}
	}

}
