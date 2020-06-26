package com.roomservices.availability.service;

import java.time.LocalDate;
import java.util.List;

import com.roomservices.availability.dto.AvailabilityStatus;
import com.roomservices.listing.model.RoomCategory;

public interface AvailabilityService {

	List<AvailabilityStatus> getAvailableRooms(LocalDate from, LocalDate until, Long roomCategoryId);

}