package com.github.viktor2308.studenttesttask.controller;

import com.github.viktor2308.studenttesttask.Dto.StudentRequest;
import com.github.viktor2308.studenttesttask.entity.Student;
import com.github.viktor2308.studenttesttask.mapper.StudentMapper;
import com.github.viktor2308.studenttesttask.service.StudentService;
import com.github.viktor2308.studenttesttask.util.LoggingUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Student API", description = "API for CRUD operation with student")
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @Operation(summary = "Create new student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful Operation", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "400", description = "Error", content = @Content)})
    @PostMapping
    public Mono<ResponseEntity<Void>> createStudent(@RequestBody @Valid StudentRequest student,
                                                   ServerHttpRequest serverHttpRequest) {
        return Mono.empty()
                .doOnEach(LoggingUtils.logOnComplete(x -> log.warn("Request for create student")))
                .then(studentService.save(studentMapper.toStudent(student)))
                .doOnEach(LoggingUtils.logOnComplete(x -> log.warn("Student created")))
                .map(createdStudent -> ResponseEntity.created(
                        URI.create(serverHttpRequest.getPath().toString().concat("/")
                                .concat(Objects.requireNonNull(createdStudent.getStudentId()).toString()))).build());
    }

}
