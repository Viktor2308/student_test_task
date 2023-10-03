package com.github.viktor2308.studenttesttask.service.impl;

import com.github.viktor2308.studenttesttask.entity.Student;
import com.github.viktor2308.studenttesttask.exception.StudentNotFoundException;
import com.github.viktor2308.studenttesttask.repository.StudentRepository;
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
    public Mono<Student> update(UUID id, Student student) {
        return studentRepository.findById(id)
                .flatMap(updatedStudent -> {
                            if (updatedStudent == null) {
                                return Mono.error(new StudentNotFoundException("Student with id" + id + ", not found"));
                            }
                            updatedStudent.setBirth(student.getBirth());
                            updatedStudent.setFullName(student.getFullName());
                            updatedStudent.setPerformance(student.getPerformance());
                            return studentRepository.save(updatedStudent);
                        }
                );
    }

    @Transactional
    @Override
    public Mono<Void> deleteStudentById(UUID id) {
        return studentRepository.findById(id).flatMap( student -> {
                    if (student == null){
                        return Mono.error(new StudentNotFoundException("Student with id" + id + ", not found"));
                    }
                    return studentRepository.deleteById(id);
                }
        );
    }
}
