package com.alesegdia.jsearchgen.algo.mapgen;

import java.util.List;

import com.alesegdia.jsearchgen.core.data.Door;
import com.alesegdia.jsearchgen.core.data.RoomInstance;
import com.alesegdia.jsearchgen.core.util.Vec2;

public interface IMapGenModel {

	Vec2 IsPossibleDoorCombination(Door inner_door, Door outer_door, boolean b);
	List<RoomInstance> GetRooms();

}
