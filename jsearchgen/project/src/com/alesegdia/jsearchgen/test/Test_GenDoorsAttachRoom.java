package com.alesegdia.jsearchgen.test;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.algo.mapgen.GraphGridModel;
import com.alesegdia.jsearchgen.algo.mapgen.RandomSolver;
import com.alesegdia.jsearchgen.core.data.Prefabs;
import com.alesegdia.jsearchgen.core.data.RoomInstance;
import com.alesegdia.jsearchgen.core.map.render.TileMapRenderer;
import com.alesegdia.jsearchgen.core.util.RNG;

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
		RNG.rng.setSeed(1234);
		room1.GenerateRandomDoors(RNG.rng, 3);
		room2.GenerateRandomDoors(RNG.rng, 2);
		room3.GenerateRandomDoors(RNG.rng, 2);

		//(new MapRenderer(room1.CreateTileMapWithDoors())).Show();
		//(new MapRenderer(room2.CreateTileMapWithDoors())).Show();
		//(new MapRenderer(room3.CreateTileMapWithDoors())).Show();

		/**
		 * ATTACH ROOM TO SOLUTION TEST
		 */
		GraphGridModel gs = new GraphGridModel(15,30);
		gs.AttachRoom(room1, 0, 0);
		System.out.println("room0 potential doors: " + Prefabs.room0.potentialDoors);

		List<RoomInstance> initial_rooms = new LinkedList<RoomInstance>();
		initial_rooms.add(room1);
		initial_rooms.add(room2);
		initial_rooms.add(room3);
		
		GraphGridModel sol = null;
		try {
			sol = new GraphGridModel(initial_rooms);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // en 32,32
		GraphGridModel gese = ((GraphGridModel)sol);
		int lx, ly;
		lx = 19;
		ly = 25;
		System.out.println(gese.tilemap.CollideWith(room1.prefab.GetTileMap(), ly, lx));
		gese.tilemap.Apply(room1.prefab.GetTileMap(), ly, lx);
		(new TileMapRenderer(((GraphGridModel)sol).tilemap)).Show();

		//gese.RenderCanvas();
		//prob.InsertRandomFeasibleRoom(sol);
		//sol.Render();
	}

}
