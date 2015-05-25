package com.alesegdia.jsearchgen.core.trash;

import java.util.List;

import com.alesegdia.jsearchgen.algo.roomselect.MapGraph;

public interface IGeneticModel {

	public IGeneticSolution GenerateRandomSolution();
	public IGeneticSolution Cross(IGeneticSolution pbs1, IGeneticSolution pbs2);
	public IGeneticSolution Mutate(IGeneticSolution pbs);
	public List<IGeneticSolution> Selection(List<IGeneticSolution> l);
	public List<IGeneticSolution> CreateInitialPopulation(int i);
	public IGeneticSolution GetBest(List<IGeneticSolution> currentPopulation);

}
