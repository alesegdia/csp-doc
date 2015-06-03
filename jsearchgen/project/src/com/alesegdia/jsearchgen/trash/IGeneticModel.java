package com.alesegdia.jsearchgen.trash;

import java.util.List;

import com.alesegdia.jsearchgen.algo.roomselect.MapGraphModel;

public interface IGeneticModel {

	public IGeneticSolution GenerateRandomSolution();
	public IGeneticSolution Cross(IGeneticSolution pbs1, IGeneticSolution pbs2);
	public IGeneticSolution Mutate(IGeneticSolution pbs);
	public List<IGeneticSolution> Selection(List<IGeneticSolution> l);
	public List<IGeneticSolution> CreateInitialPopulation(int i);
	public IGeneticSolution GetBest(List<IGeneticSolution> currentPopulation);
	public void SetBest(IGeneticSolution best);
	public float ComputeCost(IGeneticSolution sol);

}
