package com.api.mobile.service.impl;

import com.api.mobile.dto.request.CreateBookingRequest;
import com.api.mobile.dto.response.*;
import com.api.mobile.model.*;
import com.api.mobile.repository.*;
import com.api.mobile.service.BookingService;
import com.api.mobile.util.AuthenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final FieldRepository fieldRepository;
    private final SlotRepository slotRepository;

    @Override
    public CreateBookingResponse createBooking(CreateBookingRequest request) {
        UUID userId = AuthenUtil.getCurrentUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Field field = fieldRepository.findById(request.getFieldId()).orElseThrow(() -> new RuntimeException("Field not found"));
        Slot slot = slotRepository.findById(request.getSlotId()).orElseThrow(() -> new RuntimeException("Slot not found"));
        Booking booking = new Booking();
        booking.setField(field);
        booking.setSlot(slot);
        booking.setUser(user);
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

    @Override
    public List<GetHistoryResponse> getAllBookings() {
        UUID userId = AuthenUtil.getCurrentUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<GetHistoryResponse> responses = new ArrayList<>();
        List<Booking> bookings = bookingRepository.findByUserId(user.getId());
        for (Booking booking : bookings) {
            GetHistoryResponse response = new GetHistoryResponse();
            Field field = fieldRepository.findById(booking.getField().getId()).get();
            Category category = field.getCategory();
            response.setName(category.getName());
            response.setLocation(field.getLocation());
            response.setAmount(field.getPrice());
            responses.add(response);
        }
        return responses;
    }

    @Override
    public List<GetHistoryResponse> getAllBookingsByAdmin() {
        List<GetHistoryResponse> responses = new ArrayList<>();
        List<Booking> bookings = bookingRepository.findAll();
        for (Booking booking : bookings) {
            GetHistoryResponse response = new GetHistoryResponse();
            Field field = fieldRepository.findById(booking.getField().getId()).get();
            Category category = field.getCategory();
            response.setName(category.getName());
            response.setLocation(field.getLocation());
            response.setAmount(field.getPrice());
            responses.add(response);
        }
        return responses;
    }

}
