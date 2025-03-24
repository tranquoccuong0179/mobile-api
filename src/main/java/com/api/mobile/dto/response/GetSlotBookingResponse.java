package com.api.mobile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetSlotBookingResponse {
    private Time startTime;
    private Time endTime;
    private boolean booked;
}
