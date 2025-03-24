package com.api.mobile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetFieldResponseUser {
    private String location;
    private double price;
    private List<GetSlotBookingResponse> slots;
    private GetCategoryResponse category;
}
