package com.api.mobile.model;

import jakarta.persistence.Entity;
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
public class Category extends BaseEntity{
    private String name;
    private double area;
    @OneToMany(mappedBy = "category")
    private List<Field> fields;
}
