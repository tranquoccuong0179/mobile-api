package com.api.mobile.controller;

import com.api.mobile.dto.request.CreateFieldRequest;
import com.api.mobile.dto.request.UpdateFieldRequest;
import com.api.mobile.dto.response.*;
import com.api.mobile.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/field")
@RequiredArgsConstructor
public class FieldController {
    private final FieldService fieldService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIResponse<CreateFieldResponse>> create(@RequestBody CreateFieldRequest request){
        CreateFieldResponse response = fieldService.createField(request);
        return ResponseEntity.ok(new APIResponse<>("200", "Tao thành công", response));
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<APIResponse<List<GetFieldResponse>>> getAll(){
        List<GetFieldResponse> response = fieldService.getAllField();
        return ResponseEntity.ok(new APIResponse<>("200", "List thành công", response));
    }
    @GetMapping(value = "/get-id-by-user/{id}")
    public ResponseEntity<APIResponse<GetFieldResponseUser>> getbyid(@PathVariable UUID id, @RequestParam(required = false) Date date){
        GetFieldResponseUser response = fieldService.getFieldById(id, date);
        return ResponseEntity.ok(new APIResponse<>("200", "Field thành công", response));
    }
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<APIResponse<UpdateFieldResponse>> update(@PathVariable UUID id, @RequestBody UpdateFieldRequest request) {
        UpdateFieldResponse response = fieldService.updateField(id, request);
        return ResponseEntity.ok(new APIResponse<>("200", "Cập nhật thành công", response));
    }
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<APIResponse<String>> delete(@PathVariable UUID id) {
        boolean deleted = fieldService.deleteField(id);
        if (deleted) {
            return ResponseEntity.ok(new APIResponse<>("200", "Xóa thành công", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse<>(HttpStatus.NOT_FOUND.toString(), "Không tìm thấy Field để xóa", null));
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/get-id-by-admin/{id}")
    public ResponseEntity<APIResponse<GetFieldResponse>> getbyid(@PathVariable UUID id){
        GetFieldResponse response = fieldService.getFieldByIdAdmin(id);
        return ResponseEntity.ok(new APIResponse<>("200", "Field thành công", response));
    }

}
