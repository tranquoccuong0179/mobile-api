package com.api.mobile.service;

import com.api.mobile.dto.request.CreateCategoryRequest;
import com.api.mobile.dto.response.CreateCategoryResponse;
import com.api.mobile.dto.response.GetCategoryResponse;

import java.util.List;

public interface CategoryService {
    CreateCategoryResponse createCategory(CreateCategoryRequest request);
    List<GetCategoryResponse> getCategories();
}
