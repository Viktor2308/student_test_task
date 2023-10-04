package com.github.viktor2308.studenttesttask.controller;

import com.github.viktor2308.studenttesttask.Dto.NewStudentRequest;
import com.github.viktor2308.studenttesttask.Dto.StudentDto;
import com.github.viktor2308.studenttesttask.entity.Student;
import com.github.viktor2308.studenttesttask.exception.StudentNotFoundException;
import com.github.viktor2308.studenttesttask.mapper.StudentMapper;
import com.github.viktor2308.studenttesttask.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "Student API", description = "API for CRUD operation with student")
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @Operation(summary = "Create new student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Error: Bad Request")})
    @PostMapping
    public Mono<ResponseEntity<Void>> createStudent(@RequestBody @Valid NewStudentRequest student) {
        log.debug("Create new student: {}", student.getFullName());
        return studentService.save(studentMapper.toStudent(student))
                .doOnEach(x -> log.info("Create new student {}", student.getFullName()))
                .map(request -> ResponseEntity.status(HttpStatus.CREATED).build());
    }

    @Operation(summary = "Get student by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @GetMapping("/{id}")
    public Mono<Student> getStudentById(@PathVariable @NotNull(message = "id can't be null") UUID id) {
        log.debug("Get student by ID: {}", id);
        return studentService.findById(id)
                .switchIfEmpty(Mono.error(new StudentNotFoundException("Student is not found")));
    }

    @Operation(summary = "Get all student", responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Student.class)))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @GetMapping("/all")
    public Flux<Student> getAllStudent() {
        log.debug("Get all student");
        return studentService.findAll()
                .switchIfEmpty(Flux.error(new StudentNotFoundException("Student is not found")));
    }

    @Operation(summary = "Update student", responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> updateStudent(@PathVariable("id") @NotNull(message = "id can't be null") UUID id,
                                                    @RequestBody @Valid StudentDto studentDto) {
        log.debug("Update student id: {}", id);
        return studentService.update(id, studentDto)
                .doOnEach(x -> log.info("Student with id: {}, updated", id))
                .map(request -> ResponseEntity.status(HttpStatus.OK).build());
    }

    @Operation(summary = "Delete student", responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteStudent(@PathVariable("id") UUID id) {
        log.debug("Delete student id: {}", id);
        return studentService.deleteStudentById(id)
                .doOnEach(x -> log.info("Student with id: {}, deleted", id))
                .then(Mono.just(ResponseEntity.status(HttpStatus.OK).build()));
    }
}
