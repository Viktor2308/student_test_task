package com.github.viktor2308.studenttesttask.service.impl;

import com.github.viktor2308.studenttesttask.Dto.StudentDto;
import com.github.viktor2308.studenttesttask.entity.Student;
import com.github.viktor2308.studenttesttask.exception.PerformanceNotFoundException;
import com.github.viktor2308.studenttesttask.exception.StudentNotFoundException;
import com.github.viktor2308.studenttesttask.repository.StudentRepository;
import com.github.viktor2308.studenttesttask.service.PerformanceService;
import com.github.viktor2308.studenttesttask.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PerformanceService performanceService;

    @Override
    public Mono<Student> save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Mono<Student> findById(UUID id) {
        return studentRepository.findById(id);
    }

    @Override
    public Flux<Student> findAll() {
        return studentRepository.findAll();
    }

    @Transactional
    @Override
    public Mono<Student> update(UUID id, StudentDto studentDto) {
        return studentRepository.findById(id)
                .switchIfEmpty(Mono.error(new StudentNotFoundException("Student with id" + id + ", not found")))
                .then(performanceService.findById(studentDto.getPerformanceId()))
                .switchIfEmpty(Mono.error(new PerformanceNotFoundException("Student performance not found")))
                .flatMap(performance ->
                        studentRepository.save(
                                Student.builder()
                                        .studentId(id)
                                        .fullName(studentDto.getFullName())
                                        .birth(studentDto.getBirth())
                                        .performance(performance)
                                        .build()
                        )
                );
    }


    @Transactional
    @Override
    public Mono<Void> deleteStudentById(UUID id) {
        return studentRepository.findById(id)
                .switchIfEmpty(Mono.error(new StudentNotFoundException("Student with id" + id + ", not found")))
                .then(studentRepository.deleteById(id));
    }
}
