package com.api.mobile.dto.response;

import com.api.mobile.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterAccountResponse {
    private UUID id;
    private String username;
    private String email;
    private String phone;
    private String fullName;
    private String role;
}
