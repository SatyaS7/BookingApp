package com.roomservices.shared.web;

import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class DateRange {

    private static final long MAX_DURATION = 2592000000l; // 30 days

    @NotNull
    private Date from;
    
    public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}
	

	@NotNull
    private Date until;

	public Date getUntil() {
		return until;
	}

	public void setUntil(Date until) {
		this.until = until;
	}

    public boolean isBeforeNow() {
        return from.getTime() < toStartOfDay(new Date()).getTime();
    }
	


    public DateRange() {
        this(new Date(), new Date());
    }

    public DateRange(Date from, Date until) {
        this.from = toStartOfDay(from);
        this.until = toStartOfDay(until);
        if (until.getTime() < from.getTime()) {
            throw new IllegalArgumentException("Invalid dates. Until must be after from");
        }
        if (until.getTime() - from.getTime() > MAX_DURATION) {
            throw new IllegalArgumentException("Maximum period is 30 days");
        }
    }

    public static Date toStartOfDay(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("date cannot be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        return calendar.getTime();
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}