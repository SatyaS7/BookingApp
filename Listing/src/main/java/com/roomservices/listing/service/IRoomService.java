package com.roomservices.listing.service;

import java.util.List;

import com.roomservices.listing.model.*;

public interface IRoomService {

	    public RoomCategory getRoomCategory(long categoryId);

	    public Room addRoom(Room room);

		public RoomCategory addRoomCategory(RoomCategory roomCategory);

		public List<RoomCategory> getAllRoomCategories();

		public List<Room> getAllRooms();

		public Room getRoomById(long roomId);

		public String deleteRoomById(long roomId);

		public Room updateRoom(Room room, long roomId);

	    public List<Room> getAllRoomsWithCategory(RoomCategory category);
}
