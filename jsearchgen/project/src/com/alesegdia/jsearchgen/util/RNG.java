package com.alesegdia.jsearchgen.util;

import java.util.Random;

public class RNG extends Random {

	public RNG()
	{
		super();
	}
	
	public int nextInt( int min, int max )
	{
		return nextInt( (max - min) + 1 ) + min;
	}
	
}
