package com.dto;

import com.domain.Tool;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDto {
    private long id;
    private String country;
    private String city;
    private String zipCode;
    private String address;
    private String email;
    private String phone;

    @JsonIgnore
    private List<Tool> toolList;

}
