package com.alesegdia.jsearchgen.map;

import com.alesegdia.jsearchgen.util.Matrix2D;

public class TileMap extends Matrix2D<TileType> {

	public TileMap(int rows, int cols, TileType def) {
		super(rows, cols, def);
		// TODO Auto-generated constructor stub
	}
	
	public TileMap(TileMap prefab)
	{
		super(prefab);
	}
	
	int CapCoord( int coord, int max )
	{
		return Math.max(0, Math.min(coord, max));
	}

	public void Apply(TileMap other, int top, int left)
	{
		int this_top = CapCoord(top, this.rows);
		int this_left = CapCoord(left, this.cols);
		int this_bot = CapCoord(top + other.rows, this.rows);
		int this_right = CapCoord(left + other.cols, this.cols);
		
		int that_top = 0;
		int that_left = 0;
		int that_bot = CapCoord(other.rows, this.rows);
		int that_right = CapCoord(other.rows, this.rows);

		int this_row, that_row, this_col, that_col;
		for( this_row = this_top, that_row = that_top;
			 this_row < this_bot && that_row < that_bot;
			 this_row++, that_row++ )
		{
			for( this_col = this_left, that_col = that_left;
				 this_col < this_right && that_col < that_right;
				 this_col++, that_row++ )
			{
				TileType src = other.Get(that_row,  that_col);
				if( src != TileType.FREE )
				{
					this.Set(this_row, this_col, src);
				}
			}
		}
	}
	
}
