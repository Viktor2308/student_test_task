package com.github.viktor2308.studenttesttask.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class StudentRequest {
    @NotBlank (message = "Name not be null")
    private String fullName;
    @NotBlank (message = "Birth not be null")
    private LocalDate birth;
}
