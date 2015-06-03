package com.alesegdia.jsearchgen.trash;

import java.util.BitSet;

import com.alesegdia.jsearchgen.core.util.RNG;

public class DoorBitsInstance implements IGeneticSolution {

	private BitSet bs;
	private int numBits;
	private float cost = -1;

	public DoorBitsInstance( int num_bits ) {
		this.numBits = num_bits;
		bs = new BitSet(num_bits);
	}

	public void Shuffle(RNG rng) {
		if( rng.nextFloat() > 0.5 ) {
			bs.set(rng.nextInt(numBits));
		} else {
			bs.clear(rng.nextInt(numBits));
		}
	}
	
	public void Debug() {
		System.out.print(bs);
	}

	public BitSet GetBitSet() {
		return bs;
	}
	
	@Override
	public boolean IsCostComputed() {
		return cost != -1;
	}

	@Override
	public void SetCost(float value) {
		this.cost = value;
	}

	@Override
	public float GetCost() {
		return this.cost;
	}
	
}
