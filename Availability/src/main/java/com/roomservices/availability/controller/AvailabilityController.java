package com.roomservices.availability.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.WebRequest;

import com.roomservices.availability.dto.AvailabilityStatus;
import com.roomservices.availability.service.AvailabilityService;

@RestController
@RequestMapping("/availability/api")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    public AvailabilityController() { }

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AvailabilityStatus> getAvailability(@RequestParam("from") String from,
                                       @RequestParam("until") String until,
                                       @RequestParam(value = "roomCategoryId", required = false) String roomCategoryId) {
    	
    	return availabilityService.getAvailableRooms(from, until, roomCategoryId);

    }

}
