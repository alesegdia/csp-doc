package com.alesegdia.jsearchgen.map;

import java.util.ArrayList;
import java.util.List;

import com.alesegdia.jsearchgen.util.Vec2;

public class RoomPrefab {
	
	class PotentialDoorEntry {
		public Vec2 localPosition = new Vec2(0,0);
		public Door.Type doorType;
	}

	public TileMap map;
	public List<PotentialDoorEntry> potentialDoors = new ArrayList<PotentialDoorEntry>();
	
	public RoomPrefab(TileMap map)
	{
		SetTileMap(map);
	}
	
	public void SetTileMap(TileMap map)
	{
		this.map = map;
		ComputePotentialDoorPositions();
	}

	/**
	 * Computes the potential door positions, this is, a potential
	 * door position must agree with:
	 * 		- if vertical:
	 * 			- (pos.x, pos.y+1) and (pos.x, pos.y-1) must be WALL
	 * 		- if horizontal:
	 * 			- (pos.x+1, pos.y) and (pos.x-1, pos.y) must be WALL
	 * 
	 */
	private void ComputePotentialDoorPositions() {
		for( int r = 1; r < this.map.rows-1; r++ )
		{
			for( int c = 1; c < this.map.cols-1; c++ )
			{
				boolean check_horizontal =
						map.Get(r, c) == TileType.WALL &&
						map.Get(r, c+1) == TileType.WALL &&
						map.Get(r, c-1) == TileType.WALL;
				
				boolean check_vertical =
						map.Get(r, c) == TileType.WALL &&
						map.Get(r+1, c) == TileType.WALL &&
						map.Get(r-1, c) == TileType.WALL;
				
				// VERTICAL AND HORIZONTAL ARE EXCLUSIVE. A DOOR CAN'T BE OF BOTH TYPES
				if( check_horizontal )
				{
					PotentialDoorEntry pde = new PotentialDoorEntry();
					pde.doorType = Door.Type.HORIZONTAL;
					pde.localPosition.x = c;
					pde.localPosition.y = r;
					potentialDoors.add(pde);
				}
				else if( check_vertical )
				{
					PotentialDoorEntry pde = new PotentialDoorEntry();
					pde.doorType = Door.Type.VERTICAL;
					pde.localPosition.x = c;
					pde.localPosition.y = r;
					potentialDoors.add(pde);
				}
			}
		}
	}

}
