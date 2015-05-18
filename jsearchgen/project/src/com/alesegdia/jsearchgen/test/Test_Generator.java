package com.alesegdia.jsearchgen.test;

import com.alesegdia.jsearchgen.core.map.render.GraphGridSolutionRenderer;
import com.alesegdia.jsearchgen.core.room.Prefabs;
import com.alesegdia.jsearchgen.core.util.RNG;
import com.alesegdia.jsearchgen.mapgen.problem.GraphGridProblemModel;
import com.alesegdia.jsearchgen.mapgen.problem.IRandomProblemModel;
import com.alesegdia.jsearchgen.mapgen.solution.GraphGridSolution;
import com.alesegdia.jsearchgen.mapgen.solution.IMapGenSolution;
import com.alesegdia.jsearchgen.mapgen.solver.RandomRoomSolver;
import com.alesegdia.jsearchgen.pathbuild.problem.DoorBitsProblemModel;
import com.alesegdia.jsearchgen.pathbuild.solution.MapGraph;
import com.alesegdia.jsearchgen.pathbuild.solution.MapGraphData;
import com.alesegdia.jsearchgen.proxy.GraphGridAllProxy;

public class Test_Generator {

	public static void main(String[] args) {
		RandomRoomSolver generator;
		generator = new RandomRoomSolver(0xDEADBEEF);
		RNG.rng.setSeed(0x12345678);
		RNG.rng.setSeed(0x38483453);
		RNG.rng.setSeed(0x23847235);
		RNG.rng.setSeed(0xFEDEFACE);
		IRandomProblemModel problem_model = new GraphGridProblemModel();
		IMapGenSolution final_solution = generator.Generate(Prefabs.GenerateALot(), problem_model);

		MapGraph mgm = new MapGraph();
		mgm.BuildFromGraphGridSolution(new GraphGridAllProxy((GraphGridSolution) final_solution));
		mgm.Debug();
		GraphGridSolutionRenderer ggsr = new GraphGridSolutionRenderer(((GraphGridSolution)final_solution));
		ggsr.Show();
		MapGraphData mgi = mgm.CreateCleanInstance();
		mgi.Debug();
		
		DoorBitsProblemModel dbpm = new DoorBitsProblemModel(mgm);
	}
}
