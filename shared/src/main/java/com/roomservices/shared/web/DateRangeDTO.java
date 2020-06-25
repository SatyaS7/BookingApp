package com.roomservices.shared.web;

import javax.validation.constraints.NotNull;

public class DateRangeDTO {

    private static final long MAX_DURATION = 2592000000l; // 30 days

    @NotNull
    private String from;
    
    @NotNull
    private String until;
    
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getUntil() {
        return until;
    }
    public void setUntil(String until) {
        this.until = until;
    }
    
}