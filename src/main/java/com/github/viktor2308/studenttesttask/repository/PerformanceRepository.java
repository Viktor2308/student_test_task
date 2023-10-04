package com.github.viktor2308.studenttesttask.repository;

import com.github.viktor2308.studenttesttask.entity.Performance;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


public interface PerformanceRepository extends R2dbcRepository<Performance, Long> {
}
