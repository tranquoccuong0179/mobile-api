package com.api.mobile.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;

public interface PaymentService {
    public String payWithVNPAYOnline(HttpServletRequest request, UUID id) throws UnsupportedEncodingException;
    public boolean processPaymentCallback(Map<String, String> queryParams, HttpServletResponse response) throws IOException;
}
