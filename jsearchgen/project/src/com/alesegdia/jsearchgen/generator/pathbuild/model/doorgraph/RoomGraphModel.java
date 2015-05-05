package com.alesegdia.jsearchgen.generator.pathbuild.model.doorgraph;

import com.alesegdia.jsearchgen.core.map.room.Door;
import com.alesegdia.jsearchgen.core.map.room.DoorPairEntry;
import com.alesegdia.jsearchgen.core.map.room.RoomInstance;
import com.alesegdia.jsearchgen.core.util.Vec2;
import com.alesegdia.jsearchgen.generator.mapgen.model.graphgrid.GraphGridSolution;

public class RoomGraphModel {

	public void BuildFromGraphGridSolution(GraphGridSolution graphgridsolution) {
		// Computamos las potenciales combinaciones (quizá un subconjunto de ellas?)
		for( RoomInstance roominstance_outer : graphgridsolution.added_rooms ) {
			RoomGraphNode rgn = new RoomGraphNode();
			rgn.room = roominstance_outer;
			for( RoomInstance roominstance_inner : graphgridsolution.added_rooms ) {
				if( roominstance_outer != roominstance_inner ) {
					HandleRoomVsRoom(graphgridsolution, roominstance_outer, roominstance_inner);
				}
			}
		}
	}

	private void HandleRoomVsRoom(GraphGridSolution graphgridsolution, RoomInstance roominstance_outer, RoomInstance roominstance_inner) {
		for( Door door_inner : roominstance_inner.doors ) {
			for( Door door_outer : roominstance_outer.doors ) {
				HandleDoorVsDoor(graphgridsolution, door_inner, door_outer);
			}
		}		
	}

	private void HandleDoorVsDoor(GraphGridSolution graphgridsolution, Door door_inner, Door door_outer) {
		Vec2 pos = graphgridsolution.IsPossibleDoorCombination(door_inner, door_outer);
		if( pos != null ) {
			DoorPairEntry dpe = new DoorPairEntry();
			dpe.other_door = door_inner;
			dpe.this_door = door_outer;
			dpe.relativeToSolutionMap = pos;
			// check if duplicate?			
		}
	}

}
