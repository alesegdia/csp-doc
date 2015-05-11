package com.alesegdia.jsearchgen.pathbuild;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.core.room.RoomInstance;

public class MapGraphNode {

	public RoomInstance room;
	public List<MapGraphLink> links = new LinkedList<MapGraphLink>();

	
}
