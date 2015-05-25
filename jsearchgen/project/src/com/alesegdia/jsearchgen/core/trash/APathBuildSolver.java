package com.alesegdia.jsearchgen.core.trash;

import com.alesegdia.jsearchgen.algo.roomselect.MapGraph;

public abstract class APathBuildSolver<SolutionClass, ModelClass> {

	public SolutionClass Solve(MapGraph mgd, ModelClass problem_model) throws Exception {
		throw new Exception("NOT IMPLEMENTED");
	}
	
}
