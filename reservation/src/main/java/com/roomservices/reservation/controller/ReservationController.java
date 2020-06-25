package com.roomservices.reservation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roomservices.listing.model.RoomCategory;
import com.roomservices.reservation.dto.BookingRequestDTO;
import com.roomservices.reservation.model.Reservations;
import com.roomservices.reservation.service.ReservationService;
import com.roomservices.shared.web.DateRange;

@RestController
@RequestMapping("/bookings")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    public ReservationController() {
    }

    @RequestMapping(method = RequestMethod.POST)
    public Reservations reserve(@Valid @RequestBody BookingRequestDTO request) {
    	return reservationService.reservation(request);
    }

    @RequestMapping(value = "/{reservationId}", method = RequestMethod.GET)
    public Reservations getReservation(@PathVariable("reservationId") long reservationId) {
        try {
            return reservationService.getReservation(reservationId);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Reservations> getReservations() {
        List<Reservations> reservations = reservationService.getReservations();
        return reservations;
    }
    
    //@RequestMapping(value = "/reservationByDateRange", method = RequestMethod.POST)
    //public List<Reservation> book(@Valid @RequestBody DateRange dateRange) {
    @RequestMapping(value = "/reservationByDateRange", method = RequestMethod.GET)
    public List<Reservations> getReservations(@Valid @RequestBody DateRange dateRange) {
        List<Reservations> reservations = reservationService.getReservations(dateRange);
        return reservations;
    }
    
    
    @RequestMapping(value = "/reservationByDateRangeAndCategory", method = RequestMethod.GET)
    public List<Reservations> getReservations(@Valid @RequestBody DateRange dateRange, @RequestBody RoomCategory category) {
        List<Reservations> reservations = reservationService.getReservations(dateRange, category);
        return reservations;
    }
}