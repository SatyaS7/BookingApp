package com.roomservices.listing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.roomservices.listing.model.RoomCategory;

@Repository
public interface RoomCategoryRepository extends JpaRepository<RoomCategory, Long> {
}
