package com.alesegdia.jsearchgen.core.util;

public class UpperMatrix2D<T> extends Matrix2D<T> {
	
	public UpperMatrix2D( int rows, int cols, T def )
	{
		super(rows, cols, def);
	}

	@Override
	public T Get( int row, int col ) {
		if( row > col ) {
			return super.Get(col, row);
		} else {
			return super.Get(row, col);
		}
	}
	
	@Override
	public void Set( int row, int col, T val ) {
		if( row > col ) {
			super.Set(col, row, val);
		} else {
			super.Set(row, col, val);
		}
	}
	
}
