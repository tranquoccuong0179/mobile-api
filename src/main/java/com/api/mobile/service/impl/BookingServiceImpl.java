package com.api.mobile.service.impl;

import com.api.mobile.dto.request.CreateBookingRequest;
import com.api.mobile.dto.response.CreateBookingResponse;
import com.api.mobile.dto.response.GetFieldResponse;
import com.api.mobile.dto.response.GetSlotResponse;
import com.api.mobile.model.*;
import com.api.mobile.repository.*;
import com.api.mobile.service.BookingService;
import com.api.mobile.util.AuthenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final FieldRepository fieldRepository;
    private final SlotRepository slotRepository;
    private final CustomerRepository customerRepository;

    @Override
    public CreateBookingResponse createBooking(CreateBookingRequest request) {
        UUID userId = AuthenUtil.getCurrentUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Customer customer = customerRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Customer not found"));
        Field field = fieldRepository.findById(request.getFieldId()).orElseThrow(() -> new RuntimeException("Field not found"));
        Slot slot = slotRepository.findById(request.getSlotId()).orElseThrow(() -> new RuntimeException("Slot not found"));
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setField(field);
        booking.setSlot(slot);
        booking.setDate(request.getDate());
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());
        booking.setActive(true);
        bookingRepository.save(booking);
        GetFieldResponse fieldResponse = new GetFieldResponse();
        fieldResponse.setId(field.getId());
        fieldResponse.setLocation(field.getLocation());
        fieldResponse.setPrice(field.getPrice());
        GetSlotResponse slotResponse = new GetSlotResponse();
        slotResponse.setId(slot.getId());
        slotResponse.setStartTime(slot.getStartTime());
        slotResponse.setEndTime(slot.getEndTime());
        CreateBookingResponse response = new CreateBookingResponse();
        response.setDate(booking.getDate());
        response.setField(fieldResponse);
        response.setSlot(slotResponse);
        return response;
    }

}
