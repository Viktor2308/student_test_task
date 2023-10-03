package com.github.viktor2308.studenttesttask.service.impl;

import com.github.viktor2308.studenttesttask.Dto.StudentDto;
import com.github.viktor2308.studenttesttask.entity.Performance;
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
                .flatMap(updatedStudent -> {
                            if (updatedStudent == null) {
                                return Mono.error(new StudentNotFoundException("Student with id" + id + ", not found"));
                            }
                            Performance performance = performanceService.findById(studentDto.getPerformance().getPerformanceId()).block();
                            if (performance == null) {
                                return Mono.error(new PerformanceNotFoundException("Student performance not found"));
                            }
                            updatedStudent.setBirth(studentDto.getBirth());
                            updatedStudent.setFullName(studentDto.getFullName());
                            updatedStudent.setPerformance(performance);
                            return studentRepository.save(updatedStudent);
                        }
                );
    }

    @Transactional
    @Override
    public Mono<Void> deleteStudentById(UUID id) {
        return studentRepository.findById(id).flatMap(student -> {
                    if (student == null) {
                        return Mono.error(new StudentNotFoundException("Student with id" + id + ", not found"));
                    }
                    return studentRepository.deleteById(id);
                }
        );
    }
}
