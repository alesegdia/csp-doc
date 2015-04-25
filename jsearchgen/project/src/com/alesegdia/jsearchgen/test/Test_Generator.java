package com.alesegdia.jsearchgen.test;

import com.alesegdia.jsearchgen.algorithm.Generator;
import com.alesegdia.jsearchgen.map.Prefabs;
import com.alesegdia.jsearchgen.representation.ISolution;
import com.alesegdia.jsearchgen.representation.grid.GridProblemModel;
import com.alesegdia.jsearchgen.util.RNG;

public class Test_Generator {

	public static void main(String[] args) {
		
		Generator generator;
		generator = new Generator(0xDEADBEEF);
		RNG.rng.setSeed(0x12345678);
		RNG.rng.setSeed(0x38483453);
		RNG.rng.setSeed(0x23847235);
		RNG.rng.setSeed(0xFEDEFACE);
		ISolution final_solution = generator.Generate(Prefabs.GenerateSampleRoomList(), new GridProblemModel());

	}
}
