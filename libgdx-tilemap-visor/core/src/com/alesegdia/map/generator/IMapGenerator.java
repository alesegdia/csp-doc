package com.alesegdia.map.generator;

import com.alesegdia.map.Tilemap;

public abstract class IMapGenerator {
	
	public abstract class IConfig { public int width; public int height; }

	public abstract void Populate( Tilemap tilemap, IConfig cfg );
	
	public Tilemap Generate( IConfig cfg )
	{
		Tilemap tm = new Tilemap( cfg.width, cfg.height );
		Populate(tm, cfg);
		return tm;
	}

}
