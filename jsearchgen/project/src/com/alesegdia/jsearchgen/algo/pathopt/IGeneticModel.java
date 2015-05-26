package com.alesegdia.jsearchgen.algo.pathopt;

import java.util.List;

import com.alesegdia.jsearchgen.algo.roomselect.MapGraphModel;

public interface IGeneticModel {

	public IPathOptModel GenerateRandomSolution();
	public IPathOptModel Cross(IPathOptModel pbs1, IPathOptModel pbs2);
	public IPathOptModel Mutate(IPathOptModel pbs);
	public List<IPathOptModel> Selection(List<IPathOptModel> l);
	public List<IPathOptModel> CreateInitialPopulation(int i);
	public IPathOptModel GetBest(List<IPathOptModel> currentPopulation);
	public void SetBest(IPathOptModel best);

}
