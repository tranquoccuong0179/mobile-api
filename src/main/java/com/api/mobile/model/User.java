package com.api.mobile.model;

import com.api.mobile.enums.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    @OneToMany(mappedBy = "user")
    private List<Field> fields;
    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;
}
