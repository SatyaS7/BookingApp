package com.roomservices.reservation.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.roomservices.listing.model.Room;
import com.roomservices.listing.model.RoomCategory;
import com.roomservices.listing.service.IRoomService;
import com.roomservices.reservation.dao.ReservationRepository;
import com.roomservices.reservation.dto.BookingRequestDTO;
import com.roomservices.reservation.model.Reservations;
import com.roomservices.reservation.service.ReservationService;
import com.roomservices.shared.web.DateRange;

@Component
@Transactional
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;
	
    @Autowired
    private IRoomService listingService;
    
    @Transactional
    public Reservations reservation(BookingRequestDTO request) {
        Reservations reservation = new Reservations();
        Room room = listingService.getRoomById(request.getRoomId());
        reservation.setRoomId(room.getId());
        reservation.setCategoryId(room.getRoomCategory().getId()); //Getting Category from Room
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
	        Date fromDate = formatter.parse(request.getDateRange().getFrom());
	        Date untilDate = formatter.parse(request.getDateRange().getUntil());
	        
	        reservation.setFrom(fromDate);
	        reservation.setUntil(untilDate);
        } 
        catch (ParseException e) {
        	e.printStackTrace();
        }
        
        reservation.setCustomerName(request.getCustomerName());
        
        //credit card details not added
        reservationRepository.save(reservation);
        return reservation;
    }

    @Transactional(readOnly = true)
    public Reservations getReservation(long reservationId) {
        if (reservationId <= 0) {
            throw new IllegalArgumentException("Invalid booking ID. It must be greater than zero");
        }
        Reservations reservation = reservationRepository.getOne(reservationId);
        if (reservation == null) {
            throw new IllegalArgumentException("No booking with ID " + reservationId);
        }
        return reservation;
    }
    
    public List<Reservations> getReservations() {
        List<Reservations> reservations = reservationRepository.findAll();
        return reservations;
    }

    public List<Reservations> getReservations(DateRange dateRange) {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	
    	String fromDate = null;
    	String untilDate = null;

        fromDate = formatter.format(dateRange.getFrom());
		untilDate = formatter.format(dateRange.getUntil());
		
        return reservationRepository.findByDates(fromDate, untilDate);
    }

    public List<Reservations> getReservations(DateRange dateRange, RoomCategory category) {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	
    	String fromDate = null;
    	String untilDate = null;

        fromDate = formatter.format(dateRange.getFrom());
		untilDate = formatter.format(dateRange.getUntil());
		
    	return reservationRepository.findByDateAndCategory(fromDate, untilDate, category.getId());
    }

    /*public Reservation getLastUpdatedBooking(DateRange dateRange) {
        List<Reservation> bookings = sessionFactory.getCurrentSession()
                .createCriteria(Reservation.class)
                .add(Restrictions.ge("from", dateRange.getFrom()))
                .add(Restrictions.le("until", dateRange.getUntil()))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .addOrder(Order.desc("updatedAt"))
                .list();
        return bookings.isEmpty() ? null : bookings.get(0);
    }*/

    /*public Reservation getLastUpdatedBooking(DateRange dateRange, RoomCategory category) {
        List<Reservation> bookings = sessionFactory.getCurrentSession()
                .createCriteria(Reservation.class)
                .add(Restrictions.eq("categoryId", category.getId()))
                .add(Restrictions.ge("from", dateRange.getFrom()))
                .add(Restrictions.le("until", dateRange.getUntil()))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .setMaxResults(1)
                .addOrder(Order.desc("updatedAt"))
                .list();
        return bookings.isEmpty() ? null : bookings.get(0);
    }*/
}
