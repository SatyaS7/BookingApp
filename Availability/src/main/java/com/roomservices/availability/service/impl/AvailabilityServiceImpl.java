package com.roomservices.availability.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	// Get All Rooms
	public List<AvailabilityStatus> getAvailableRooms(String from, String until, String categoryId) {
		List<AvailabilityStatus> availabilityStatusList = new ArrayList<AvailabilityStatus>();
		// Get Room Category
		RoomCategory category = roomService.getRoomCategory(Long.valueOf(categoryId));

		List<Room> roomsByCategory = roomService.getAllRoomsWithCategory(category);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		DateRange dateRange = new DateRange();

		try {
			dateRange.setFrom(formatter.parse(from));
			dateRange.setUntil(formatter.parse(until));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Room room : roomsByCategory) {
			AvailabilityStatus availabilityStatus = new AvailabilityStatus();
			availabilityStatus.setRoomId(room.getId());
			List<Date> availableDates = checkRoomAvailableorNot(room.getId(), dateRange, category);
			availabilityStatus.setAvailableDates(availableDates);
			
			availabilityStatusList.add(availabilityStatus);
		}

		return availabilityStatusList;
	}

	private List<Date> checkRoomAvailableorNot(Long id, DateRange dateRange, RoomCategory category) {
		// TODO Auto-generated method stub
		List<Date> availableDates = new ArrayList<Date>();
		List<Date> dates = new ArrayList<Date>();

		DateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date startDate = dateRange.getFrom();
			Date endDate = dateRange.getUntil();

			long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
			long endTime = endDate.getTime(); // create your endtime here, possibly using Calendar or Date
			long curTime = startDate.getTime();
			while (curTime < endTime) {
				dates.add(new Date(curTime));
				curTime += interval;
			}

			for (int i = 0; i < dates.size(); i++) {
				Date lDate = (Date) dates.get(i);
				String ds = formatter.format(lDate);
				System.out.println(" Date is ..." + ds);
			}
		} catch (Exception e) {

		}

		for (Date date : dates) {
			Calendar calendar = Calendar.getInstance();

			calendar.setTime(dateRange.getFrom());
			calendar.add(Calendar.DATE, 1);
			Date datePlusOne = calendar.getTime();

			DateRange dateRange2 = new DateRange();
			dateRange2.setFrom(date);
			dateRange2.setUntil(datePlusOne);
			List<Reservations> reservations = reservationService.getReservations(dateRange2, category);
			if (reservations == null || reservations.isEmpty()) {
				availableDates.add(date);
			} else {
				for (Reservations reservation : reservations) {
					if (reservation.getRoomId() != id) {
						availableDates.add(date);
					}
				}

			}
		}
		return availableDates;
	}

	// public Date getLastUpdatedBooking(AvailabilityDto query);

	// Check which Rooms are booked for the dates passed

	// The remaining rooms will be the rooms that are available for the dates passed
}
