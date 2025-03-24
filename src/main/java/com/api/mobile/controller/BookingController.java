package com.api.mobile.controller;

import com.api.mobile.dto.request.CreateBookingRequest;
import com.api.mobile.dto.response.APIResponse;
import com.api.mobile.dto.response.CreateBookingResponse;
import com.api.mobile.dto.response.GetFieldResponse;
import com.api.mobile.dto.response.GetHistoryResponse;
import com.api.mobile.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping(value = "/get-all")
    public ResponseEntity<APIResponse<List<GetHistoryResponse>>> getAll(){
        List<GetHistoryResponse> response = bookingService.getAllBookings();
        return ResponseEntity.ok(new APIResponse<>("200", "List thành công", response));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/get-all-by-admin")
    public ResponseEntity<APIResponse<List<GetHistoryResponse>>> getAllByAdmin(){
        List<GetHistoryResponse> response = bookingService.getAllBookingsByAdmin();
        return ResponseEntity.ok(new APIResponse<>("200", "List thành công", response));
    }
}
