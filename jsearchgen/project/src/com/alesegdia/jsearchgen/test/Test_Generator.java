package com.alesegdia.jsearchgen.test;

import com.alesegdia.jsearchgen.core.map.room.Prefabs;
import com.alesegdia.jsearchgen.core.util.RNG;
import com.alesegdia.jsearchgen.generator.mapgen.RandomRoomGenerator;
import com.alesegdia.jsearchgen.generator.mapgen.model.GraphGridModel;
import com.alesegdia.jsearchgen.generator.mapgen.model.IMapGenSolution;

public class Test_Generator {

	public static void main(String[] args) {
		
		RandomRoomGenerator generator;
		generator = new RandomRoomGenerator(0xDEADBEEF);
		RNG.rng.setSeed(0x12345678);
		RNG.rng.setSeed(0x38483453);
		RNG.rng.setSeed(0x23847235);
		RNG.rng.setSeed(0xFEDEFACE);
		IMapGenSolution final_solution = generator.Generate(Prefabs.GenerateALot(), new GraphGridModel());
		final_solution.RenderCanvas();
	}
}
