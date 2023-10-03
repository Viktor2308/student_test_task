package com.github.viktor2308.studenttesttask.service.impl;

import com.github.viktor2308.studenttesttask.Dto.PerformanceRequest;
import com.github.viktor2308.studenttesttask.entity.Performance;
import com.github.viktor2308.studenttesttask.exception.PerformanceNotFoundException;
import com.github.viktor2308.studenttesttask.repository.PerformanceRepository;
import com.github.viktor2308.studenttesttask.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceRepository performanceRepository;

    @Transactional
    @Override
    public Mono<Performance> updatePerformance(Long id, PerformanceRequest performanceRequest) {
        return performanceRepository.findById(id)
                .flatMap(updatedPerformance -> {
                            if (updatedPerformance == null) {
                                return Mono.error(new PerformanceNotFoundException("Performance with id" + id + ", not found"));
                            }
                            updatedPerformance.setText(performanceRequest.getText());
                            return performanceRepository.save(updatedPerformance);
                        }
                );
    }
}

