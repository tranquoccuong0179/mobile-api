package com.api.mobile.service;

import com.api.mobile.dto.request.CreateCategoryRequest;
import com.api.mobile.dto.request.UpdateCategoryRequest;
import com.api.mobile.dto.response.CreateCategoryResponse;
import com.api.mobile.dto.response.GetCategoryResponse;
import com.api.mobile.dto.response.UpdateCategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CreateCategoryResponse createCategory(CreateCategoryRequest request);
    List<GetCategoryResponse> getCategories();
    GetCategoryResponse getCategory(UUID id);
    boolean deleteCategory(UUID id);
    UpdateCategoryResponse updateCategory(UUID id, UpdateCategoryRequest request);
}
