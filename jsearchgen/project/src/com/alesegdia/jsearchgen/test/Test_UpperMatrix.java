package com.alesegdia.jsearchgen.test;

import com.alesegdia.jsearchgen.core.util.UpperMatrix2D;

public class Test_UpperMatrix {

	public static void main( String[] args ) {
		UpperMatrix2D<Integer> um = new UpperMatrix2D<Integer>(10,10, 0);
		for( int i = 0; i < 10; i++ ) {
			for( int j = 0; j < 10; j++ ) {
				um.Set(i, j, 1);
			}
		}
		um.Debug();
	}
}
