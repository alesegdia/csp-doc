package com.alesegdia.jsearchgen.algo.pathopt;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.algo.common.ISolver;
import com.alesegdia.jsearchgen.algo.roomselect.MapGraphModel;

public class GeneticSolver implements ISolver {

	private IGeneticModel gpm;

	/**
	 * Resuelve el problema de construir el camino dado un layout activando y desactivando puertas
	 * con un modelo genético.
	 * @param sol solución provinente de un IMapGenSolver
	 */
	
	public GeneticSolver( IGeneticModel gpm ) {
		this.gpm = gpm;
	}
	
	@Override
	public void Solve() {
		// población inicial
		List<IGeneticSolution> currentPopulation = gpm.CreateInitialPopulation(50);
		
		int num_iter = 0;
		IGeneticSolution best = null;
		best = gpm.GetBest(currentPopulation);
		for( IGeneticSolution gs : currentPopulation ) {
			gs.Debug();
			System.out.println(" cost: " + gs.GetCost());
		}
		/*
		while(num_iter  > 1000000) {
			
			// selección de los mejores
			List<IGeneticSolution> selectionResult = gpm.Selection(currentPopulation);
			
			// cruce de los mejores
			List<IGeneticSolution> newGeneration = new LinkedList<IGeneticSolution>();
			for( int i = 0; i < selectionResult.size()-2; i+=2 ) {
				IGeneticSolution pbs1 = selectionResult.get(i);
				IGeneticSolution pbs2 = selectionResult.get(i+1);
				// 10 hijos por pareja
				for( int j = 0; j < 10; j++ ) {
					IGeneticSolution child = gpm.Cross(pbs1, pbs2);
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
		gpm.SetBest(best);
		*/
	}

}
