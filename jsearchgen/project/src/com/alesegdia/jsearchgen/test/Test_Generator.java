package com.alesegdia.jsearchgen.test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.alesegdia.jsearchgen.map.RoomInstance;
import com.alesegdia.jsearchgen.map.RoomPrefab;
import com.alesegdia.jsearchgen.map.TileMap;
import com.alesegdia.jsearchgen.representation.grid.GridProblemModel;
import com.alesegdia.jsearchgen.representation.grid.GridSolution;
import com.alesegdia.jsearchgen.util.RNG;

public class Test_Generator {
	
	public static void main(String[] args) {
		
		RNG rng = new RNG();
		Integer[] map_data = new Integer[] { 0, 0, 0, 0, 0,
							                 0, 1, 1, 1, 0,
							                 0, 1, 0, 1, 0,
							                 0, 1, 0, 1, 0,
							                 0, 1, 1, 1, 0,
				                             0, 0, 0, 0, 0 };
		List<Integer> data = Arrays.asList(map_data);
		TileMap mini = new TileMap(data, 6, 5);
		
		map_data = new Integer[] { 1, 1, 1, 1,
					               1, 0, 0, 1,
								   1, 1, 1, 1 };
		data = Arrays.asList(map_data);
		TileMap other = new TileMap(data, 3, 4);

		RoomPrefab prefab1 = new RoomPrefab(mini);
		RoomInstance room1 = new RoomInstance(prefab1);
		RoomPrefab prefab2 = new RoomPrefab(other);
		RoomInstance room2 = new RoomInstance(prefab2);
		
		GridProblemModel prob = new GridProblemModel();
		GridSolution gs = new GridSolution(15,30);
		
		room1.GenerateRandomDoors(RNG.rng, 3);
		
		gs.AttachRoom(room1, 0, 0);
		gs.Render();
		System.out.println(prefab1.potentialDoors);
		
		
	}

}
