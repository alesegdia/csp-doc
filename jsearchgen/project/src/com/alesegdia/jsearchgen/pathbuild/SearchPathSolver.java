package com.alesegdia.jsearchgen.pathbuild;

import com.alesegdia.jsearchgen.pathbuild.auxdata.MapGraph;

public class SearchPathSolver implements IPathBuildSolver {

	private ISearchModel spm;

	public SearchPathSolver(ISearchModel spm) {
		this.spm = spm;
	}
	
	@Override
	public IPathBuildSolution Solve(MapGraph mgd) {
		IPathBuildSolution pbs = spm.GetFirstSolution();
		
		return null;
	}

}
