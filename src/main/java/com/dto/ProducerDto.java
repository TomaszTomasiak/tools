package com.dto;

import com.domain.Model;
import com.domain.Tool;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProducerDto {
    private long id;
    private String name;

    @JsonIgnore
    private List<Tool> toolList;

    @JsonIgnore
    private List<Model> modelList;
}
