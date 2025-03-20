package com.api.mobile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateBookingResponse {
    private LocalDate date;
    private GetFieldResponse field;
    private GetSlotResponse slot;
}
