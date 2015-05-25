package com.alesegdia.jsearchgen.algo.roomselect;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.core.data.DoorPairEntry;
import com.alesegdia.jsearchgen.core.data.RoomInstance;
import com.alesegdia.jsearchgen.core.util.UpperMatrix2D;
import com.alesegdia.jsearchgen.proxy.IMapGenPathBuildProxy;

public class MapGraph {

	public List<RoomInstance> rooms;
	public UpperMatrix2D<List<DoorPairEntry>> possibleLinksUpperMatrix = null;
	public List<List<DoorPairEntry>> possibleLinksPerRoom = new ArrayList<List<DoorPairEntry>>();
	public UpperMatrix2D<Float> simplifiedConnectionMatrix;
	
	// para clonarse cada vez que se pida una nueva
	private MapGraphData cleanInstance;
	
	// NO SE ESTÁ CLONANDO AHORA MISMO
	public MapGraphData CreateCleanInstance() {
		return cleanInstance.Clone();
	}
	
	public UpperMatrix2D<Float> CloneSimplifiedConnectionMatrix() {
		return new UpperMatrix2D<Float>(simplifiedConnectionMatrix);
	}

	// cada habitación tiene un ID que se usará para identificarla de forma única
	// y usarla como índice
	public void BuildFromGraphGridSolution(IMapGenPathBuildProxy dpp ) {

		// Asignamos ID para cada habitación
		int i = 0;
		for( RoomInstance roominstance : dpp.GetRooms() ) {
			roominstance.id = i;
			possibleLinksPerRoom.add(new ArrayList<DoorPairEntry>());
			i++;
		}
		System.out.println("END i: " + i);

		possibleLinksUpperMatrix = new UpperMatrix2D<List<DoorPairEntry>>(i, i, null);
		simplifiedConnectionMatrix = new UpperMatrix2D<Float>(i, i, -1.f);

		List<DoorPairEntry> dpes = dpp.GetDoorPairList();
		for( DoorPairEntry dpe : dpes ) {
			AppendLink(dpe);
			possibleLinksPerRoom.get(dpe.other_door.ri_owner.id).add(dpe);
			possibleLinksPerRoom.get(dpe.this_door.ri_owner.id).add(dpe);
		}
		
		rooms = dpp.GetRooms();
		
		PrepareCleanInstance();
	
		for( i = 0; i < possibleLinksUpperMatrix.cols; i++ ) {
			for( int j = 0; j < possibleLinksUpperMatrix.rows; j++ ) {
				List<DoorPairEntry> l = possibleLinksUpperMatrix.Get(i, j);
				if( l != null ) {
					RoomInstance r1, r2;
					r1 = rooms.get(i);
					r2 = rooms.get(j);
					simplifiedConnectionMatrix.Set(i, j, r1.globalPosition.distance(r2.globalPosition));
				}
				else
				{
					simplifiedConnectionMatrix.Set(i, j, Float.MAX_VALUE);
				}
			}
			System.out.println();
		}
	
	}
	
	public int NumRooms() {
		return possibleLinksUpperMatrix.cols;
	}
	
	private void PrepareCleanInstance() {
		cleanInstance = new MapGraphData(this);
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
		
		for( i = 0; i < simplifiedConnectionMatrix.cols; i++ ) {
			for( int j = 0; j < simplifiedConnectionMatrix.rows; j++ ) {
				Float f = simplifiedConnectionMatrix.Get(i, j);
				if( f == -1 ) {
					System.out.print("·\t");
				} else {
					System.out.print(f.intValue());
					System.out.print("\t");
				}
			}
			System.out.println();
		}


	}

	Integer numPossibleConnections = null;
	Integer numAllLinks = null;
	public int NumPossibleRoomConnections() {
		if( numPossibleConnections == null ) {
			ComputePossibleConnections();
		}
		return numPossibleConnections.intValue();
	}
	
	public int NumAllLinks() {
		if( numPossibleConnections == null ) {
			ComputePossibleConnections();
		}
		return numAllLinks.intValue();
	}

	private void ComputePossibleConnections() {
		numPossibleConnections = 0;
		numAllLinks = 0;
		for( int i = 0; i < possibleLinksUpperMatrix.cols; i++ ) {
			for( int j = 0; j < possibleLinksUpperMatrix.rows; j++ ) {
				List<DoorPairEntry> l = possibleLinksUpperMatrix.Get(i, j);
				if( l != null ) {
					numPossibleConnections++;
					numAllLinks += l.size();
				}
			}
		}
	}

}
