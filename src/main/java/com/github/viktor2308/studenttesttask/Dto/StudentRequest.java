package com.github.viktor2308.studenttesttask.Dto;

import com.github.viktor2308.studenttesttask.entity.Performance;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class StudentRequest {
    @NotBlank
    private String fullName;
    @NotBlank
    private LocalDate birth;
    private Performance performance;
}
