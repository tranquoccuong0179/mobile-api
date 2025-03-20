package com.api.mobile.service;

import com.api.mobile.dto.request.CreateBookingRequest;
import com.api.mobile.dto.response.CreateBookingResponse;

public interface BookingService {
    CreateBookingResponse createBooking(CreateBookingRequest request);
}
