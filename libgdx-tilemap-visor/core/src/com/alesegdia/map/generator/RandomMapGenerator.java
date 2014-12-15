package com.alesegdia.map.generator;

import java.util.Random;

import com.alesegdia.map.Tilemap;

public class RandomMapGenerator extends IMapGenerator {

	public class Config extends IMapGenerator.IConfig
	{
		public float thres;
		public int seed;
	}
	
	Random random = new Random();

	@Override
	public void Populate( Tilemap tilemap, IConfig cfg ) {
		Config config = ((Config)cfg);
		for( int i = 0; i < tilemap.Width(); i++ )
		{
			for( int j = 0; j < tilemap.Height(); j++ )
			{
				tilemap.Set(i, j, ( random.nextFloat() > config.thres ? 0 : 1 ));
			}
		}
	}
	
}
