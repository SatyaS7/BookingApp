package com.roomservices.reservation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.roomservices.reservation.model.Reservations;

@Repository
public interface ReservationRepository extends JpaRepository<Reservations, Long>{
	//@Query("select r from Reservation r where r.from > ?1 and r.until < ?2")
	@Query(value = "SELECT * FROM reservations where r.from > ?1 and r.until < ?2", nativeQuery = true)
	public List<Reservations> findByDates(String from, String until);
	
	//@Query("select r from Reservation r where r.from > ?1 and r.until < ?2 and r.categoryId = ?3")
	@Query(value = "SELECT * FROM reservations where r.from > ?1 and r.until < ?2 and r.categoryId = ?3", nativeQuery = true)
	public List<Reservations> findByDateAndCategory(String from, String until, Long categoryId);
}
