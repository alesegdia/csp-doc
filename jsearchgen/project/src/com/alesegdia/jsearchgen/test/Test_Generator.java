package com.alesegdia.jsearchgen.test;

import java.util.BitSet;

import com.alesegdia.jsearchgen.core.map.GraphGridSolutionRenderer;
import com.alesegdia.jsearchgen.core.room.DoorPairEntry;
import com.alesegdia.jsearchgen.core.room.Prefabs;
import com.alesegdia.jsearchgen.core.util.RNG;
import com.alesegdia.jsearchgen.mapgen.GraphGridSolution;
import com.alesegdia.jsearchgen.mapgen.IMapGenSolution;
import com.alesegdia.jsearchgen.mapgen.RandomRoomResolver;
import com.alesegdia.jsearchgen.pathbuild.MapGraphInstance;
import com.alesegdia.jsearchgen.pathbuild.MapGraphModel;
import com.alesegdia.jsearchgen.proxy.GraphGridAllProxy;
import com.alesegdia.jsearchgen.proxy.GraphGridSimpleProxy;
import com.alesegdia.jsearchgen.proxy.IMapGenPathBuildProxy;

public class Test_Generator {

	public static void main(String[] args) {
		RandomRoomResolver generator;
		generator = new RandomRoomResolver(0xDEADBEEF);
		RNG.rng.setSeed(0x12345678);
		RNG.rng.setSeed(0x38483453);
		RNG.rng.setSeed(0x23847235);
		RNG.rng.setSeed(0xFEDEFACE);
		IMapGenSolution final_solution = generator.Generate(Prefabs.GenerateALot());

		MapGraphModel mgm = new MapGraphModel();
		//mgm.BuildFromGraphGridSolution(new GraphGridSimpleProxy((GraphGridSolution) final_solution));
		mgm.BuildFromGraphGridSolution(new GraphGridAllProxy((GraphGridSolution) final_solution));
		mgm.Debug();
		GraphGridSolutionRenderer ggsr = new GraphGridSolutionRenderer(((GraphGridSolution)final_solution));
		ggsr.Show();
		MapGraphInstance mgi = mgm.CreateCleanInstance();
		mgi.Debug();
	}
}
