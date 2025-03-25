package com.api.mobile.controller;

import com.api.mobile.dto.response.APIResponse;
import com.api.mobile.model.Payment;
import com.api.mobile.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping("/create")
    public ResponseEntity<APIResponse<String>> Create(HttpServletRequest request, @RequestParam UUID id) throws UnsupportedEncodingException {
        String paymentUrl = paymentService.payWithVNPAYOnline(request, id);
        return ResponseEntity.ok(new APIResponse<>("200", "Tạo mã thanh toán thành công", paymentUrl));
    }

    @GetMapping("/payment-callback")
    public ResponseEntity<Boolean> paymentCallback(
            @RequestParam Map<String, String> queryParams,
            HttpServletResponse response) throws IOException {
        boolean paymentStatus = paymentService.processPaymentCallback(queryParams, response);
        return ResponseEntity.ok(paymentStatus);
    }
}
