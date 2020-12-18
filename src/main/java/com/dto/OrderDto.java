package com.dto;

import com.domain.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private long id;
    private long userId;

    @Builder.Default
    private List<Booking> bookings = new ArrayList<>();

    private BigDecimal totalCost;
}

