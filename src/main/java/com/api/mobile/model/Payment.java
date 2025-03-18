package com.api.mobile.model;

import com.api.mobile.enums.PaymentEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Payment extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "booking_id")
    @JsonIgnore
    private Booking booking;
    private double amount;
    @Enumerated(EnumType.STRING)
    private PaymentEnum status;
}
