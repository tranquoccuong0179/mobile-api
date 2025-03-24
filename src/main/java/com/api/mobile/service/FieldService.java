package com.api.mobile.service;

import com.api.mobile.dto.request.CreateFieldRequest;
import com.api.mobile.dto.request.UpdateFieldRequest;
import com.api.mobile.dto.response.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface FieldService {
    CreateFieldResponse createField(CreateFieldRequest request);
    List<GetFieldResponse> getAllField();
    GetFieldResponseUser getFieldById(UUID id, Date date);
    UpdateFieldResponse updateField(UUID id, UpdateFieldRequest request);
    GetFieldResponse getFieldByIdAdmin(UUID id);
    boolean deleteField(UUID id);
}
