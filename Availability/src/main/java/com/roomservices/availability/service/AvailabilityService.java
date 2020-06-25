package com.roomservices.availability.service;

import java.util.List;

import com.roomservices.availability.dto.AvailabilityStatus;

public interface AvailabilityService {

	List<AvailabilityStatus> getAvailableRooms(String from, String until, String categoryId);

}