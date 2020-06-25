package com.roomservices.reservation.dto;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.roomservices.shared.web.DateRangeDTO;


public class BookingRequestDTO {

    @Min(1)
    private long roomId;
    @Valid @NotNull
    private DateRangeDTO dateRange;
    @Size(min = 1, max = 128)
    private String customerName;
    @Valid @NotNull
    private CreditCardDetailsDTO creditCardDetailsDto;

    public BookingRequestDTO() {}

    public BookingRequestDTO(long roomId, DateRangeDTO dateRange, String customerName,
                            CreditCardDetailsDTO creditCardDetailsDto) {
        this.roomId = roomId;
        this.dateRange = dateRange;
        this.customerName = customerName;
        this.creditCardDetailsDto = creditCardDetailsDto;
    }

    public long getRoomId() {
        return roomId;
    }

    public DateRangeDTO getDateRange() {
        return dateRange;
    }

    public String getCustomerName() {
        return customerName;
    }

    public CreditCardDetailsDTO getCreditCardDetailsDto() {
        return creditCardDetailsDto;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}