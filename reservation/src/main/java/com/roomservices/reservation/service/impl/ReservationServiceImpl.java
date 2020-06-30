package com.roomservices.reservation.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate fromDate = LocalDate.parse(request.getDateRange().getFrom(), formatter);
		LocalDate untilDate = LocalDate.parse(request.getDateRange().getUntil(), formatter);
		
		reservation.setFrom(fromDate);
		reservation.setUntil(untilDate);
        
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
		System.out.println("In get reservation" + fromDate);
		
		//System.out.println(fromDate);
		
    	return reservationRepository.findByDateAndCategory(fromDate, category.getId());
    }
    
    public List<Reservations> getReservations(DateRange dateRange, Room room) {	
    	//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	
    	String fromDate = null;
    	String untilDate = null;
    	
        fromDate = dateRange.getFrom().format(formatter);
		untilDate = dateRange.getUntil().format(formatter);
		
    	return reservationRepository.findByDateAndRoom(fromDate, room.getId());
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
