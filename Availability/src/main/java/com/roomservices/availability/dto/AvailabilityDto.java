package com.roomservices.availability.dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.roomservices.shared.web.DateRange;

public class AvailabilityDto {
    private final DateRange dateRange;
    private final long categoryId;

    public AvailabilityDto(DateRange dateRange, long categoryId) {
        if (dateRange == null) {
            throw new IllegalArgumentException("dateRange is mandatory");
        }
        this.dateRange = dateRange;
        this.categoryId = categoryId;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public long getCategoryId() {
        return categoryId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}