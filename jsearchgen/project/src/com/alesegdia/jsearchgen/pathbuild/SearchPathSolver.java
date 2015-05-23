package com.alesegdia.jsearchgen.pathbuild;

import com.alesegdia.jsearchgen.core.structure.MapGraph;

public class SearchPathSolver extends APathBuildSolver<ISearchSolution, ISearchModel> {

	private ISearchModel spm;

	public SearchPathSolver(ISearchModel spm) {
		this.spm = spm;
	}
	
	@Override
	public ISearchSolution Solve(MapGraph mgd, ISearchModel problem_model) {
		IGeneticSolution pbs = spm.GetFirstSolution();
		return null;
	}

}
