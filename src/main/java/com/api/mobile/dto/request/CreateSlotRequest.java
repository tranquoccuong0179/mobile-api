package com.api.mobile.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Getter
@Setter
public class CreateSlotRequest {
    private Time startTime;
    private Time endTime;
    private Date slotDate;
    private UUID fieldId; // Để liên kết với Field
}