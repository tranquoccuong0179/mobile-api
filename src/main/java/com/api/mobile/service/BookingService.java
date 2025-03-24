package com.api.mobile.service;

import com.api.mobile.dto.request.CreateBookingRequest;
import com.api.mobile.dto.response.CreateBookingResponse;
import com.api.mobile.dto.response.GetBookingResponse;
import com.api.mobile.dto.response.GetHistoryResponse;

import java.util.List;

public interface BookingService {
    CreateBookingResponse createBooking(CreateBookingRequest request);
    List<GetHistoryResponse> getAllBookings();
    List<GetHistoryResponse> getAllBookingsByAdmin();
}
