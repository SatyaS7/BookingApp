package com.roomservices.reservation.service;

import java.util.List;

import com.roomservices.listing.model.Room;
import com.roomservices.listing.model.RoomCategory;
import com.roomservices.reservation.dto.BookingRequestDTO;
import com.roomservices.reservation.model.Reservations;
import com.roomservices.shared.web.DateRange;

public interface ReservationService {

    public Reservations getReservation(long bookingId);    

    public List<Reservations> getReservations();

    public List<Reservations> getReservations(DateRange dateRange);

    public List<Reservations> getReservations(DateRange dateRange, RoomCategory roomCategory);
    
    public List<Reservations> getReservations(DateRange dateRange, Room room);

    public Reservations reservation(BookingRequestDTO request);

    //public Reservation getLastUpdatedBooking(DateRange dateRange);

    //public Reservation getLastUpdatedBooking(DateRange dateRange, RoomCategory roomCategory);

}
