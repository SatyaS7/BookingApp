package com.roomservices.availability.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.roomservices.listing.model.Room;

public class AvailabilityStatus {
    
    private Room room;
	private List<LocalDate> availableDates;
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<LocalDate> getAvailableDates() {
		if(availableDates == null)
		{
			availableDates = new ArrayList<LocalDate>();
		}
		return availableDates;
	}

	public void setAvailableDates(List<LocalDate> availableDates) {
		this.availableDates = availableDates;
	}

}
