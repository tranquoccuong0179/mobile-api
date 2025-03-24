package com.api.mobile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class GetFieldResponse {
    private UUID id;
    private String location;
    private double price;
    private GetCategoryResponse category;
}
