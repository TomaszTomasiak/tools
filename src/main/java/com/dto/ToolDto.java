package com.dto;

import com.domain.ToolsGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToolDto {
    private Long id;
    private String producer;
    private String model;
    private Long groupId;
}
