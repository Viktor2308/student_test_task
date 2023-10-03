package com.github.viktor2308.studenttesttask.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Table("performance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Performance {

    private Long performanceId;
    private String text;
}
