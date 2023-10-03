package com.github.viktor2308.studenttesttask.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table("student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    private UUID studentId;
    private String fullName;
    private LocalDate birth;
    private Performance performance;
}
