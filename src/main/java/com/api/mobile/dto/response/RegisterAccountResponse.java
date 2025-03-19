package com.api.mobile.dto.response;

import com.api.mobile.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterAccountResponse {
    private String username;
    private String email;
    private String phone;
    private String fullName;
    private String role;
}
