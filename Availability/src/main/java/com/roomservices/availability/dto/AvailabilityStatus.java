package com.roomservices.availability.dto;

import java.util.Date;
import java.util.List;

public class AvailabilityStatus {
    
    private Long roomId;
    
    public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public List<Date> getAvailableDates() {
		return availableDates;
	}

	public void setAvailableDates(List<Date> availableDates) {
		this.availableDates = availableDates;
	}

	private List<Date> availableDates;
}
