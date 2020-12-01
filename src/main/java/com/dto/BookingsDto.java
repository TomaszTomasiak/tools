package com.dto;

import com.domain.Tool;
import com.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingsDto {
    private long id;
    private Tool tool;
    private User user;
    private LocalDate bookedDateFrom;
    private LocalDate bookedDateTo;
}
