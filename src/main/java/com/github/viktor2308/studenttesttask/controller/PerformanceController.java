package com.github.viktor2308.studenttesttask.controller;

import com.github.viktor2308.studenttesttask.Dto.PerformanceDto;
import com.github.viktor2308.studenttesttask.service.PerformanceService;
import com.github.viktor2308.studenttesttask.util.LoggingUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/performance")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Performance API", description = "API for correct performance students")
public class PerformanceController {

    private final PerformanceService performanceService;
    @Operation(summary = "Update performance", responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> updatePerformance(@PathVariable("id") Long id,
                                                    @RequestBody @Valid PerformanceDto performanceDto) {
        return Mono.empty()
                .doOnEach(LoggingUtils.logOnComplete(x -> log.warn("Before updating performance")))
                .then(performanceService.updatePerformance(id, performanceDto))
                .doOnEach(LoggingUtils.logOnComplete(x -> log.warn("Performance updated")))
                .map(request -> ResponseEntity.status(HttpStatus.OK).build());
    }
}