package com.roomservices.reservation.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity//(name = "reservations")
//@javax.persistence.Table(name = "reservations")
@Table(name = "reservations")
public class Reservations {
	
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "category_id", nullable = false)
    private Long categoryId;
    @Column(name = "room_id", nullable = false)
    private Long roomId;
    @Column(name = "_from", nullable = false) // "from" is an SQL keyword
    private LocalDate from;
    @Column(name = "until", nullable = false)
    private LocalDate until;
    private String customerName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Reservations() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }


    public Long getId() {
        return id;
    }
    void setId(Long id) {
        this.id = id;
    }


    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }


    public Long getRoomId() {
        return roomId;
    }
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    //@Temporal(TemporalType.DATE)
    
    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getUntil() {
        return until;
    }

    public void setUntil(LocalDate until) {
        this.until = until;
    }

    @Column(name = "customer_name", nullable = false, length = 128)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Column(name = "created_at", nullable = false)
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_at", nullable = false)
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
