package com.alesegdia.jsearchgen.algo.roomselect;

import com.alesegdia.jsearchgen.core.util.UpperMatrix2D;

public interface IFloydWarshallModel {

	int NumRooms();
	UpperMatrix2D<Float> CloneSCM();
	void SetSolution(int biggest_r1, int biggest_r2, float biggest_value);

}
