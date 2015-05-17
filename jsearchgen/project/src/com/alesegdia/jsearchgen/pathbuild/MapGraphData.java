package com.alesegdia.jsearchgen.pathbuild;

import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

import com.alesegdia.jsearchgen.core.room.DoorPairEntry;
import com.alesegdia.jsearchgen.core.util.UpperMatrix2D;

public class MapGraphData {
	
	public class RoomPDEIterator implements Iterator {

		private BitSet bs;
		private int currentBit;
		private MapGraph mgm;
		private int r2_index;
		private int r1_index;
		private boolean set;

		public RoomPDEIterator( MapGraph mgm, BitSet bitSet, int r1_index, int r2_index ) {
			this(mgm, bitSet, r1_index, r2_index, true);
		}
		
		public RoomPDEIterator( MapGraph mgm, BitSet bitSet, int r1_index, int r2_index, boolean set ) {
			this.bs = bitSet;
			this.mgm = mgm;
			this.r1_index = r1_index;
			this.r2_index = r2_index;
			this.set = set;
		}
		
		@Override
		public boolean hasNext() {
			return this.bs != null &&
					this.currentBit < this.mgm.possibleLinksUpperMatrix.GetUpper(r1_index, r2_index).size() &&
					this.bs.nextSetBit(this.currentBit) != -1;
		}

		@Override
		public DoorPairEntry next() {
			int status = this.bs.nextSetBit(this.currentBit);
			if( status == -1 ) System.err.println("BAD ROOM ITERATOR");
			this.currentBit = status+1;
			return this.mgm.possibleLinksUpperMatrix.GetUpper(r1_index, r2_index).get(status);
		}

		@Override
		public void remove() {
			System.err.println("remove() method not implemented");
		}

	}
	
	public RoomPDEIterator ActiveRoomLinksIterator( int r1_index, int r2_index ) {
		RoomPDEIterator it = new RoomPDEIterator(this.mgm, activeLinks.GetUpper(r1_index, r2_index), r1_index, r2_index);
		return it;
	}

	public MapGraph mgm = null;
	public UpperMatrix2D<BitSet> activeLinks = null;

	public MapGraphData( MapGraph mgm ) {
		this.mgm = mgm;
		int sz = mgm.NumRooms();
		activeLinks = new UpperMatrix2D<BitSet>(sz, sz, null);
		for( int i = 0; i < mgm.possibleLinksUpperMatrix.cols; i++ ) {
			for( int j = 0; j < mgm.possibleLinksUpperMatrix.rows; j++ ) {
				List<DoorPairEntry> dpe_list = mgm.possibleLinksUpperMatrix.GetUpper(i, j);
				if( dpe_list != null ) {
					BitSet bs = new BitSet(dpe_list.size());
					bs.set(0, bs.size(), true);
					activeLinks.SetUpper(i, j, bs);
				}
			}
		}
	}

	public void Debug() {

		System.out.println("\n===================================================");
		System.out.println("===================================================");
		System.out.println("NUMBER OF CONNECTIONS PER ROOM");
		System.out.println("===================================================");
		for( int i = 0; i < activeLinks.cols; i++ ) {
			for( int j = 0; j < activeLinks.rows; j++ ) {
				BitSet bs = activeLinks.Get(i, j);
				if( bs == null ) {
					System.out.print("·  ");
				} else {
					System.out.print(bs.length() + " ");
				}
			}
			System.out.println("");
		}
		
		System.out.println("\n===================================================");
		System.out.println("===================================================");
		System.out.println("NUMBER OF CONNECTIONS PER ROOM");
		System.out.println("===================================================");
		for( int i = 0; i < mgm.NumRooms(); i++ ) {
			for( int j = 0; j < mgm.NumRooms(); j++ ) {
				if( i != j ) {
					if( this.mgm.possibleLinksUpperMatrix.GetUpper(i, j) != null ) {
						System.out.print("Conexiones entre " + i + " y " + j + ": ");
						MapGraphData.RoomPDEIterator it = ActiveRoomLinksIterator(i, j);
						while( it.hasNext() ) {
							DoorPairEntry dpe = it.next();
							System.out.print(dpe + ", ");
						}
						System.out.println();
					}
				}
			}
		}

	}

	public MapGraphData Clone() {
		return this;
	}

}
