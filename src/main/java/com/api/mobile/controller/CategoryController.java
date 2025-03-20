package com.api.mobile.controller;

import com.api.mobile.dto.request.CreateCategoryRequest;
import com.api.mobile.dto.request.UpdateCategoryRequest;
import com.api.mobile.dto.response.APIResponse;
import com.api.mobile.dto.response.CreateCategoryResponse;
import com.api.mobile.dto.response.GetCategoryResponse;
import com.api.mobile.dto.response.UpdateCategoryResponse;
import com.api.mobile.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<APIResponse<CreateCategoryResponse>> create(@RequestBody CreateCategoryRequest request) {
        CreateCategoryResponse response = categoryService.createCategory(request);
        return ResponseEntity.ok().body(new APIResponse<>("200", "Create Successfull", response));
    }

    @GetMapping("")
    public ResponseEntity<APIResponse<List<GetCategoryResponse>>> getAll() {
        List<GetCategoryResponse> response = categoryService.getCategories();
        return ResponseEntity.ok().body(new APIResponse<>("200", "List Successfull", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<GetCategoryResponse>> get(@PathVariable UUID id) {
        GetCategoryResponse response = categoryService.getCategory(id);
        return ResponseEntity.ok().body(new APIResponse<>("200", "Successfull", response));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<UpdateCategoryResponse>> update(@PathVariable UUID id, UpdateCategoryRequest request) {
        UpdateCategoryResponse response = categoryService.updateCategory(id, request);
        return ResponseEntity.ok().body(new APIResponse<>("200", "Update Successfull", response));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Boolean>> delete(@PathVariable UUID id) {
        boolean response = categoryService.deleteCategory(id);
        return ResponseEntity.ok().body(new APIResponse<>("200", "Delete Successfull", response));
    }

}
