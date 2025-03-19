package com.api.mobile.service;

import com.api.mobile.dto.request.AuthenticateRequest;
import com.api.mobile.dto.request.RegisterAccountRequest;
import com.api.mobile.dto.response.APIResponse;
import com.api.mobile.dto.response.AuthenticationResponse;
import com.api.mobile.dto.response.RegisterAccountResponse;
import com.api.mobile.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public RegisterAccountResponse Register(RegisterAccountRequest registerAccountRequest);
    public AuthenticationResponse Auth(AuthenticateRequest authenticateRequest);
}
