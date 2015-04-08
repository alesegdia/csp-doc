package com.alesegdia.jsearchgen.map;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.util.RNG;

public final class Prefabs {

	public static RoomPrefab room0 = new RoomPrefab(new TileMap("rooms/room0.json"));
	public static RoomPrefab room1 = new RoomPrefab(new TileMap("rooms/room1.json"));
	
	public static List<RoomInstance> GenerateSampleRoomList()
	{
		RoomInstance room1 = new RoomInstance(Prefabs.room1);
		RoomInstance room2 = new RoomInstance(Prefabs.room0);
		RoomInstance room3 = new RoomInstance(Prefabs.room1);
		room1.GenerateRandomDoors(RNG.rng, 10);
		room2.GenerateRandomDoors(RNG.rng, 10);
		room3.GenerateRandomDoors(RNG.rng, 10);
		room1.name = "room1";
		room2.name = "room2";
		room3.name = "room3";
		
		//room1.RenderCanvas();
		//room2.RenderCanvas();
		//room3.RenderCanvas();
		List<RoomInstance> initial_rooms = new LinkedList<RoomInstance>();
		initial_rooms.add(room1);
		initial_rooms.add(room2);
		initial_rooms.add(room3);
		return initial_rooms;
	}
}
