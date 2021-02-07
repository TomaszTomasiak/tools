package com.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate bookedDateFrom;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate bookedDateTo;

    private long toolId;

    private long cartId;


}
