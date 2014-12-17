package com.alesegdia.tilevisor.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.alesegdia.map.Tilemap;
import com.alesegdia.tilevisor.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Tilemap tm = null;
		System.out.println("asdasdasd");
		System.out.println(arg.length);
		if( arg.length == 1 )
		{
			tm = Tilemap.LoadFromFile(arg[0]);
		}
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MyGdxGame(tm), config);
	}
}
