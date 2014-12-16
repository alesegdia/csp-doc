package com.alesegdia.map.generator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.alesegdia.core.RNG;
import com.alesegdia.map.Tilemap;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Segment;

public class BSPMapGenerator extends IMapGenerator {

	public class Config extends IMapGenerator.IConfig 
	{
		public int num_iterations = 5;
		public int param = 10;
		public long seed;
	}
	
	List<BoundingBox> bb_list = new ArrayList<BoundingBox>();
	RNG rng = new RNG();
	
	public void Divide( BoundingBox bb, boolean horizontal, int iteration )
	{
		if( IsValid(bb) )
		{
			BoundingBox bb1 = new BoundingBox();
			BoundingBox bb2 = new BoundingBox();
	
			if( horizontal )
			{
				int cut = rng.nextInt(((int)bb.min.y), ((int)bb.max.y));
				bb1.min.x = bb2.min.x = bb.min.x;
				bb1.max.x = bb2.max.x = bb.max.x;
				
				bb1.min.y = bb.min.y;
				bb2.min.y = bb1.max.y;
	
				bb1.max.y = bb.min.y + cut;
				bb2.max.y = bb.max.y;
			}
			else
			{
				int cut = rng.nextInt(((int)bb.min.x), ((int)bb.max.x));
				bb1.min.y = bb2.min.y = bb.min.y;
				bb1.max.y = bb2.max.y = bb.max.y;
				
				bb1.min.x = bb.min.x;
				bb2.min.x = bb1.max.x;
				
				bb1.max.x = bb.min.x + cut;
				bb2.max.x = bb.max.x;
			}
			
			if( iteration == 0 )
			{
				bb_list.add(bb1);
				bb_list.add(bb2);
			}
			else
			{
				Divide( bb1, !horizontal, iteration - 1 );
				Divide( bb2, !horizontal, iteration - 1 );
			}
		}
	}
	
	private boolean IsValid(BoundingBox bb) {
		return bb.max.x > bb.min.x || bb.max.y > bb.min.y;
	}

	int Area( BoundingBox bb )
	{
		if( !IsValid(bb) ) return -1;
		return (int) ((bb.max.x - bb.min.x) * (bb.max.y - bb.min.y));
	}
	
	@Override
	public void Populate( Tilemap tilemap, IConfig cfg ) {
		Config config = ((Config)cfg);
		rng.setSeed(config.seed);
		Divide( new BoundingBox(new Vector3(0,0,0), new Vector3(tilemap.Width()-1, tilemap.Height()-1, 0)), true, config.num_iterations);
		RasterBoxes( tilemap );
	}
	
	public void RasterBoxes( Tilemap tm )
	{
		for( BoundingBox bb : bb_list )
		{
			// up seg
			for( int i = (int) bb.min.x; i < bb.max.x; i++ ) tm.Set(i, (int) bb.min.y, tm.Get(i, (int) bb.min.y) + 1);
			// down seg
			for( int i = (int) bb.min.x; i < bb.max.x; i++ ) tm.Set(i, (int) bb.max.y, tm.Get(i, (int) bb.max.y) + 1);
			// left seg
			for( int i = (int) bb.min.y; i < bb.max.y; i++ ) tm.Set((int) bb.min.x, i, tm.Get((int) bb.min.x, i) + 1);
			// right seg
			for( int i = (int) bb.min.y; i < bb.max.y; i++ ) tm.Set((int) bb.max.x, i, tm.Get((int) bb.max.x, i) + 1);
		}
	}

}
