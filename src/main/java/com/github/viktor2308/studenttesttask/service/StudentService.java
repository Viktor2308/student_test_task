package com.github.viktor2308.studenttesttask.service;

import com.github.viktor2308.studenttesttask.entity.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface StudentService {
    Mono<Student> save(Student student);

    Mono<Student> findById(UUID id);

    Flux<Student> findAll();

    Mono<Student> update(UUID id, Student student);

    Mono<Void> deleteStudentById(UUID id);
}
