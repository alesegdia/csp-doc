package com.alesegdia.jsearchgen.pathbuild.problem;

import java.util.List;

import com.alesegdia.jsearchgen.pathbuild.solution.IPathBuildSolution;

public interface IGeneticProblemModel {

	public IPathBuildSolution GenerateRandomSolution();
	public IPathBuildSolution Cross(IPathBuildSolution pbs1, IPathBuildSolution pbs2);
	public IPathBuildSolution Mutate(IPathBuildSolution pbs);
	public List<IPathBuildSolution> Selection(List<IPathBuildSolution> l); 

}
