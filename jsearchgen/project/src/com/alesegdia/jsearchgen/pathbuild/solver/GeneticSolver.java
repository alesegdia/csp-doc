package com.alesegdia.jsearchgen.pathbuild.solver;

import java.util.List;

import com.alesegdia.jsearchgen.mapgen.solution.IMapGenSolution;
import com.alesegdia.jsearchgen.pathbuild.problem.IGeneticProblemModel;
import com.alesegdia.jsearchgen.pathbuild.solution.DoorBitsSolution;
import com.alesegdia.jsearchgen.pathbuild.solution.IPathBuildSolution;
import com.alesegdia.jsearchgen.pathbuild.solution.MapGraphData;

public class GeneticSolver implements IPathBuildSolver, IGeneticProblemModel {

	/**
	 * Resuelve el problema de construir el camino activando y desactivando puertas
	 * con un modelo genético.
	 * @param sol solución provinente de un IMapGenSolver
	 */
	@Override
	public IPathBuildSolution Solve(MapGraphData mgd) {
		DoorBitsSolution dbs = new DoorBitsSolution();
		return dbs;
	}

	@Override
	public IPathBuildSolution GenerateRandomSolution() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPathBuildSolution Cross(IPathBuildSolution pbs1,
			IPathBuildSolution pbs2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPathBuildSolution Mutate(IPathBuildSolution pbs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IPathBuildSolution> Selection(List<IPathBuildSolution> l) {
		// TODO Auto-generated method stub
		return null;
	}

}
