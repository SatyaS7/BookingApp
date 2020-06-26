package com.roomservices.listing.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roomservices.listing.dao.RoomCategoryRepository;
import com.roomservices.listing.dao.RoomRepository;
import com.roomservices.listing.model.Room;
import com.roomservices.listing.model.RoomCategory;
import com.roomservices.listing.service.IRoomService;

@Service
@Transactional
public class RoomServiceImpl implements IRoomService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoomServiceImpl.class);

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private RoomCategoryRepository roomCategoryRepository;

	public RoomServiceImpl() {
	}

	public Room addRoom(Room room) {
		if (room.getId() != null && room.getId() > 0) {
			throw new IllegalArgumentException("room already exists");
		}
		roomRepository.save(room);
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Added new room {}", room);
		}
		return room;
	}

	public RoomCategory addRoomCategory(RoomCategory roomCategory) {
		roomCategoryRepository.save(roomCategory);
		return roomCategory;
	}

	public RoomCategory getRoomCategory(long categoryId) {
		if (categoryId <= 0) {
			throw new IllegalArgumentException("Invalid category ID. It must be greater than zero");
		}
		RoomCategory category = roomCategoryRepository.getOne(categoryId);
		if (category == null) {
			throw new IllegalArgumentException("No room category with ID " + categoryId);
		}
		return category;
	}

	public Room getRoomById(long roomId) {
		Room room = roomRepository.getOne(roomId);
		if (room == null) {
			throw new IllegalArgumentException("No room with ID " + roomId);
		}
		return room;
	}

	public String deleteRoomById(long roomId) {
		String response = null;
		Room room = getRoomById(roomId);
		if (room != null) {
			roomRepository.delete(room);
			response = "success";
		} else {
			response = "Room Id not found";
		}
		return response;

	}

	public Room updateRoom(Room room, long roomId) {
		Room room1 = getRoomById(roomId);
		if (room1 != null) {
			if (room.getName() != null) {
				room1.setName(room.getName());
			}

			if (room.getDescription() != null) {
				room1.setDescription(room.getDescription());
			}

			if (room.getRoomCategory().getId() != null) {
				RoomCategory category = roomCategoryRepository.getOne(room.getRoomCategory().getId());
				room1.setRoomCategory(category);
			}
			roomRepository.save(room1);
		}
		return room1;
	}

	public List<RoomCategory> getAllRoomCategories() {
		return roomCategoryRepository.findAll();
	}

	public List<Room> getAllRooms() {
		return roomRepository.findAll();
	}

	@Transactional(readOnly = true)
	public List<Room> getAllRoomsWithCategory(Long categoryId) {
		return roomRepository.findByRoomCategoryId(categoryId);
	}

}
