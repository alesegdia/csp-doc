package com.alesegdia.jsearchgen.test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.algorithm.Generator;
import com.alesegdia.jsearchgen.map.RoomInstance;
import com.alesegdia.jsearchgen.map.RoomPrefab;
import com.alesegdia.jsearchgen.map.TileMap;
import com.alesegdia.jsearchgen.representation.IProblemModel;
import com.alesegdia.jsearchgen.representation.ISolution;
import com.alesegdia.jsearchgen.representation.grid.GridProblemModel;
import com.alesegdia.jsearchgen.representation.grid.GridSolution;
import com.alesegdia.jsearchgen.util.RNG;

public class Test_GenDoorsAttachRoom {
	
	static List<RoomInstance> BuildRoomSet()
	{
		List<RoomInstance> rooms = new LinkedList<RoomInstance>();
		return rooms;
	}

	public static void main(String[] args) {

		/**
		 * TILEMAPS CREATION
		 */
		RNG rng = new RNG();
		Integer[] map_data = new Integer[] { 0, 0, 0, 0, 0,
							                 0, 1, 1, 1, 0,
							                 0, 1, 3, 1, 0,
							                 0, 1, 3, 1, 0,
							                 0, 1, 1, 1, 0,
				                             0, 0, 0, 0, 0 };
		List<Integer> data = Arrays.asList(map_data);
		TileMap mini = new TileMap(data, 6, 5);
		
		map_data = new Integer[] { 1, 1, 1, 1,
					               1, 2, 2, 1,
								   1, 1, 1, 1 };
		data = Arrays.asList(map_data);
		TileMap other = new TileMap(data, 3, 4);
		TileMap tm3 = TileMap.CreateTilemap(new Integer[] {
				0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 1, 1, 1, 1, 1, 0,
				0, 1, 0, 0, 0, 0, 1, 0,
				0, 1, 0, 0, 0, 0, 1, 0,
				0, 1, 0, 0, 0, 0, 1, 0,
				0, 1, 0, 1, 1, 1, 1, 0,
				0, 1, 0, 1, 0, 0, 0, 0,
				0, 1, 0, 1, 1, 1, 1, 0,
				0, 1, 0, 0, 0, 0, 1, 0,
				0, 1, 0, 0, 1, 1, 1, 0,
				0, 1, 1, 1, 1, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0
		}, 12, 8);

		/**
		 * ROOM PREFABS AND INSTANCES
		 */
		RoomPrefab prefab1 = new RoomPrefab(mini);
		RoomInstance room1 = new RoomInstance(prefab1);
		RoomPrefab prefab2 = new RoomPrefab(other);
		RoomInstance room2 = new RoomInstance(prefab2);
		RoomPrefab prefab3 = new RoomPrefab(tm3);
		RoomInstance room3 = new RoomInstance(prefab3);

		/**
		 * GENERATE RANDOM DOORS
		 */
		room1.GenerateRandomDoors(RNG.rng, 3);
		room2.GenerateRandomDoors(RNG.rng, 2);
		room3.GenerateRandomDoors(RNG.rng, 2);
		
		/**
		 * ATTACH ROOM TO SOLUTION TEST
		 */
		GridSolution gs = new GridSolution(15,30);
		gs.AttachRoom(room1, 0, 0);
		gs.Render();
		System.out.println(prefab1.potentialDoors);

		List<RoomInstance> initial_rooms = new LinkedList<RoomInstance>();
		initial_rooms.add(room1);
		initial_rooms.add(room2);
		initial_rooms.add(room3);
		
		IProblemModel prob = new GridProblemModel();
		ISolution sol = prob.CreateFirstSolution(initial_rooms);
		sol.Render();
		
		prob.InsertRandomFeasibleRoom(sol);
		sol.Render();
		
	}

}
