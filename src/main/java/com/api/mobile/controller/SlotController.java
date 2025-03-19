package com.api.mobile.controller;

import com.api.mobile.dto.request.CreateSlotRequest;
import com.api.mobile.dto.request.UpdateSlotRequest;
import com.api.mobile.dto.response.APIResponse;
import com.api.mobile.dto.response.CreateSlotResponse;
import com.api.mobile.dto.response.GetSlotResponse;
import com.api.mobile.dto.response.UpdateSlotResponse;
import com.api.mobile.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/slot")
@RequiredArgsConstructor
public class SlotController {
    private final SlotService slotService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIResponse<CreateSlotResponse>> create(@RequestBody CreateSlotRequest request) {
        CreateSlotResponse response = slotService.createSlot(request);
        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.toString(), "Tạo Slot thành công", response));
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<APIResponse<List<GetSlotResponse>>> getAll() {
        List<GetSlotResponse> response = slotService.getAllSlots();
        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.toString(), "Lấy danh sách Slot thành công", response));
    }

    @GetMapping(value = "/get-id/{id}")
    public ResponseEntity<APIResponse<GetSlotResponse>> getById(@PathVariable UUID id) {
        GetSlotResponse response = slotService.getSlotById(id);
        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.toString(), "Lấy Slot thành công", response));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIResponse<UpdateSlotResponse>> update(@PathVariable UUID id, @RequestBody UpdateSlotRequest request) {
        UpdateSlotResponse response = slotService.updateSlot(id, request);
        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.toString(), "Cập nhật Slot thành công", response));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<APIResponse<String>> delete(@PathVariable UUID id) {
        boolean deleted = slotService.deleteSlot(id);
        if (deleted) {
            return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.toString(), "Xóa Slot thành công", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse<>(HttpStatus.NOT_FOUND.toString(), "Không tìm thấy Slot để xóa", null));
        }
    }
}