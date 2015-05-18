package com.alesegdia.jsearchgen.pathbuild.solver;

import com.alesegdia.jsearchgen.pathbuild.solution.IPathBuildSolution;
import com.alesegdia.jsearchgen.pathbuild.solution.MapGraph;

public interface IPathBuildSolver {

	public IPathBuildSolution Solve(MapGraph mgd);



}
