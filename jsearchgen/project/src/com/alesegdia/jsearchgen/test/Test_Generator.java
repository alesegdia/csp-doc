package com.alesegdia.jsearchgen.test;

import com.alesegdia.jsearchgen.algo.mapgen.GraphGridModel;
import com.alesegdia.jsearchgen.algo.mapgen.IRandomModel;
import com.alesegdia.jsearchgen.algo.mapgen.RandomSolver;
import com.alesegdia.jsearchgen.algo.mapgen.proxy.GraphGridAllProxy;
import com.alesegdia.jsearchgen.algo.pathopt.DoorBitsModel;
import com.alesegdia.jsearchgen.algo.pathopt.GeneticSolver;
import com.alesegdia.jsearchgen.algo.roomselect.FloydWarshallSolver;
import com.alesegdia.jsearchgen.algo.roomselect.IFloydWarshallModel;
import com.alesegdia.jsearchgen.algo.roomselect.MapGraphModel;
import com.alesegdia.jsearchgen.algo.roomselect.MapGraphInstance;
import com.alesegdia.jsearchgen.core.data.Prefabs;
import com.alesegdia.jsearchgen.core.map.render.GraphGridSolutionRenderer;
import com.alesegdia.jsearchgen.core.util.RNG;

public class Test_Generator {

	public static void main(String[] args) {
		
		// setup rng
		RNG.rng = new RNG();
		RNG.rng.setSeed(0xDEADFEED);
		
		// generate map layout
		IRandomModel ggm = new GraphGridModel(Prefabs.GenerateALot());
		RandomSolver generator = new RandomSolver(ggm);
		generator.Solve();

		// compute first and last rooms
		MapGraphModel mgm = new MapGraphModel(new GraphGridAllProxy(ggm));
		FloydWarshallSolver fwsolver = new FloydWarshallSolver();
		fwsolver.Solve(mgm.CloneSCM());

		// get minimum path matrix
		DoorBitsModel dbpm = new DoorBitsModel(mgm);
		GeneticSolver gsolver = new GeneticSolver(dbpm);
		gsolver.Solve();
		
		// debug stuff
		MapGraphInstance mgi = mgm.CreateCleanInstance();
		mgm.Debug();
		GraphGridSolutionRenderer ggsr = new GraphGridSolutionRenderer((GraphGridModel) ggm);
		ggsr.Show();		
		//mgi.Debug();

	}
}
