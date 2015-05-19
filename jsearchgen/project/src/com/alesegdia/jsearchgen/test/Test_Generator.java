package com.alesegdia.jsearchgen.test;

import com.alesegdia.jsearchgen.core.map.render.GraphGridSolutionRenderer;
import com.alesegdia.jsearchgen.core.room.Prefabs;
import com.alesegdia.jsearchgen.core.util.RNG;
import com.alesegdia.jsearchgen.mapgen.representation.GraphGridRandom;
import com.alesegdia.jsearchgen.mapgen.representation.GraphGridSolution;
import com.alesegdia.jsearchgen.mapgen.representation.IMapGenSolution;
import com.alesegdia.jsearchgen.mapgen.representation.IRandomModel;
import com.alesegdia.jsearchgen.mapgen.representation.RandomSolver;
import com.alesegdia.jsearchgen.pathbuild.DoorBitsGenetic;
import com.alesegdia.jsearchgen.pathbuild.auxdata.MapGraph;
import com.alesegdia.jsearchgen.pathbuild.auxdata.MapGraphData;
import com.alesegdia.jsearchgen.proxy.GraphGridAllProxy;

public class Test_Generator {

	public static void main(String[] args) {
		RandomSolver generator;
		generator = new RandomSolver(0xDEADBEEF);
		//RNG.rng.setSeed(0x12345678);
		//RNG.rng.setSeed(0x38483453);
		//RNG.rng.setSeed(0x23847235);
		//RNG.rng.setSeed(0xFEDEFACE);
		IRandomModel problem_model = new GraphGridRandom();
		IMapGenSolution final_solution = generator.Generate(Prefabs.GenerateALot(), problem_model);

		MapGraph mgm = new MapGraph();
		mgm.BuildFromGraphGridSolution(new GraphGridAllProxy((GraphGridSolution) final_solution));
		mgm.Debug();
		GraphGridSolutionRenderer ggsr = new GraphGridSolutionRenderer(((GraphGridSolution)final_solution));
		ggsr.Show();
		MapGraphData mgi = mgm.CreateCleanInstance();
		mgi.Debug();
		
		DoorBitsGenetic dbpm = new DoorBitsGenetic(mgm);
	}
}
