package com.alesegdia.jsearchgen.map;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.alesegdia.jsearchgen.util.FileUtil;
import com.alesegdia.jsearchgen.util.Matrix2D;

public class TileMap extends Matrix2D<Integer> {

	public static TileMap CreateTilemap(Integer[] map_data, int rows, int cols )
	{
		List<Integer> data = Arrays.asList(map_data);
		TileMap tm = new TileMap(data, rows, cols);
		return tm;
	}

	
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
	
	public TileMap(String path_to_json_file)
	{
		super();
		List<Integer> data = new ArrayList<Integer>();
		try {
			JSONObject obj = new JSONObject( FileUtil.readFileContents(path_to_json_file, Charset.defaultCharset() ));
			JSONArray size = obj.getJSONArray("size");
			int cols = (int) size.get(0);
			int rows = (int) size.get(1);
			JSONArray ext_data = obj.getJSONArray("data");
			for( int i = 0; i < ext_data.length(); i++ )
			{
				int val = ext_data.getInt(i);
				data.add(val);
			}
			super.CreateFromData(data, rows, cols);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean CollideWith(TileMap other, int top, int left)
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
		boolean collide = false;
		
		for( this_row = this_top, that_row = that_top;
			 this_row < this_bot && that_row < that_bot && !collide;
			 this_row++, that_row++ )
		{
			for( this_col = this_left, that_col = that_left;
				 this_col < this_right && that_col < that_right && !collide;
				 this_col++, that_col++ )
			{
				int src = other.Get(that_row,  that_col);
				int dst = this.Get(this_row, this_col);
				if( src != TileType.FREE && dst != TileType.FREE )
				{
					collide = true;
				}
			}
		}
		return collide;
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
