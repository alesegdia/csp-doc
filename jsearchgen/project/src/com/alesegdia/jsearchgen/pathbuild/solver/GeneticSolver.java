package com.alesegdia.jsearchgen.pathbuild.solver;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.pathbuild.problem.IGeneticProblemModel;
import com.alesegdia.jsearchgen.pathbuild.solution.IPathBuildSolution;
import com.alesegdia.jsearchgen.pathbuild.solution.MapGraph;

public class GeneticSolver implements IPathBuildSolver {

	private IGeneticProblemModel gpm;

	/**
	 * Resuelve el problema de construir el camino dado un layout activando y desactivando puertas
	 * con un modelo genético.
	 * @param sol solución provinente de un IMapGenSolver
	 */
	
	public GeneticSolver( IGeneticProblemModel gpm ) {
		this.gpm = gpm;
	}
	
	@Override
	public IPathBuildSolution Solve(MapGraph mg) {
		// población inicial
		List<IPathBuildSolution> currentPopulation = gpm.CreateInitialPopulation(50);
		int num_iter = 0;
		IPathBuildSolution best = null;
		while(num_iter  > 1000000) {
			
			// selección de los mejores
			List<IPathBuildSolution> selectionResult = gpm.Selection(currentPopulation);
			
			// cruce de los mejores
			List<IPathBuildSolution> newGeneration = new LinkedList<IPathBuildSolution>();
			for( int i = 0; i < selectionResult.size()-2; i+=2 ) {
				IPathBuildSolution pbs1 = selectionResult.get(i);
				IPathBuildSolution pbs2 = selectionResult.get(i+1);
				// 10 hijos por pareja
				for( int j = 0; j < 10; j++ ) {
					IPathBuildSolution child = gpm.Cross(pbs1, pbs2);
					newGeneration.add(child);
				}
			}
			
			// ¿mutación?
			// TODO
			
			// siguiente generación
			currentPopulation = newGeneration;
			currentPopulation.addAll(selectionResult);
			best = gpm.GetBest(currentPopulation);
		}
		return best;
	}

}
