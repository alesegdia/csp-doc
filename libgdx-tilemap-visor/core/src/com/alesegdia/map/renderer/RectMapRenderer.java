package com.alesegdia.map.renderer;

import com.alesegdia.map.Tilemap;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class RectMapRenderer extends IMapRenderer {

	private int tileWidth = 10;
	private int tileHeight = 10;
	
	public RectMapRenderer(Tilemap tilemap) {
		super(tilemap);
	}

	private float normalize( int tint )
	{
		return ((tint) / 255.f);
	}
	
	public void Render( ShapeRenderer shaperenderer, float offsetx, float offsety )
	{
		Color c = new Color();
		for( int i = 0; i < tilemap.Width(); i++ )
		{
			for( int j = 0; j < tilemap.Height(); j++ )
			{
				int val = tilemap.Get(i, j);
				c.set( normalize((val * 8) % 255), normalize((val * 32) % 255), normalize((val * 128) % 255), 1.0f );
				shaperenderer.rect( offsetx + i * tileWidth, offsety + j * tileHeight,  tileWidth, tileHeight, c, c, c, c);
			}
		}
	}

	public float Height() {
		return tileHeight * tilemap.Height();
	}
	
	public float Width() {
		return tileWidth * tilemap.Width();
	}
	
	
	
}
