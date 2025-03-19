package com.api.mobile.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
public class UpdateSlotRequest {
    private Time startTime;
    private Time endTime;
    private Date slotDate;
}