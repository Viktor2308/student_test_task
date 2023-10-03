package com.github.viktor2308.studenttesttask.Dto;

import com.github.viktor2308.studenttesttask.entity.Performance;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class StudentDto {

    @NotBlank(message = "Name not be null")
    private String fullName;
    private LocalDate birth;
    @NotBlank (message = "Performance not be null")
    private Performance performance;
}
