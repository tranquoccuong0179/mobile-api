package com.api.mobile.service.impl;

import com.api.mobile.dto.request.CreateFieldRequest;
import com.api.mobile.dto.request.UpdateFieldRequest;
import com.api.mobile.dto.response.CreateFieldResponse;

import com.api.mobile.dto.response.GetCategoryResponse;
import com.api.mobile.dto.response.GetFieldResponse;
import com.api.mobile.dto.response.UpdateFieldResponse;
import com.api.mobile.model.Category;
import com.api.mobile.model.Field;
import com.api.mobile.repository.CategoryRepository;
import com.api.mobile.repository.FieldRepository;
import com.api.mobile.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {
    private final CategoryRepository categoryRepository;
    private final FieldRepository fieldRepository;
    @Override
    public CreateFieldResponse createField(CreateFieldRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        Field fields = new Field();
        fields.setLocation(request.getLocation());
        fields.setPrice(request.getPrice());
        fields.setCreatedAt(LocalDateTime.now());
        fields.setUpdatedAt(LocalDateTime.now());
        fields.setActive(true);
        fields.setCategory(category);
        fieldRepository.save(fields);
        CreateFieldResponse response = new CreateFieldResponse();
        response.setId(fields.getId());
        response.setLocation(fields.getLocation());
        response.setPrice(fields.getPrice());
        return response;
    }

    @Override
    public List<GetFieldResponse> getAllField() {
        List<GetFieldResponse> list = new ArrayList<>();
        List<Field> fields = fieldRepository.findAll();
        for (Field field : fields){
            GetFieldResponse response = new GetFieldResponse();
            response.setId(field.getId());
            response.setLocation(field.getLocation());
            response.setPrice(field.getPrice());
            GetCategoryResponse responseCategory = new GetCategoryResponse();
            responseCategory.setId(field.getCategory().getId());
            responseCategory.setName(field.getCategory().getName());
            responseCategory.setArea(field.getCategory().getArea());
            response.setCategory(responseCategory);
            list.add(response);
        }
        return list;
    }

    @Override
    public GetFieldResponse getFieldById(UUID id) {
        Field field = fieldRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy"));
        GetFieldResponse response = new GetFieldResponse();
        response.setId(field.getId());
        response.setLocation(field.getLocation());
        response.setPrice(field.getPrice());
        GetCategoryResponse responseCategory = new GetCategoryResponse();
        responseCategory.setId(field.getCategory().getId());
        responseCategory.setName(field.getCategory().getName());
        responseCategory.setArea(field.getCategory().getArea());
        response.setCategory(responseCategory);
        return response;
    }

    @Override
    public UpdateFieldResponse updateField(UUID id, UpdateFieldRequest request) {
        Field field = fieldRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy"));
        field.setLocation(request.getLocation());
        field.setPrice(request.getPrice());
        field.setUpdatedAt(LocalDateTime.now());
        field.setCategory(field.getCategory());
        fieldRepository.save(field);
        UpdateFieldResponse response = new UpdateFieldResponse();
        response.setId(field.getId());
        response.setLocation(field.getLocation());
        response.setPrice(field.getPrice());
        return response;
    }

    @Override
    public boolean deleteField(UUID id) {
        Optional<Field> field = fieldRepository.findById(id);
        if (field.isPresent()) {
            field.get().setActive(false);
            field.get().setUpdatedAt(LocalDateTime.now());
            fieldRepository.save(field.get());
            return true;
        }
        return false;
    }


}
