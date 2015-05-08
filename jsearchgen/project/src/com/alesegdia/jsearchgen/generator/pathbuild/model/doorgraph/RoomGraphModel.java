package com.alesegdia.jsearchgen.generator.pathbuild.model.doorgraph;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.core.map.room.Door;
import com.alesegdia.jsearchgen.core.map.room.DoorPairEntry;
import com.alesegdia.jsearchgen.core.map.room.RoomInstance;
import com.alesegdia.jsearchgen.core.util.UpperMatrix2D;
import com.alesegdia.jsearchgen.core.util.Vec2;
import com.alesegdia.jsearchgen.generator.mapgen.model.graphgrid.GraphGridSolution;

public class RoomGraphModel {

	public List<RoomInstance> rooms;
	public UpperMatrix2D<List<DoorPairEntry>> possibleLinks = null;

	public void BuildFromGraphGridSolution(GraphGridSolution graphgridsolution) {
		
		// Asignamos ID para cada habitación
		int i = 0;
		for( RoomInstance roominstance : graphgridsolution.added_rooms ) {
			roominstance.id = i;
			i++;
		}
		
		possibleLinks = new UpperMatrix2D<List<DoorPairEntry>>(i, i, null);
		
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

		// Borrar duplicados en possibleLinks (mismas puertas)
	}

	private void HandleRoomVsRoom(GraphGridSolution graphgridsolution, RoomInstance roominstance_outer, RoomInstance roominstance_inner) {
		for( Door door_inner : roominstance_inner.doors ) {
			for( Door door_outer : roominstance_outer.doors ) {
				HandleDoorVsDoor(graphgridsolution, door_inner, door_outer);
			}
		}
	}
	
	private void AddOrCreateAndAdd(Door inner, Door outer, DoorPairEntry dpe) {
		int id1, id2;
		id1 = inner.connected_room.id;
		id2 = outer.connected_room.id;
		List<DoorPairEntry> dpe_list = possibleLinks.Get(id1, id2);
		if( dpe_list == null ) {
			dpe_list = new LinkedList<DoorPairEntry>();
			possibleLinks.Set(id1, id2, dpe_list);
		}
		dpe_list.add(dpe);
	}

	private void HandleDoorVsDoor(GraphGridSolution graphgridsolution, Door door_inner, Door door_outer) {
		Vec2 pos = graphgridsolution.IsPossibleDoorCombination(door_inner, door_outer);
		if( pos != null ) {
			DoorPairEntry dpe = new DoorPairEntry();
			dpe.other_door = door_inner;
			dpe.this_door = door_outer;
			dpe.relativeToSolutionMap = pos;
			AddOrCreateAndAdd(door_inner, door_outer, dpe);
		}
	}

}
