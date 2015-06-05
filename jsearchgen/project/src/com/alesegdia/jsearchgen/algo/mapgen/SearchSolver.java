package com.alesegdia.jsearchgen.algo.mapgen;

import java.util.List;

import com.alesegdia.jsearchgen.algo.common.ISolver;
import com.alesegdia.jsearchgen.core.data.RoomInstance;

public class SearchSolver implements ISolver {

	List<RoomInstance> rooms = null;
	
	public void Setup(List<RoomInstance> rooms) {
		this.rooms = rooms;
	}
	
	@Override
	public void Solve() throws Exception {
		if( this.rooms == null ) {
			throw new Exception("room list is null!");
		}
		GraphGridModel sm = null;
		
		while(!sm.GetRemainingRooms().isEmpty()) {
			//List<ISearchSolution> sols = sm.GetPossibleSolutions();
			//ISearchSolution best = GetBest(sm, sols);
			//sm.ApplySolutionToModel(best);
			//ISerializedSearchSolution sss;
		}
	}

	/*
	private ISearchSolution GetBest(ISearchModel sm, List<ISearchSolution> sols) {
		for( ISearchSolution sol : sols ) sm.ComputeFintess(sol);
		return null;
	}
	*/

}
