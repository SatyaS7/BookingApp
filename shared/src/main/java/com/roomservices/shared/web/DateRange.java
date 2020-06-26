package com.roomservices.shared.web;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class DateRange {

    private static final long MAX_DURATION = 2592000000l; // 30 days

    @NotNull
    private LocalDate from;
    
    public @NotNull LocalDate getFrom() {
		return from;
	}

	public void setFrom(@NotNull LocalDate from) {
		this.from = from;
	}
	

	@NotNull
    private LocalDate until;

	public @NotNull LocalDate getUntil() {
		return until;
	}

	public void setUntil(@NotNull LocalDate until) {
		this.until = until;
	}

    public boolean isBeforeNow() {
        return false;//from.getTime() < toStartOfDay(new Date()).getTime();
    }
	

    public DateRange() {
    }
    
	/*
	 * public static Calendar toStartOfDay(Date date) { if (date == null) { throw
	 * new IllegalArgumentException("date cannot be null"); } Calendar calendar =
	 * Calendar.getInstance(); calendar.setTime(date); calendar.set(Calendar.HOUR,
	 * 0); calendar.set(Calendar.MINUTE, 0); calendar.set(Calendar.SECOND, 0);
	 * calendar.set(Calendar.MILLISECOND, 0); calendar.set(Calendar.AM_PM,
	 * Calendar.AM); return calendar; }
	 */


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}