package com.github.viktor2308.studenttesttask.controller;

import com.github.viktor2308.studenttesttask.Dto.StudentRequest;
import com.github.viktor2308.studenttesttask.entity.Student;
import com.github.viktor2308.studenttesttask.mapper.StudentMapper;
import com.github.viktor2308.studenttesttask.service.StudentService;
import com.github.viktor2308.studenttesttask.util.LoggingUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

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
            @ApiResponse(responseCode = "201", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Error")})
    @PostMapping
    public Mono<ResponseEntity<Void>> createStudent(@RequestBody @Valid StudentRequest student) {
        return Mono.empty()
                .doOnEach(LoggingUtils.logOnComplete(x -> log.warn("Request for create student")))
                .then(studentService.save(studentMapper.toStudent(student)))
                .doOnEach(LoggingUtils.logOnComplete(x -> log.warn("Student created")))
                .map(request -> ResponseEntity.status(HttpStatus.CREATED).build());
    }

    @Operation(summary = "Get student by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @GetMapping("/{id}")
    public Mono<Student> getStudentById(@PathVariable UUID id) {
        return Mono.empty()
                .doOnEach(LoggingUtils.logOnComplete(x -> log.warn("Before student obtained")))
                .then(studentService.findById(id))
                .doOnEach(LoggingUtils.logOnComplete(x -> log.warn("Student obtained")));
    }

    @Operation(summary = "Get all student", responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Student.class)))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @GetMapping("/all")
    public Flux<Student> getAllStudent() {
        return Flux.empty()
                .doOnEach(LoggingUtils.logOnComplete(x -> log.warn("Before all student obtained")))
                .thenMany(studentService.findAll())
                .doOnEach(LoggingUtils.logOnComplete(x -> log.warn("All student obtained")));
    }

    @Operation(summary = "Update student", responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> updateStudent(@PathVariable("id") UUID id,
                                                    @RequestBody Student student) {
        return Mono.empty()
                .doOnEach(LoggingUtils.logOnComplete(x -> log.warn("Before updating student")))
                .then(studentService.update(id, student))
                .doOnEach(LoggingUtils.logOnComplete(x -> log.warn("Student updated")))
                .map(request -> ResponseEntity.status(HttpStatus.OK).build());
    }

    @Operation(summary = "Delete student", responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteStudent(@PathVariable("id") UUID id) {
        return Mono.empty()
                .doOnEach(LoggingUtils.logOnComplete(x -> log.warn("Before deleting student")))
                .then(studentService.deleteStudentById(id))
                .doOnEach(LoggingUtils.logOnComplete(x -> log.warn("Student deleted")))
                .then(Mono.fromCallable(() -> ResponseEntity.status(HttpStatus.OK).build()));
    }

}
