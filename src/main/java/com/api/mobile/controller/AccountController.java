package com.api.mobile.controller;

import com.api.mobile.dto.request.AuthenticateRequest;
import com.api.mobile.dto.request.RegisterAccountRequest;
import com.api.mobile.dto.response.APIResponse;
import com.api.mobile.dto.response.AuthenticationResponse;
import com.api.mobile.dto.response.RegisterAccountResponse;
import com.api.mobile.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AccountController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<APIResponse<RegisterAccountResponse>> register(@RequestBody RegisterAccountRequest request) {
        RegisterAccountResponse response = userService.Register(request);
        return ResponseEntity.ok().body(new APIResponse<>("200", "Create Successfull", response));
    }

    @PostMapping("/auth")
    public ResponseEntity<APIResponse<AuthenticationResponse>> auth(@RequestBody AuthenticateRequest request) {
        AuthenticationResponse response = userService.Auth(request);
        return ResponseEntity.ok().body(new APIResponse<>("200", "Login Successfull", response));
    }
}
