package com.alesegdia.jsearchgen.test;

import com.alesegdia.jsearchgen.algorithm.Generator;
import com.alesegdia.jsearchgen.map.Prefabs;
import com.alesegdia.jsearchgen.representation.ISolution;
import com.alesegdia.jsearchgen.representation.grid.GridProblemModel;

public class Test_Generator {

	public static void main(String[] args) {
		
		Generator generator = new Generator(0xFACEFEED);
		ISolution final_solution = generator.Generate(Prefabs.GenerateSampleRoomList(), new GridProblemModel());
		final_solution.RenderCanvas();
	}
}
