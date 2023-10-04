package com.github.viktor2308.studenttesttask.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NewStudentRequest {

    @NotBlank(message = "Name not be null")
    private String fullName;
    @NotNull
    private LocalDate birth;
}
