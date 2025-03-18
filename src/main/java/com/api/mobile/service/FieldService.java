package com.api.mobile.service;

import com.api.mobile.dto.request.CreateFieldRequest;
import com.api.mobile.dto.request.UpdateFieldRequest;
import com.api.mobile.dto.response.CreateFieldResponse;
import com.api.mobile.dto.response.GetFieldResponse;
import com.api.mobile.dto.response.UpdateFieldResponse;

import java.util.List;
import java.util.UUID;

public interface FieldService {
    CreateFieldResponse createField(CreateFieldRequest request);
    List<GetFieldResponse> getAllField();
    GetFieldResponse getFieldById(UUID id);
    UpdateFieldResponse updateField(UUID id, UpdateFieldRequest request);

    boolean deleteField(UUID id);

}
