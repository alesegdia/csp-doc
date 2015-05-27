package com.alesegdia.jsearchgen.algo.roomselect;

import com.alesegdia.jsearchgen.algo.common.ISolver;
import com.alesegdia.jsearchgen.core.util.UpperMatrix2D;


public class FloydWarshallSolver implements IFloydWarshallSolver {


	public FloydWarshallSolver() {
	}

	UpperMatrix2D<Float> min_distances;
	private int spawn_room;
	private int goal_room;
	private float distance;
	
	@Override
	public void Solve(UpperMatrix2D<Float> matrix) {
		min_distances = matrix;
		for( int i = 0; i < matrix.cols; i++ ) {
			min_distances.Set(i, i, 0.f);
		}
		for( int k = 0; k < matrix.cols; k++ ) {
			for( int i = 0; i < matrix.cols; i++ ) {
				for( int j = 0; j < matrix.cols; j++ ) {
					if( min_distances.GetUpper(i, j) > min_distances.GetUpper(i, k) + min_distances.GetUpper(k, j)) {
						float newvalue =              min_distances.GetUpper(i, k) + min_distances.GetUpper(k, j);
						min_distances.SetUpper(i, j, newvalue);
					}
				}
			}
		}
		//min_distances.Debug();
		int biggest_r1 = 0, biggest_r2 = 0;
		float biggest_value = Float.MIN_VALUE;
		for( int i = 0; i < matrix.cols; i++ ) {
			for( int j = 0; j < matrix.cols; j++ ) {
				float val = min_distances.GetUpper(i, j);
				if( val < 100000000f && val > biggest_value ) {
					biggest_r1 = i;
					biggest_r2 = j;
					biggest_value = val;
				}
			}
		}
		this.spawn_room = biggest_r1;
		this.goal_room = biggest_r2;
		this.distance = biggest_value;
		System.out.println("MAS LEJANAS: " + biggest_r1 + ", " + biggest_r2 + " con " + biggest_value);
	}

	@Override
	public int GetSpawnRoom() {
		return spawn_room;
	}

	@Override
	public int GetGoalRoom() {
		return goal_room;
	}

	@Override
	public float GetDistance() {
		return distance;
	}

}
