package com.alesegdia.jsearchgen.pathbuild;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.core.room.Door;
import com.alesegdia.jsearchgen.core.room.DoorPairEntry;
import com.alesegdia.jsearchgen.core.room.RoomInstance;
import com.alesegdia.jsearchgen.core.util.UpperMatrix2D;
import com.alesegdia.jsearchgen.core.util.Vec2;
import com.alesegdia.jsearchgen.mapgen.GraphGridSolution;

public class MapGraphModel {

	public List<RoomInstance> rooms;
	public UpperMatrix2D<List<DoorPairEntry>> possibleLinksUpperMatrix = null;
	public List<List<DoorPairEntry>> possibleLinksPerRoom = new ArrayList<List<DoorPairEntry>>();

	// cada habitación tiene un ID que se usará para identificarla de forma única
	// y usarla como índice
	public void BuildFromGraphGridSolution(GraphGridSolution graphgridsolution) {

		// Asignamos ID para cada habitación
		int i = 0;
		for( RoomInstance roominstance : graphgridsolution.added_rooms ) {
			roominstance.id = i;
			possibleLinksPerRoom.add(new ArrayList<DoorPairEntry>());
			i++;
		}
		System.out.println("END i: " + i);

		possibleLinksUpperMatrix = new UpperMatrix2D<List<DoorPairEntry>>(i, i, null);

		for( DoorPairEntry dpe : graphgridsolution.added_dpes ) {
			AppendLink(dpe);
			possibleLinksPerRoom.get(dpe.other_door.ri_owner.id).add(dpe);
			possibleLinksPerRoom.get(dpe.this_door.ri_owner.id).add(dpe);
		}
		
		// Borrar duplicados en possibleLinks (mismas puertas)
	}

	private void AppendLink(DoorPairEntry dpe) {
		int id1, id2;
		id1 = dpe.other_door.ri_owner.id;
		id2 = dpe.this_door.ri_owner.id;

		List<DoorPairEntry> dpe_list = possibleLinksUpperMatrix.GetUpper(id2, id1);
		if( dpe_list == null ) {
			dpe_list = new LinkedList<DoorPairEntry>();
			possibleLinksUpperMatrix.SetUpper(id2, id1, dpe_list);
		}
		dpe_list.add(dpe);
	}

	public void Debug() {
		for( int i = 0; i < possibleLinksUpperMatrix.cols; i++ ) {
			for( int j = 0; j < possibleLinksUpperMatrix.rows; j++ ) {
				List<DoorPairEntry> l = possibleLinksUpperMatrix.Get(i, j);
				if( l == null ) {
					System.out.print("· ");
				} else {
					System.out.print(l.size() + " ");
				}
			}
			System.out.println();
		}

		int i = 0;
		for( List<DoorPairEntry> l : possibleLinksPerRoom) {
			System.out.print("room " + i + " has " + l.size() + " connections: ");
			for( DoorPairEntry dpe : l ) {
				System.out.print(dpe + ", ");
			}
			System.out.println();
			i++;
		}
	}

}
