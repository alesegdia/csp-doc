package com.alesegdia.jsearchgen.test;

import com.alesegdia.jsearchgen.generator.RandomGenerator;
import com.alesegdia.jsearchgen.model.GraphGridProblemModel;
import com.alesegdia.jsearchgen.model.ISolution;
import com.alesegdia.jsearchgen.room.Prefabs;
import com.alesegdia.jsearchgen.util.RNG;

public class Test_Generator {

	public static void main(String[] args) {
		
		RandomGenerator generator;
		generator = new RandomGenerator(0xDEADBEEF);
		RNG.rng.setSeed(0x12345678);
		RNG.rng.setSeed(0x38483453);
		RNG.rng.setSeed(0x23847235);
		RNG.rng.setSeed(0xFEDEFACE);
		ISolution final_solution = generator.Generate(Prefabs.GenerateALot(), new GraphGridProblemModel());
		final_solution.RenderCanvas();
	}
}
