package com.alesegdia.jsearchgen.pathbuild.model.doorgraph;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.core.room.RoomInstance;

public class MapGraphNode {

	public RoomInstance room;
	public List<MapGraphLink> links = new LinkedList<MapGraphLink>();

	
}
