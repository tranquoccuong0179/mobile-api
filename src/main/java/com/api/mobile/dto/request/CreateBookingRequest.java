package com.api.mobile.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateBookingRequest {
    private LocalDate date;
    private UUID slotId;
    private UUID fieldId;
}
