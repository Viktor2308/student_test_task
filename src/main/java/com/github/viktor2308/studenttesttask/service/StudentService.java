package com.github.viktor2308.studenttesttask.service;

import com.github.viktor2308.studenttesttask.entity.Student;
import reactor.core.publisher.Mono;

public interface StudentService {
    Mono<Student> save(Student student);
}
