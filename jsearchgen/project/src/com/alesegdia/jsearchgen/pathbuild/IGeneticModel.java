package com.alesegdia.jsearchgen.pathbuild;

import java.util.List;

import com.alesegdia.jsearchgen.pathbuild.auxdata.MapGraph;

public interface IGeneticModel {

	public IPathBuildSolution GenerateRandomSolution();
	public IPathBuildSolution Cross(IPathBuildSolution pbs1, IPathBuildSolution pbs2);
	public IPathBuildSolution Mutate(IPathBuildSolution pbs);
	public List<IPathBuildSolution> Selection(List<IPathBuildSolution> l);
	public List<IPathBuildSolution> CreateInitialPopulation(int i);
	public IPathBuildSolution GetBest(List<IPathBuildSolution> currentPopulation);

}
