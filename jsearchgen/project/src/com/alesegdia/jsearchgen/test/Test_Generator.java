package com.alesegdia.jsearchgen.test;

import com.alesegdia.jsearchgen.algo.mapgen.GraphGridSolution;
import com.alesegdia.jsearchgen.algo.mapgen.IMapGenSolution;
import com.alesegdia.jsearchgen.algo.mapgen.RandomSolver;
import com.alesegdia.jsearchgen.algo.mapgen.MapGenSolutionFactory;
import com.alesegdia.jsearchgen.algo.roomselect.DoorBitsModel;
import com.alesegdia.jsearchgen.algo.roomselect.FloydWarshallSolver;
import com.alesegdia.jsearchgen.algo.roomselect.IRoomSelectSolution;
import com.alesegdia.jsearchgen.algo.roomselect.MapGraph;
import com.alesegdia.jsearchgen.algo.roomselect.MapGraphData;
import com.alesegdia.jsearchgen.core.data.Prefabs;
import com.alesegdia.jsearchgen.core.map.render.GraphGridSolutionRenderer;
import com.alesegdia.jsearchgen.core.util.RNG;
import com.alesegdia.jsearchgen.proxy.GraphGridAllProxy;

public class Test_Generator {

	public static void main(String[] args) {
		
		// setup rng
		RNG.rng = new RNG();
		RNG.rng.setSeed(0xDEADFEED);
		
		// generate map layout
		RandomSolver generator = new RandomSolver(MapGenSolutionFactory.CreateFirstSolution(Prefabs.GenerateALot()));
		IMapGenSolution final_solution = generator.Solve();

		// build graph from previous solution using all doors
		MapGraph mgm = new MapGraph();
		mgm.BuildFromGraphGridSolution(new GraphGridAllProxy((GraphGridSolution) final_solution));
		mgm.Debug();
		GraphGridSolutionRenderer ggsr = new GraphGridSolutionRenderer(((GraphGridSolution)final_solution));
		ggsr.Show();		
		MapGraphData mgi = mgm.CreateCleanInstance();
		mgi.Debug();
		
		// get minimum path matrix
		DoorBitsModel dbpm = new DoorBitsModel(mgm);
		FloydWarshallSolver fwsolver = new FloydWarshallSolver(dbpm);
		IRoomSelectSolution rss = fwsolver.Solve();
	}
}
