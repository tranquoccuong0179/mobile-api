package com.api.mobile.controller;

import com.api.mobile.dto.request.CreateFieldRequest;
import com.api.mobile.dto.request.UpdateFieldRequest;
import com.api.mobile.dto.response.APIResponse;
import com.api.mobile.dto.response.CreateFieldResponse;
import com.api.mobile.dto.response.GetFieldResponse;
import com.api.mobile.dto.response.UpdateFieldResponse;
import com.api.mobile.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/field")
@RequiredArgsConstructor
public class FieldController {
    private final FieldService fieldService;
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIResponse<CreateFieldResponse>> create(@RequestBody CreateFieldRequest request){
        CreateFieldResponse response = fieldService.createField(request);
        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.toString(), "Tao thành công", response));
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<APIResponse<List<GetFieldResponse>>> getAll(){
        List<GetFieldResponse> response = fieldService.getAllField();
        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.toString(), "List thành công", response));
    }
    @GetMapping(value = "/get-id/{id}")
    public ResponseEntity<APIResponse<GetFieldResponse>> getbyid(@PathVariable UUID id){
        GetFieldResponse response = fieldService.getFieldById(id);
        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.toString(), "Field thành công", response));
    }
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIResponse<UpdateFieldResponse>> update(@PathVariable UUID id, @RequestBody UpdateFieldRequest request) {
        UpdateFieldResponse response = fieldService.updateField(id, request);
        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.toString(), "Cập nhật thành công", response));
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<APIResponse<String>> delete(@PathVariable UUID id) {
        boolean deleted = fieldService.deleteField(id);
        if (deleted) {
            return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.toString(), "Xóa thành công", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse<>(HttpStatus.NOT_FOUND.toString(), "Không tìm thấy Field để xóa", null));
        }
    }

}
