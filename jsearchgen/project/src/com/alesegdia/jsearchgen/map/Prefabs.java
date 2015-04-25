package com.alesegdia.jsearchgen.map;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.jsearchgen.map.Door.Type;
import com.alesegdia.jsearchgen.util.RNG;

public final class Prefabs {

	public static RoomPrefab room0 = new RoomPrefab(new TileMap("rooms/room0.json"));
	public static RoomPrefab room1 = new RoomPrefab(new TileMap("rooms/room1.json"));
	
	public static List<RoomInstance> GenerateSampleRoomList()
	{
		List<RoomInstance> initial_rooms = new LinkedList<RoomInstance>();
		RoomInstance room1 = new RoomInstance(Prefabs.room1);
		RoomInstance room2 = new RoomInstance(Prefabs.room0);
		RoomInstance room3 = new RoomInstance(Prefabs.room1);
		room1.GenerateRandomDoors(RNG.rng, 10);
		room2.GenerateRandomDoors(RNG.rng, 10);
		room3.GenerateRandomDoors(RNG.rng, 10);
		room1.name = "room1";
		room2.name = "room2";
		room3.name = "room3";
		initial_rooms.add(room1);
		initial_rooms.add(room2);
		initial_rooms.add(room3);
		return initial_rooms;
	}
	
	public static List<RoomInstance> GenerateFixedDoorRoomList()
	{
		List<RoomInstance> initial_rooms = new LinkedList<RoomInstance>();
		RoomInstance room1 = new RoomInstance(Prefabs.room0);
		RoomInstance room2 = new RoomInstance(Prefabs.room1);
		room1.AddDoor(12, 14, Type.HORIZONTAL);
		room2.AddDoor(3, 1, Type.HORIZONTAL);
		room1.name = "room1";
		room2.name = "room2";
		initial_rooms.add(room1);
		initial_rooms.add(room2);
		return initial_rooms;
	}
	
}
