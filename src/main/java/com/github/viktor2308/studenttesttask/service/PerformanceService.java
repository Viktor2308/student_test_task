package com.github.viktor2308.studenttesttask.service;

import com.github.viktor2308.studenttesttask.Dto.PerformanceRequest;
import com.github.viktor2308.studenttesttask.entity.Performance;
import reactor.core.publisher.Mono;

public interface PerformanceService {
    Mono<Performance> updatePerformance(Long id, PerformanceRequest performanceRequest);
}
