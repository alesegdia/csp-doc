package com.alesegdia.jsearchgen.map;

import java.util.List;

import com.alesegdia.jsearchgen.util.Matrix2D;

public class TileMap extends Matrix2D<Integer> {

	public TileMap(int rows, int cols, int def) {
		super(rows, cols, def);
		// TODO Auto-generated constructor stub
	}
	
	public TileMap(TileMap prefab) {
		super(prefab);
	}
	
	public TileMap(List<Integer> data, int cols, int rows) {
		super(data, cols, rows);
	}

	int CapCoord( int coord, int max )
	{
		int ret = coord;
		if( coord < 0 ) ret = 0;
		if( coord > max ) ret = max;
		return ret;
	}

	public void Apply(TileMap other, int top, int left)
	{
		int this_top = CapCoord(top, this.rows);
		int this_left = CapCoord(left, this.cols);
		int this_bot = CapCoord(top + other.rows, this.rows);
		int this_right = CapCoord(left + other.cols, this.cols);
		
		int that_top = 0;
		int that_left = 0;
		if( top < 0 ) that_top = -top;
		int that_bot = this_bot - this_top + that_top;
		if( left < 0 ) that_left = -left;
		int that_right = this_right - this_left + that_left;

		int this_row, that_row, this_col, that_col;
		for( this_row = this_top, that_row = that_top;
			 this_row < this_bot && that_row < that_bot;
			 this_row++, that_row++ )
		{
			for( this_col = this_left, that_col = that_left;
				 this_col < this_right && that_col < that_right;
				 this_col++, that_col++ )
			{
				int src = other.Get(that_row,  that_col);
				if( src != TileType.FREE )
				{
					this.Set(this_row, this_col, src);
				}
			}
		}
	}
	
	public void Render()
	{
		for( int i = 0; i < this.rows; i++ )
		{
			for( int j = 0; j < this.cols; j++ )
			{
				System.out.print( this.Get(i, j) );
			}
			System.out.println();
		}
	}
	
}
