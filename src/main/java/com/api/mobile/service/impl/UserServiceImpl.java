package com.api.mobile.service.impl;

import com.api.mobile.dto.request.AuthenticateRequest;
import com.api.mobile.dto.request.RegisterAccountRequest;
import com.api.mobile.dto.response.APIResponse;
import com.api.mobile.dto.response.AuthenticationResponse;
import com.api.mobile.dto.response.RegisterAccountResponse;
import com.api.mobile.enums.RoleEnum;
import com.api.mobile.model.Customer;
import com.api.mobile.model.User;
import com.api.mobile.repository.CustomerRepository;
import com.api.mobile.repository.UserRepository;
import com.api.mobile.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    @Override
    public RegisterAccountResponse Register(RegisterAccountRequest registerAccountRequest) {
        User user = new User();
        user.setUsername(registerAccountRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerAccountRequest.getPassword()));
        user.setEmail(registerAccountRequest.getEmail());
        user.setFullName(registerAccountRequest.getFullName());
        user.setPhone(registerAccountRequest.getPhone());
        user.setRole(RoleEnum.USER);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setActive(true);
        userRepository.save(user);

        Customer customer = new Customer();
        customer.setUser(user);
        customerRepository.save(customer);

        RegisterAccountResponse response = new RegisterAccountResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole().toString());
        response.setUserId(customer.getId());

        return response;
    }

    @Override
    public RegisterAccountResponse RegisterAdmin(RegisterAccountRequest registerAccountRequest) {
        User user = new User();
        user.setUsername(registerAccountRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerAccountRequest.getPassword()));
        user.setEmail(registerAccountRequest.getEmail());
        user.setFullName(registerAccountRequest.getFullName());
        user.setPhone(registerAccountRequest.getPhone());
        user.setRole(RoleEnum.ADMIN);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setActive(true);
        userRepository.save(user);

        Customer customer = new Customer();
        customer.setUser(user);
        customerRepository.save(customer);

        RegisterAccountResponse response = new RegisterAccountResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole().toString());
        response.setUserId(customer.getId());
        return response;
    }

    @Override
    public AuthenticationResponse Auth(AuthenticateRequest authenticateRequest) {
        Authentication authentication;
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateRequest.getUsername(),
                        authenticateRequest.getPassword()
                )
        );
        User user = (User) authentication.getPrincipal();
        var jwtToken = jwtService.generateToken(user);

        authenticationResponse.setToken(jwtToken);
        authenticationResponse.setUsername(authenticateRequest.getUsername());
        authenticationResponse.setRole(user.getRole().toString());
        return authenticationResponse;
    }
}
