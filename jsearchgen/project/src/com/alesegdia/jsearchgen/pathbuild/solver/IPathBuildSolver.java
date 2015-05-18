package com.alesegdia.jsearchgen.pathbuild.solver;

import java.util.List;

import com.alesegdia.jsearchgen.mapgen.solution.IMapGenSolution;
import com.alesegdia.jsearchgen.pathbuild.solution.IPathBuildSolution;
import com.alesegdia.jsearchgen.pathbuild.solution.MapGraph;
import com.alesegdia.jsearchgen.pathbuild.solution.MapGraphData;

public interface IPathBuildSolver {

	public IPathBuildSolution Solve(MapGraph mgd);



}
