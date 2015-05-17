package com.alesegdia.jsearchgen.test;

import com.alesegdia.jsearchgen.core.map.render.GraphGridSolutionRenderer;
import com.alesegdia.jsearchgen.core.room.Prefabs;
import com.alesegdia.jsearchgen.core.util.RNG;
import com.alesegdia.jsearchgen.mapgen.GraphGridProblemModel;
import com.alesegdia.jsearchgen.mapgen.GraphGridSolution;
import com.alesegdia.jsearchgen.mapgen.IMapGenProblemModel;
import com.alesegdia.jsearchgen.mapgen.IMapGenSolution;
import com.alesegdia.jsearchgen.mapgen.RandomRoomSolver;
import com.alesegdia.jsearchgen.pathbuild.MapGraphInstance;
import com.alesegdia.jsearchgen.pathbuild.MapGraphModel;
import com.alesegdia.jsearchgen.proxy.GraphGridAllProxy;

public class Test_Generator {

	public static void main(String[] args) {
		RandomRoomSolver generator;
		generator = new RandomRoomSolver(0xDEADBEEF);
		RNG.rng.setSeed(0x12345678);
		RNG.rng.setSeed(0x38483453);
		RNG.rng.setSeed(0x23847235);
		RNG.rng.setSeed(0xFEDEFACE);
		IMapGenProblemModel problem_model = new GraphGridProblemModel();
		IMapGenSolution final_solution = generator.Generate(Prefabs.GenerateALot(), problem_model);

		MapGraphModel mgm = new MapGraphModel();
		mgm.BuildFromGraphGridSolution(new GraphGridAllProxy((GraphGridSolution) final_solution));
		mgm.Debug();
		GraphGridSolutionRenderer ggsr = new GraphGridSolutionRenderer(((GraphGridSolution)final_solution));
		ggsr.Show();
		MapGraphInstance mgi = mgm.CreateCleanInstance();
		mgi.Debug();
	}
}
