package com.alesegdia.jsearchgen.algo.roomselect;

import com.alesegdia.jsearchgen.core.util.UpperMatrix2D;

public interface IFloydWarshallSolver {

	public int GetSpawnRoom();
	public int GetGoalRoom();
	public float GetDistance();
	void Solve(UpperMatrix2D<Float> matrix);
	
}
