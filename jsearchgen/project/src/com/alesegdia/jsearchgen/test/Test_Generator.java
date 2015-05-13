package com.alesegdia.jsearchgen.test;

import com.alesegdia.jsearchgen.core.room.Prefabs;
import com.alesegdia.jsearchgen.core.util.RNG;
import com.alesegdia.jsearchgen.mapgen.GraphGridResolver;
import com.alesegdia.jsearchgen.mapgen.GraphGridSolution;
import com.alesegdia.jsearchgen.mapgen.IMapGenSolution;
import com.alesegdia.jsearchgen.mapgen.RandomRoomGenerator;
import com.alesegdia.jsearchgen.pathbuild.MapGraphModel;

public class Test_Generator {

	public static void main(String[] args) {
		
		RandomRoomGenerator generator;
		generator = new RandomRoomGenerator(0xDEADBEEF);
		RNG.rng.setSeed(0x12345678);
		RNG.rng.setSeed(0x38483453);
		RNG.rng.setSeed(0x23847235);
		RNG.rng.setSeed(0xFEDEFACE);
		IMapGenSolution final_solution = generator.Generate(Prefabs.GenerateALot(), new GraphGridResolver());
		final_solution.RenderCanvas();

		MapGraphModel mgm = new MapGraphModel();
		mgm.BuildFromGraphGridSolution(((GraphGridSolution)final_solution));
		mgm.Debug();
	}
}
