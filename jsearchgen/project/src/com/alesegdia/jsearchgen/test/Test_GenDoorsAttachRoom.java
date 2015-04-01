package com.alesegdia.jsearchgen.test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.algorithm.Generator;
import com.alesegdia.jsearchgen.map.Prefabs;
import com.alesegdia.jsearchgen.map.RoomInstance;
import com.alesegdia.jsearchgen.map.RoomPrefab;
import com.alesegdia.jsearchgen.map.TileMap;
import com.alesegdia.jsearchgen.map.canvas.MapRenderer;
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

		RNG rng = new RNG();
		RoomInstance room1 = new RoomInstance(Prefabs.room0);
		RoomInstance room2 = new RoomInstance(Prefabs.room0);
		RoomInstance room3 = new RoomInstance(Prefabs.room1);

		/**
		 * GENERATE RANDOM DOORS
		 */
		room1.GenerateRandomDoors(RNG.rng, 3);
		room2.GenerateRandomDoors(RNG.rng, 2);
		room3.GenerateRandomDoors(RNG.rng, 2);
		
		MapRenderer mr = new MapRenderer(room1.CreateTileMapWithDoors());
		mr.Show();
		/**
		 * ATTACH ROOM TO SOLUTION TEST
		 */
		GridSolution gs = new GridSolution(15,30);
		gs.AttachRoom(room1, 0, 0);
		System.out.println(Prefabs.room0.potentialDoors);

		List<RoomInstance> initial_rooms = new LinkedList<RoomInstance>();
		initial_rooms.add(room1);
		initial_rooms.add(room2);
		initial_rooms.add(room3);
		
		IProblemModel prob = new GridProblemModel();
		ISolution sol = prob.CreateFirstSolution(initial_rooms);
		//prob.InsertRandomFeasibleRoom(sol);
		sol.Render();
		
	}

}
