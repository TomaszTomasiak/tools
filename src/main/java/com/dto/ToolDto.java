package com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToolDto {
    private long id;
    private String name;
    private String producer;
    private String model;
    private long groupId;
    private BigDecimal rentRate;
    private long locationId;
}
