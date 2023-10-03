package com.github.viktor2308.studenttesttask.service.impl;

import com.github.viktor2308.studenttesttask.entity.Student;
import com.github.viktor2308.studenttesttask.service.StudentService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public Mono<Student> save(Student student) {
        return null;
    }
}
