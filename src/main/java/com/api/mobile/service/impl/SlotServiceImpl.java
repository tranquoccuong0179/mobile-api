package com.api.mobile.service.impl;

import com.api.mobile.dto.request.CreateSlotRequest;
import com.api.mobile.dto.request.UpdateSlotRequest;
import com.api.mobile.dto.response.CreateSlotResponse;
import com.api.mobile.dto.response.GetSlotResponse;
import com.api.mobile.dto.response.UpdateSlotResponse;
import com.api.mobile.model.Field;
import com.api.mobile.model.Slot;
import com.api.mobile.repository.FieldRepository;
import com.api.mobile.repository.SlotRepository;
import com.api.mobile.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SlotServiceImpl implements SlotService {
    private final SlotRepository slotRepository;
    private final FieldRepository fieldRepository;

    @Override
    public CreateSlotResponse createSlot(CreateSlotRequest request) {
        // Tìm Field theo fieldId
        Field field = fieldRepository.findById(request.getFieldId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Field với id: " + request.getFieldId()));

        // Tạo Slot mới
        Slot slot = new Slot();
        slot.setStartTime(request.getStartTime());
        slot.setEndTime(request.getEndTime());
        slot.setField(field);
        slot.setCreatedAt(LocalDateTime.now());
        slot.setUpdatedAt(LocalDateTime.now());
        slotRepository.save(slot);

        // Tạo response
        CreateSlotResponse response = new CreateSlotResponse();
        response.setId(slot.getId());
        response.setStartTime(slot.getStartTime());
        response.setEndTime(slot.getEndTime());
        response.setFieldId(slot.getField().getId());
        return response;
    }

    @Override
    public List<GetSlotResponse> getAllSlots() {
        List<GetSlotResponse> list = new ArrayList<>();
        List<Slot> slots = slotRepository.findAll();
        for (Slot slot : slots) {
            GetSlotResponse response = new GetSlotResponse();
            response.setId(slot.getId());
            response.setStartTime(slot.getStartTime());
            response.setEndTime(slot.getEndTime());
            list.add(response);
        }
        return list;
    }

    @Override
    public GetSlotResponse getSlotById(UUID id) {
        Slot slot = slotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Slot với id: " + id));
        GetSlotResponse response = new GetSlotResponse();
        response.setId(slot.getId());
        response.setStartTime(slot.getStartTime());
        response.setEndTime(slot.getEndTime());
        return response;
    }

    @Override
    public UpdateSlotResponse updateSlot(UUID id, UpdateSlotRequest request) {
        Slot slot = slotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Slot với id: " + id));
        slot.setStartTime(request.getStartTime());
        slot.setEndTime(request.getEndTime());
        slot.setUpdatedAt(LocalDateTime.now());
        slotRepository.save(slot);

        UpdateSlotResponse response = new UpdateSlotResponse();
        response.setId(slot.getId());
        response.setStartTime(slot.getStartTime());
        response.setEndTime(slot.getEndTime());
        response.setFieldId(slot.getField().getId());
        return response;
    }

    @Override
    public boolean deleteSlot(UUID id) {
        if (slotRepository.existsById(id)) {
            slotRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<GetSlotResponse> getSlotByField(UUID id) {
        Field field = fieldRepository.findById(id).orElse(null);
        List<Slot> slots = slotRepository.findByFieldId(field.getId());
        List<GetSlotResponse> responses = new ArrayList<>();
        for (Slot slot : slots) {
            GetSlotResponse response = new GetSlotResponse();
            response.setId(slot.getId());
            response.setStartTime(slot.getStartTime());
            response.setEndTime(slot.getEndTime());
            responses.add(response);
        }

        return responses;
    }
}