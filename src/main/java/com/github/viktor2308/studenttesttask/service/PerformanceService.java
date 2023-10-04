package com.github.viktor2308.studenttesttask.service;

import com.github.viktor2308.studenttesttask.dto.PerformanceDto;
import com.github.viktor2308.studenttesttask.entity.Performance;
import reactor.core.publisher.Mono;

public interface PerformanceService {

    Mono<Performance> updatePerformance(long id, PerformanceDto performanceDto);

    Mono<Performance> findById(long id);
}
