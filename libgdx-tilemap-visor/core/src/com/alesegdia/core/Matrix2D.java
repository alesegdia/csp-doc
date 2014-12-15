package com.alesegdia.core;

import java.util.ArrayList;

public class Matrix2D <T> {
	
	protected ArrayList<T> data = null;
	protected int cols, rows;
	
	public Matrix2D( int rows, int cols, T def )
	{
		data = new ArrayList<T>();
		for( int i = 0; i < rows * cols; i++ )
		{
			data.add(def);
		}
		this.rows = rows;
		this.cols = cols;
	}

	public T Get( int row, int col )
	{
		return data.get( row * this.cols + col );
	}
	
	public void Set( int row, int col, T val )
	{
		data.set( row * this.cols + col, val );
	}
	
	public void Fill( T val )
	{
		for( int i = 0; i < data.size(); i++ )
		{
			data.set(i, val);
		}
	}

}
