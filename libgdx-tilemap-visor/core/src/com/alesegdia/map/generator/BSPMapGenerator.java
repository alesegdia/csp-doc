package com.alesegdia.map.generator;

import com.alesegdia.map.Tilemap;

public class BSPMapGenerator extends IMapGenerator {

	public class Config extends IMapGenerator.IConfig 
	{
	}
	
	@Override
	public void Populate( Tilemap tilemap, IConfig cfg ) {
		Config config = ((Config)cfg);
		
	}

}
