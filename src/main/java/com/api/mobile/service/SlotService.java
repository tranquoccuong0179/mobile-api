package com.api.mobile.service;

import com.api.mobile.dto.request.CreateSlotRequest;
import com.api.mobile.dto.request.UpdateSlotRequest;
import com.api.mobile.dto.response.CreateSlotResponse;
import com.api.mobile.dto.response.GetSlotResponse;
import com.api.mobile.dto.response.UpdateSlotResponse;

import java.util.List;
import java.util.UUID;

public interface SlotService {
    CreateSlotResponse createSlot(CreateSlotRequest request);
    List<GetSlotResponse> getAllSlots();
    GetSlotResponse getSlotById(UUID id);
    UpdateSlotResponse updateSlot(UUID id, UpdateSlotRequest request);
    boolean deleteSlot(UUID id);
}