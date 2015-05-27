package com.alesegdia.jsearchgen.algo.pathopt;

public interface IGeneticSolution {

	void Debug();
	boolean IsCostComputed();
	void SetCost(float value);
	float GetCost();

}
