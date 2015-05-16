package com.alesegdia.jsearchgen.proxy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.core.room.Door;
import com.alesegdia.jsearchgen.core.room.DoorPairEntry;
import com.alesegdia.jsearchgen.core.room.RoomInstance;
import com.alesegdia.jsearchgen.core.util.RNG;
import com.alesegdia.jsearchgen.core.util.Vec2;
import com.alesegdia.jsearchgen.mapgen.GraphGridSolution;
import com.alesegdia.jsearchgen.mapgen.IMapGenSolution;

public class GraphGridAllProxy implements IMapGenPathBuildProxy {

	GraphGridSolution ggs;

	public GraphGridAllProxy( GraphGridSolution ggs ) {
		this.ggs = ggs;
	}

	@Override
	public List<DoorPairEntry> GetDoorPairList() {
		List<DoorPairEntry> feasible_doors = new LinkedList<DoorPairEntry>();
		List<RoomInstance> rooms = GetRooms();
		for( int i = 0; i < rooms.size(); i++ ) {
			RoomInstance outer = rooms.get(i);
			for( int j = i+1; j < rooms.size(); j++ ) {
				RoomInstance inner = rooms.get(j);
				feasible_doors.addAll(HandleRoomVsRoom(inner, outer));
			}
		}
		return feasible_doors;
	}

	private List<DoorPairEntry> HandleRoomVsRoom(RoomInstance inner, RoomInstance outer) {
		List<DoorPairEntry> dpes = new LinkedList<DoorPairEntry>();
		for( Door inner_door : inner.doors ) {
			for( Door outer_door : outer.doors ) {
				int dx, dy;
				Vec2 inner_global = inner_door.GetGlobalPosition();
				Vec2 outer_global = outer_door.GetGlobalPosition();
				dx = Math.abs(inner_global.x - outer_global.x);
				dy = Math.abs(inner_global.y - outer_global.y);
				if( dx + dy == 1 ) {
					// creamos el par y lo metemos en la lista a devolver
					Vec2 relative_to_this_map = this.ggs.IsPossibleDoorCombination( inner_door, outer_door, false );
					if( relative_to_this_map != null ) {
						DoorPairEntry dpe = new DoorPairEntry();
						dpe.relativeToSolutionMap = relative_to_this_map;
						dpe.other_door = inner_door;
						dpe.this_door = outer_door;
						dpes.add(dpe);
					}
				}
			}
		}
		return dpes;
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
