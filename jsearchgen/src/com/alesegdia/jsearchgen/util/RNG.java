package com.alesegdia.jsearchgen.util;

import java.util.Random;

public class RNG {

	Random rng = new Random();
	
	public void setSeed( long seed )
	{
		rng.setSeed(seed);
	}
	
	public int nextInt()
	{
		return rng.nextInt();
	}
	
	public int nextInt( int top )
	{
		return rng.nextInt(top);
	}
	
	public int nextInt( int bot, int top )
	{
		return rng.nextInt( top - bot ) + bot;
	}
	
}
