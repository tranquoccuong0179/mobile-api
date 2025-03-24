package com.api.mobile.service.impl;

import com.api.mobile.dto.request.CreateCategoryRequest;
import com.api.mobile.dto.request.UpdateCategoryRequest;
import com.api.mobile.dto.response.CreateCategoryResponse;
import com.api.mobile.dto.response.GetCategoryResponse;
import com.api.mobile.dto.response.GetFieldResponse;
import com.api.mobile.dto.response.UpdateCategoryResponse;
import com.api.mobile.model.Category;
import com.api.mobile.repository.CategoryRepository;
import com.api.mobile.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public CreateCategoryResponse createCategory(CreateCategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setArea(request.getArea());
        category.setActive(true);
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        category.setActive(true);
        categoryRepository.save(category);
        CreateCategoryResponse response = new CreateCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setArea(category.getArea());
        return response;
    }

    @Override
    public List<GetCategoryResponse> getCategories() {
        List<GetCategoryResponse> list = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories){
            GetCategoryResponse response = new GetCategoryResponse();
            response.setId(category.getId());
            response.setName(category.getName());
            response.setArea(category.getArea());
            list.add(response);
        }
        return list;
    }

    @Override
    public GetCategoryResponse getCategory(UUID id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy"));
        GetCategoryResponse response = new GetCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setArea(category.getArea());
        return response;
    }

    @Override
    public boolean deleteCategory(UUID id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy"));
        category.setActive(false);
        categoryRepository.save(category);
        return true;
    }

    @Override
    public UpdateCategoryResponse updateCategory(UUID id, UpdateCategoryRequest request) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy"));
        category.setName(request.getName() == null ? category.getName() : request.getName());
        category.setArea(request.getArea() == 0 ? category.getArea() : request.getArea());
        category.setUpdatedAt(LocalDateTime.now());
        categoryRepository.save(category);
        UpdateCategoryResponse response = new UpdateCategoryResponse();
        response.setName(category.getName());
        response.setArea(category.getArea());
        return response;
    }
}
