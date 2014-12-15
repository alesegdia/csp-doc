package com.alesegdia.map.renderer;

import com.alesegdia.map.Tilemap;

public abstract class IMapRenderer {
	
	public IMapRenderer(Tilemap tilemap) {
		this.tilemap = tilemap;
	}

	protected Tilemap tilemap;
	
}
