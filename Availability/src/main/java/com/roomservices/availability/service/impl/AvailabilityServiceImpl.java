package com.roomservices.availability.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roomservices.availability.dto.AvailabilityStatus;
import com.roomservices.availability.service.AvailabilityService;
import com.roomservices.listing.model.Room;
import com.roomservices.listing.model.RoomCategory;
import com.roomservices.listing.service.IRoomService;
import com.roomservices.reservation.model.Reservations;
import com.roomservices.reservation.service.ReservationService;
import com.roomservices.shared.web.DateRange;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

	@Autowired
	private IRoomService roomService;

	@Autowired
	private ReservationService reservationService;
	
	public List<AvailabilityStatus> getAvailableRooms(LocalDate startDate, LocalDate endDate, Long roomCategoryId) {
		List<LocalDate> availableDates;
		List<Room> roomsByCategory = roomService.getAllRoomsWithCategory(roomCategoryId);
		List<AvailabilityStatus> availabilityStatusList = new ArrayList<AvailabilityStatus>();
		
		for(Room room: roomsByCategory)
		{			
			availableDates = new ArrayList<LocalDate>();
			LocalDate newStartDate = startDate;
			
			while(newStartDate.isBefore(endDate)) {				
				DateRange dateRange = new DateRange();
				dateRange.setFrom(newStartDate);
				dateRange.setUntil(newStartDate.plusDays(1));
			
				List<Reservations> reservations = reservationService.getReservations(dateRange, room);
				if (reservations == null || reservations.isEmpty()) {
					System.out.println(reservations.toString());
					availableDates.add(newStartDate);
				}				
				newStartDate = newStartDate.plusDays(1);
			}
			
			if(availableDates != null || !availableDates.isEmpty())
			{
				AvailabilityStatus availabilityStatus = new AvailabilityStatus();
				availabilityStatus.setRoom(room);
				availabilityStatus.setAvailableDates(availableDates);
				availabilityStatusList.add(availabilityStatus);
			}
		}
		return availabilityStatusList;
	}
	// public Date getLastUpdatedBooking(AvailabilityDto query);

	// Check which Rooms are booked for the dates passed

	// The remaining rooms will be the rooms that are available for the dates passed
}
