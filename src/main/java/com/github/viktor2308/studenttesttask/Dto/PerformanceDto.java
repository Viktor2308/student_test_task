package com.github.viktor2308.studenttesttask.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerformanceDto {

    @NotBlank (message = "Text not be null")
    private String text;
}
