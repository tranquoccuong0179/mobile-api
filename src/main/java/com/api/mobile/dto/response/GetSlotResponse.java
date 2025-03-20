package com.api.mobile.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Getter
@Setter
public class GetSlotResponse {
    private UUID id;
    private Time startTime;
    private Time endTime;
}