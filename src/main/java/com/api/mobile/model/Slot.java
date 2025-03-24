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

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Slot extends BaseEntity{
    private Time startTime;
    private Time endTime;
    @ManyToOne
    @JoinColumn(name = "field_id")
    @JsonIgnore
    private Field field;

    @OneToMany(mappedBy = "slot")
    private List<Booking> bookings;
}
