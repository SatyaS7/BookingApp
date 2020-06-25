package com.roomservices.listing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roomservices.listing.model.Room;
import com.roomservices.listing.model.RoomCategory;
import com.roomservices.listing.service.IRoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController {
	
	@Autowired
	private IRoomService roomService;
	
	@RequestMapping(value = "addRoom", method = RequestMethod.POST)
    public Room addRoom(@RequestBody Room room) {
        RoomCategory category = roomService.getRoomCategory(room.getRoomCategory().getId());
        room.setRoomCategory(category);
        return roomService.addRoom(room);
    }
    
	@RequestMapping(value = "addRoomCategory", method = RequestMethod.POST)
    public RoomCategory addRoomCategory(@RequestBody RoomCategory roomCategory) {
        return roomService.addRoomCategory(roomCategory);
    }
    
    @RequestMapping(value = "getAllRoomCategories", method = RequestMethod.GET)
    public List<RoomCategory> getAllRoomCategories() {
        return roomService.getAllRoomCategories();
    }
    
    @RequestMapping(value = "getAllRooms", method = RequestMethod.GET)
    public List<Room> getAllRooms() {
    	return roomService.getAllRooms();
    }
    
    @RequestMapping(value = "getRoomById/{roomId}", method = RequestMethod.GET)
    public Room getRoomById(@PathVariable ("roomId") long roomId) {
    	return roomService.getRoomById(roomId);
    }
    
    @RequestMapping(value = "deleteRoomById/{roomId}", method = RequestMethod.GET)
    public String deleteRoomById(@PathVariable ("roomId") long roomId) {
    	String response = roomService.deleteRoomById(roomId);
        return response;
    }

	@RequestMapping(value = "updateRoom/{roomId}", method = RequestMethod.PUT)
    public Room updateRoom(@RequestBody Room room, @PathVariable ("roomId") long roomId) {
		return roomService.updateRoom(room, roomId);
    }
}
