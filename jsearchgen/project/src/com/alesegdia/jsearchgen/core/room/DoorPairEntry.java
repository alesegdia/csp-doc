package com.alesegdia.jsearchgen.core.room;

import com.alesegdia.jsearchgen.core.util.Vec2;

public class DoorPairEntry {
	public Door other_door;
	public Vec2 relativeToSolutionMap;
	public Door this_door;
	
	@Override
	public String toString() {
		return "[" + other_door.ri_owner.id + "," + this_door.ri_owner.id + "]"; 
	}
}
