package com.alesegdia.jsearchgen.algo.roomselect;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.core.data.DoorPairEntry;
import com.alesegdia.jsearchgen.core.data.RoomInstance;
import com.alesegdia.jsearchgen.core.trash.IGeneticModel;
import com.alesegdia.jsearchgen.core.trash.IGeneticSolution;
import com.alesegdia.jsearchgen.core.util.Matrix2D;
import com.alesegdia.jsearchgen.core.util.UpperMatrix2D;

class GroupData {
	int room1_index;
	int room2_index;
	int start_index;
}

class RoomData {
	RoomInstance room;
	List<GroupData> groups = new ArrayList<GroupData>();
}

public class DoorBitsModel implements IFloydWarshallModel {

	GroupData groupsData[];
	DoorPairEntry dpes[];
	RoomData roomsData[];
	private MapGraph mg;
	
	public DoorBitsModel(MapGraph mg) {
		this.mg = mg;
		Cache(mg);
	}

	private void Cache(MapGraph mg) {
		groupsData = new GroupData[mg.NumPossibleRoomConnections()];
		dpes = new DoorPairEntry[mg.NumAllLinks()];
		roomsData = new RoomData[mg.NumRooms()];
		for( RoomInstance ri : mg.rooms ) {
			roomsData[ri.id] = new RoomData();
			roomsData[ri.id].room = ri;
		}
		int group_index = 0;
		int global_index = 0;
		for(int i = 0; i < mg.NumRooms(); i++) {
			for( int j = i+1; j < mg.NumRooms(); j++ ) {
				List<DoorPairEntry> dpe_list = mg.possibleLinksUpperMatrix.GetUpper(i,j);
				if( dpe_list != null ) {
					groupsData[group_index] = new GroupData();
					int id1, id2;
					id1 = dpe_list.get(0).this_door.ri_owner.id;
					id2 = dpe_list.get(0).other_door.ri_owner.id;
					groupsData[group_index].room1_index = id1;
					groupsData[group_index].room2_index = id2;
					groupsData[group_index].start_index = global_index;
					roomsData[id1].groups.add(groupsData[group_index]);
					roomsData[id2].groups.add(groupsData[group_index]);
					group_index++;
					for( DoorPairEntry dpe : dpe_list ) {
						dpes[global_index] = dpe;
						global_index++;
					}
				}
			}
		}
	}

	@Override
	public int NumRooms() {
		return mg.NumRooms();
	}

	@Override
	public UpperMatrix2D<Float> CloneSCM() {
		return mg.CloneSimplifiedConnectionMatrix();
	}

}
