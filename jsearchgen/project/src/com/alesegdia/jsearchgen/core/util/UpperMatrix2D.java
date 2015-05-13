package com.alesegdia.jsearchgen.core.util;

public class UpperMatrix2D<T> extends Matrix2D<T> {
	
	public UpperMatrix2D( int rows, int cols, T def )
	{
		super(rows, cols, def);
	}

	public T GetUpper( int row, int col ) {
		if( row > col ) {
			return super.Get(col, row);
		} else {
			return super.Get(row, col);
		}
	}
	
	public void SetUpper( int row, int col, T val ) {
		if( row > col ) {
			super.Set(col, row, val);
		} else {
			super.Set(row, col, val);
		}
	}

	public void Debug() {
		for( int i = 0; i < cols; i++ ) {
			for( int j = 0; j < rows; j++ ) {
				System.out.print(super.Get(i,j));
			}
			System.out.println();
		}
	}

}
