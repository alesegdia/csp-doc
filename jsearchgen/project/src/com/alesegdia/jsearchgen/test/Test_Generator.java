package com.alesegdia.jsearchgen.test;

import com.alesegdia.jsearchgen.algo.mapgen.GraphGridModel;
import com.alesegdia.jsearchgen.algo.mapgen.IMapGenModel;
import com.alesegdia.jsearchgen.algo.mapgen.IRandomModel;
import com.alesegdia.jsearchgen.algo.mapgen.RandomSolver;
import com.alesegdia.jsearchgen.algo.pathopt.DoorBitsModel;
import com.alesegdia.jsearchgen.algo.roomselect.FloydWarshallSolver;
import com.alesegdia.jsearchgen.algo.roomselect.IFloydWarshallModel;
import com.alesegdia.jsearchgen.algo.roomselect.IRoomSelectSolution;
import com.alesegdia.jsearchgen.algo.roomselect.MapGraphModel;
import com.alesegdia.jsearchgen.algo.roomselect.MapGraphData;
import com.alesegdia.jsearchgen.algo.roomselect.RoomSelectSolution;
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
		IRandomModel ggm = new GraphGridModel(Prefabs.GenerateALot());
		RandomSolver generator = new RandomSolver(ggm);
		generator.Solve();

		// compute first and last rooms
		IFloydWarshallModel mgm = new MapGraphModel(new GraphGridAllProxy(ggm));
		FloydWarshallSolver fwsolver = new FloydWarshallSolver(mgm);
		fwsolver.Solve();

		// get minimum path matrix
		DoorBitsModel dbpm = new DoorBitsModel((MapGraphModel) mgm);
		
		// debug stufff
		MapGraphData mgi = ((MapGraphModel) mgm).CreateCleanInstance();
		((MapGraphModel) mgm).Debug();
		GraphGridSolutionRenderer ggsr = new GraphGridSolutionRenderer((GraphGridModel) ggm);
		ggsr.Show();		
		mgi.Debug();

	}
}
