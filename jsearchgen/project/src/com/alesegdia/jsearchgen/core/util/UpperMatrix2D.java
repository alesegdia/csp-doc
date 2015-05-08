package com.alesegdia.jsearchgen.core.util;

import java.util.ArrayList;

public class UpperMatrix2D<T> extends Matrix2D<T> {
	
	public UpperMatrix2D( int rows, int cols, T def )
	{
		super(rows, cols, def);
	}

	
	@Override
	public T Get( int row, int col ) {
		if( row > col ) {
			return Get(col, row);
		} else {
			return Get(row, col);
		}
	}
	
	@Override
	public void Set( int row, int col, T val ) {
		if( row > col ) {
			Set(col, row, val);
		} else {
			Set(row, col, val);
		}
	}
	
}
