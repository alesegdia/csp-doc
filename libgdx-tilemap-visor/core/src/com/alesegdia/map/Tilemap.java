package com.alesegdia.map;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.alesegdia.core.Matrix2D;

public class Tilemap extends Matrix2D<Integer> {
	
	public Tilemap(int rows, int cols) {
		// ATENCIÓN!! ESTÁN CAMBIADOS!! (para que sea x, y)
		super(cols, rows, 0);
	}
	
	public void Debug()
	{
		for( int i = 0; i < this.cols; i++ )
		{
			for( int j = 0; j < this.rows; j++ )
			{
				System.out.print( Get(j,Height()-i-1) );
			}
			System.out.println();
		}
	}

	public int Width() {
		return this.cols;
	}

	public int Height() {
		return this.rows;
	}
	
	public static Tilemap LoadFromFile( String path )
	{
		Tilemap tm = null;
		try {
			Scanner scanner = new Scanner( new BufferedReader( new FileReader( path )));
			int width = scanner.nextInt();
			int height = scanner.nextInt();
			tm = new Tilemap( width, height );
			int i = 0;
			while( scanner.hasNext() )
			{
				tm.Set(i, scanner.nextInt());
				i++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return tm;
	}

	private void Set(int i, int val) {
		this.data.set(i, val);
	}

}
