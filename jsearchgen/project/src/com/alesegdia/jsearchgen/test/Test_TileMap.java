package com.alesegdia.jsearchgen.test;

import java.util.Arrays;
import java.util.List;

import com.alesegdia.jsearchgen.core.map.MapRenderer;
import com.alesegdia.jsearchgen.core.map.TileMap;
import com.alesegdia.jsearchgen.core.room.Prefabs;

public class Test_TileMap {
	
	public static void br() {
		System.out.println();
	}

	public static void main(String[] args) {

		Integer[] map_data = new Integer[] { 1, 1, 1, 1,
											 1, 3, 3, 1,
											 1, 1, 1, 1 };

		List<Integer> data = Arrays.asList(map_data);
		TileMap mini = new TileMap(data, 3, 4);
		TileMap output = new TileMap(15,30,0);

		mini.Render();
		br();
		output.Apply(mini, 10, 5);
		output.Apply(mini, -1, -1);
		output.Apply(mini, 13, 27);
		output.Render();

		// CONSOLE DEBUG ***************************
		br();
		Prefabs.room0.GetTileMap().Render();
		br();
		mini.Render();
		br();
		System.out.println(Prefabs.room0.GetTileMap().CollideWith(mini, 0, 0));
		System.out.println(Prefabs.room0.GetTileMap().CollideWith(mini, 9, 0));
		Prefabs.room0.GetTileMap().Apply(mini, 1, 9);
		// *****************************************
		
		// GRAPHICAL DEBUG *************************
		MapRenderer mr = new MapRenderer(Prefabs.room0.GetTileMap());
		mr.Show();
		(new MapRenderer(mini)).Show();
		// *****************************************
	}

}
