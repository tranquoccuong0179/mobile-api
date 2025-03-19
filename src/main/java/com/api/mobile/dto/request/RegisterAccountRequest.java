package com.api.mobile.dto.request;

import com.api.mobile.enums.RoleEnum;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterAccountRequest {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String fullName;
}
