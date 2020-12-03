package com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingDto {
    private long id;
    private long toolId;
    private long userId;
    private LocalDate bookedDateFrom;
    private LocalDate bookedDateTo;
}