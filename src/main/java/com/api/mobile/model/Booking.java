package com.api.mobile.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Booking extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @ManyToOne
    @JoinColumn(name = "field_id")
    @JsonIgnore
    private Field field;
    private LocalDate date;
}
