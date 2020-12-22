package com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDto {
    private long id;
    private String country;
    private String city;
    private String zipCode;
    private String addres;
    private String email;
    private String phone;
}
