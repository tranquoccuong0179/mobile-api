package com.api.mobile.service.impl;

import com.api.mobile.dto.request.CreateCategoryRequest;
import com.api.mobile.dto.response.CreateCategoryResponse;
import com.api.mobile.dto.response.GetCategoryResponse;
import com.api.mobile.dto.response.GetFieldResponse;
import com.api.mobile.model.Category;
import com.api.mobile.repository.CategoryRepository;
import com.api.mobile.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public CreateCategoryResponse createCategory(CreateCategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setArea(request.getArea());
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
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
}
