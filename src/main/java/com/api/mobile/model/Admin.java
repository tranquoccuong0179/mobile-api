package com.api.mobile.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Admin extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "admin")
    private List<Field> fields;
}
