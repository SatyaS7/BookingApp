package com.roomservices.reservation.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

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
    private Date from;
    private Date until;
    private String customerName;
    private Date createdAt;
    private Date updatedAt;

    public Reservations() {
        createdAt = new Date();
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

    @Temporal(TemporalType.DATE)
    @Column(name = "_from", nullable = false) // "from" is an SQL keyword
    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "until", nullable = false)
    public Date getUntil() {
        return until;
    }

    public void setUntil(Date until) {
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
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_at", nullable = false)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
