package com.alesegdia.jsearchgen.algo.pathopt;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.alesegdia.jsearchgen.algo.roomselect.FloydWarshallSolver;
import com.alesegdia.jsearchgen.algo.roomselect.MapGraphModel;
import com.alesegdia.jsearchgen.core.data.DoorPairEntry;
import com.alesegdia.jsearchgen.core.data.RoomInstance;
import com.alesegdia.jsearchgen.core.util.Matrix2D;
import com.alesegdia.jsearchgen.core.util.RNG;
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

class RemovableLink {
	int incoming_room_index;
	int outgoing_room_index;
}

class RoomRemovableData {
	List<RemovableLink> rl_list = new LinkedList<RemovableLink>();
}

public class DoorBitsModel implements IGeneticModel {

	public class PossibleConnectionsIterator implements Iterator<Integer> {

		RoomData rd;
		int current_roomdata = 0;
		private int source_room;
		
		public PossibleConnectionsIterator( int source_room, RoomData rd ) {
			current_roomdata = 0;
			this.rd = rd;
			this.source_room = source_room;
		}
		
		@Override
		public boolean hasNext() {
			return current_roomdata < rd.groups.size(); 
		}

		@Override
		public Integer next() {
			GroupData gd = rd.groups.get(current_roomdata);
			int room_index = -1;
			if( gd.room1_index == source_room ) room_index = gd.room2_index;
			else room_index = gd.room1_index;
			current_roomdata++;
			return room_index;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	GroupData groupsData[];
	DoorPairEntry dpes[];
	RoomData roomsData[];
	private MapGraphModel mg;
	private List<RemovableLink> potentialRemovableLinks;
	private List<RemovableLink> removableLinks;
	RoomRemovableData removableLinksPerRoom[];
	
	public DoorBitsModel(MapGraphModel mg) {
		this.mg = mg;
		Cache(mg);
	}

	private void ComputeRemovableLinks(MapGraphModel mg) {
		potentialRemovableLinks = new ArrayList<RemovableLink>();
		floodfill_matrix = new UpperMatrix2D<Integer>(mg.NumRooms(), mg.NumRooms(), 0);
		visited_matrix = new UpperMatrix2D<Boolean>(mg.NumRooms(), mg.NumRooms(), false);
		removableLinksPerRoom = new RoomRemovableData[mg.NumRooms()];
		for( int i = 0; i < removableLinksPerRoom.length; i++ ) {
			removableLinksPerRoom[i] = new RoomRemovableData();
		}
		FloodFillStart(mg.GetSpawnRoom());
		System.out.println("FLOODFILMATRIX >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		floodfill_matrix.Debug();
		System.out.println("FLOODFILMATRIX >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		for( int i = 0; i < removableLinksPerRoom.length; i++ ) {
			System.out.println(this.removableLinksPerRoom[i].rl_list.size());			
		}

		first_time = new BitSet(500);
		first_time.set(0, 500, true);
		removableLinks = new LinkedList<RemovableLink>();
		for( int i = 0; i < potentialRemovableLinks.size(); i++ ) {
			RemovableLink rl = potentialRemovableLinks.get(i);
			if( !first_time.get(rl.outgoing_room_index) ) {
				removableLinks.add(rl);
				System.out.println("METEMOS! " + rl.outgoing_room_index);
				this.removableLinksPerRoom[rl.outgoing_room_index].rl_list.add(rl);
			} else {
				System.out.println("dejamos... " + rl.outgoing_room_index);
				first_time.clear(rl.outgoing_room_index);
			}
		}
		System.out.println("potential removable links: " + potentialRemovableLinks.size());
		System.out.println("num removable links: " + removableLinks.size());

		
	}
	
	class FFNode {
		public int incoming;
		public int outgoing;
	}
	// esta hecho en PROFUNDIAD, hacerlo en ANCHURA, para ello, no recursivo, sino abiertos / cerrados
	// quizá se pueda hacer siendo floodfill_matrix un array de las habitaciones realmente, pues guardamos cada nodo
	// por otro lado tendríamos asociados los posibles enlaces para quitar categorizados por room, y quitaríamos uno luego
	// para la matriz hay que usar upper, para referirse al mismo enlace al ir que al venir
	private void FloodFillStart(int spawnRoom) {
		//FloodFillDispatch(spawnRoom, spawnRoom);
		Queue<FFNode> open = new ArrayDeque<FFNode>();
		FFNode ffn = new FFNode();
		ffn.incoming = spawnRoom;
		ffn.outgoing = spawnRoom;
		open.add(ffn);
		while(!open.isEmpty()) {
			ffn = open.poll();
			Iterator<Integer> linksIterator = CreateLinksIterator(ffn.outgoing);
			while(linksIterator.hasNext()) {
				int possible_room_connection = linksIterator.next();
				if( possible_room_connection != ffn.incoming && !visited_matrix.GetUpper(ffn.outgoing, possible_room_connection) ) {
					visited_matrix.SetUpper(ffn.outgoing, possible_room_connection, true);
					int current = floodfill_matrix.GetUpper(ffn.outgoing, possible_room_connection);
					floodfill_matrix.SetUpper(ffn.outgoing, possible_room_connection, current + 1);
					RemovableLink rl = new RemovableLink();
					rl.incoming_room_index = ffn.outgoing;
					rl.outgoing_room_index = possible_room_connection;
					potentialRemovableLinks.add(rl);
					FFNode ffnew = new FFNode();
					ffnew.incoming = rl.incoming_room_index;
					ffnew.outgoing = rl.outgoing_room_index;
					open.add(ffnew);
				}
			}
		}
	}

	UpperMatrix2D<Integer> floodfill_matrix;
	UpperMatrix2D<Boolean> visited_matrix;
	List<RemovableLink> rl_per_room[];
	BitSet first_time;

	// construir una máscara obligatoria
	
	private void FloodFillDispatch(int incoming, int outgoing) {
		// check all links from outgoing but not incoming, and that also
		// hasn't been visited already
		Iterator<Integer> linksIterator = CreateLinksIterator(outgoing);
		while(linksIterator.hasNext()) {
			int possible_room_connection = linksIterator.next();
			if( possible_room_connection != incoming && !visited_matrix.Get(outgoing, possible_room_connection) ) {
				visited_matrix.Set(outgoing, possible_room_connection, true);
				int current = floodfill_matrix.GetUpper(outgoing, possible_room_connection);
				floodfill_matrix.SetUpper(outgoing, possible_room_connection, current + 1);
				System.out.println("start");
				FloodFillDispatch(outgoing, possible_room_connection);
				System.out.println("stop");
				RemovableLink rl = new RemovableLink();
				rl.incoming_room_index = outgoing;
				rl.outgoing_room_index = possible_room_connection;
				potentialRemovableLinks.add(rl);

			}
		}
	}

	private Iterator<Integer> CreateLinksIterator(int outgoing) {
		return new PossibleConnectionsIterator(outgoing, this.roomsData[outgoing]);
	}

	private void Cache(MapGraphModel mg) {
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
		
		ComputeRemovableLinks(mg);
	}

	@Override
	public IGeneticSolution GenerateRandomSolution() {
		DoorBitsInstance dbi = new DoorBitsInstance(removableLinks.size());
		for( int i = 0; i < removableLinks.size(); i++ ) {
			dbi.Shuffle(RNG.rng);
		}
		return dbi;
	}

	@Override
	public List<IGeneticSolution> CreateInitialPopulation(int num) {
		List<IGeneticSolution> pob = new LinkedList<IGeneticSolution>();
		for( int i = 0; i < num; i++ ) {
			IGeneticSolution sol = this.GenerateRandomSolution();
			sol.Debug();
			pob.add(this.GenerateRandomSolution());
			System.out.println();
		}
		return pob;
	}

	@Override
	public IGeneticSolution GetBest(List<IGeneticSolution> currentPopulation) {
		IGeneticSolution best_sol = null;
		float best_cost = -1f;
		for( IGeneticSolution sol : currentPopulation ) {
			if( !sol.IsCostComputed() ) sol.SetCost(this.ComputeCost(sol));
			if( sol.GetCost() > best_cost ) {
				best_cost = sol.GetCost();
				best_sol = sol;
			}
		}
		return best_sol;
	}

	IGeneticSolution best;
	
	@Override
	public void SetBest(IGeneticSolution best) {
		this.best = best;
	}

	@Override
	public float ComputeCost(IGeneticSolution sol) {
		UpperMatrix2D<Float> um = mg.CloneSCM();
		DoorBitsInstance dbi = ((DoorBitsInstance)sol);
		int current_clear_bit = dbi.GetBitSet().nextClearBit(0);
		while( current_clear_bit < removableLinks.size() ) {
			RemovableLink rl = removableLinks.get(current_clear_bit);
			um.SetUpper(rl.incoming_room_index, rl.outgoing_room_index, Float.POSITIVE_INFINITY);
			current_clear_bit = dbi.GetBitSet().nextClearBit(current_clear_bit+1);
		}
		//um.Debug();
		FloydWarshallSolver fwsolver = new FloydWarshallSolver();
		fwsolver.Solve(um);
		return fwsolver.GetDistance();
	}
	
	@Override
	public IGeneticSolution Cross(IGeneticSolution pbs1, IGeneticSolution pbs2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGeneticSolution Mutate(IGeneticSolution pbs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IGeneticSolution> Selection(List<IGeneticSolution> l) {
		// TODO Auto-generated method stub
		return null;
	}

}
