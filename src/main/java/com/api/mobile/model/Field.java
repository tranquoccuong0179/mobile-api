package com.api.mobile.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Field extends BaseEntity{
    private String name;
    private String location;
    private double price;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "field")
    private List<Booking> bookings;
}
