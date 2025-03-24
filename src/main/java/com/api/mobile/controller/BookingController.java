package com.api.mobile.controller;

import com.api.mobile.dto.request.CreateBookingRequest;
import com.api.mobile.dto.response.APIResponse;
import com.api.mobile.dto.response.CreateBookingResponse;
import com.api.mobile.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;
    @PostMapping("/create")
    public ResponseEntity<APIResponse<CreateBookingResponse>> create(@RequestBody CreateBookingRequest request) {
        CreateBookingResponse response = bookingService.createBooking(request);
        return ResponseEntity.ok().body(new APIResponse<>("200", "Create Successfull", response));
    }
}
