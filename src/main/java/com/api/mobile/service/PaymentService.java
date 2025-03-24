package com.api.mobile.service;

import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

public interface PaymentService {
    public String payWithVNPAYOnline(HttpServletRequest request, UUID id) throws UnsupportedEncodingException;
}
