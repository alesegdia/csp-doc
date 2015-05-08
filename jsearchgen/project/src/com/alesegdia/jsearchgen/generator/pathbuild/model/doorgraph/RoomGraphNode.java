package com.alesegdia.jsearchgen.generator.pathbuild.model.doorgraph;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.core.map.room.RoomInstance;

public class RoomGraphNode {

	public RoomInstance room;
	public List<RoomGraphLink> links = new LinkedList<RoomGraphLink>();

	
}
