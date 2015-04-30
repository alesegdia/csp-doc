package com.alesegdia.jsearchgen.test;

import com.alesegdia.jsearchgen.algorithm.RandomGenerator;
import com.alesegdia.jsearchgen.map.Prefabs;
import com.alesegdia.jsearchgen.representation.ISolution;
import com.alesegdia.jsearchgen.representation.grid.GridProblemModel;
import com.alesegdia.jsearchgen.util.RNG;

public class Test_Generator {

	public static void main(String[] args) {
		
		RandomGenerator generator;
		generator = new RandomGenerator(0xDEADBEEF);
		RNG.rng.setSeed(0x12345678);
		RNG.rng.setSeed(0x38483453);
		RNG.rng.setSeed(0x23847235);
		RNG.rng.setSeed(0xFEDEFACE);
		ISolution final_solution = generator.Generate(Prefabs.GenerateALot(), new GridProblemModel());
		final_solution.RenderCanvas();
	}
}
