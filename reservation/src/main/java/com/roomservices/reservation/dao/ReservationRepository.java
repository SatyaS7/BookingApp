package com.roomservices.reservation.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.roomservices.listing.model.Room;
import com.roomservices.reservation.model.Reservations;

@Repository
public interface ReservationRepository extends JpaRepository<Reservations, Long>{
	@Query(value = "SELECT * FROM reservations r where r._from > ?1 and r.until < ?2", nativeQuery = true)
	public List<Reservations> findByDates(String from, String until);
	
	@Query(value = "SELECT * FROM reservations r where r._from = ?1 and r.until = ?2 and r.category_id = ?3", nativeQuery = true)
	public List<Reservations> findByDateAndCategory(String from, String until, Long categoryId);
	
	@Query(value = "SELECT * FROM reservations r where r._from = ?1 and r.until = ?2 and r.room_id = ?3", nativeQuery = true)
	public List<Reservations> findByDateAndRoom(String from, String until, Long roomId);
}
