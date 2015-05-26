package com.alesegdia.jsearchgen.algo.roomselect;

import com.alesegdia.jsearchgen.algo.common.ISolver;
import com.alesegdia.jsearchgen.algo.common.IResolutiveSolver;
import com.alesegdia.jsearchgen.core.util.UpperMatrix2D;


public class FloydWarshallSolver implements IResolutiveSolver<IFloydWarshallSolution, IFloydWarshallModel> {

	private IFloydWarshallModel problem_model;

	public FloydWarshallSolver(IFloydWarshallModel problem_model) {
		this.problem_model = problem_model;
	}

	UpperMatrix2D<Float> min_distances;
	
	@Override
	public void Solve() {
		min_distances = this.problem_model.CloneSCM();
		for( int i = 0; i < problem_model.NumRooms(); i++ ) {
			min_distances.Set(i, i, 0.f);
		}
		for( int k = 0; k < problem_model.NumRooms(); k++ ) {
			for( int i = 0; i < problem_model.NumRooms(); i++ ) {
				for( int j = 0; j < problem_model.NumRooms(); j++ ) {
					if( min_distances.GetUpper(i, j) > min_distances.GetUpper(i, k) + min_distances.GetUpper(k, j)) {
						float newvalue =              min_distances.GetUpper(i, k) + min_distances.GetUpper(k, j);
						min_distances.SetUpper(i, j, newvalue);
					}
				}
			}
		}
		min_distances.Debug();
		int biggest_r1 = 0, biggest_r2 = 0;
		float biggest_value = Float.MIN_VALUE;
		for( int i = 0; i < problem_model.NumRooms(); i++ ) {
			for( int j = 0; j < problem_model.NumRooms(); j++ ) {
				float val = min_distances.GetUpper(i, j);
				if( val > biggest_value ) {
					biggest_r1 = i;
					biggest_r2 = j;
					biggest_value = val;
				}
			}
		}
		System.out.println("MAS LEJANAS: " + biggest_r1 + ", " + biggest_r2 + " con " + biggest_value);
		RoomSelectSolution rss = new RoomSelectSolution();
		problem_model.SetSolution(biggest_r1, biggest_r2, biggest_value);
	}

}
