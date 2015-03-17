package com.alesegdia.jsearchgen.test;

import java.util.Arrays;
import java.util.List;

import com.alesegdia.jsearchgen.map.TileMap;

public class Test_TileMap {
	
	public static void br() {
		System.out.println();
	}

	public static void main(String[] args) {

		Integer[] map_data = new Integer[] { 2, 1, 1, 1,
											 1, 1, 1, 0,
											 1, 0, 1, 1 };
		List<Integer> data = Arrays.asList(map_data);
		TileMap mini = new TileMap(data, 3, 4);
		TileMap output = new TileMap(15,30,0);

		mini.Render();
		br();
		output.Apply(mini, 10, 5);
		output.Apply(mini, -1, -1);
		output.Apply(mini, 13, 27);
		output.Render();
	}

}
