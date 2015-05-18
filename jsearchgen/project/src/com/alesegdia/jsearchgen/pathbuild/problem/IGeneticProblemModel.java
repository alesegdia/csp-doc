package com.alesegdia.jsearchgen.pathbuild.problem;

import java.util.List;

import com.alesegdia.jsearchgen.pathbuild.solution.IPathBuildSolution;
import com.alesegdia.jsearchgen.pathbuild.solution.MapGraph;

public interface IGeneticProblemModel {

	public IPathBuildSolution GenerateRandomSolution();
	public IPathBuildSolution Cross(IPathBuildSolution pbs1, IPathBuildSolution pbs2);
	public IPathBuildSolution Mutate(IPathBuildSolution pbs);
	public List<IPathBuildSolution> Selection(List<IPathBuildSolution> l);
	IPathBuildSolution Solve(MapGraph mgd, IGeneticProblemModel gpm);
	public List<IPathBuildSolution> CreateInitialPopulation(int i);
	public IPathBuildSolution GetBest(List<IPathBuildSolution> currentPopulation); 

}
