package com.github.viktor2308.studenttesttask.repository;

import com.github.viktor2308.studenttesttask.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    @Test
    void shouldSetIdOnSave() {
        Student student = Student.builder()
                .birth(LocalDate.of(2002,1,1))
                .fullName("Ivanov Ivan Ivanovich")
                .build();
        Mono<Student> studentMono = repository.save(student);

        StepVerifier
                .create(studentMono)
                .assertNext(stud -> assertNotNull(stud.getStudentId()))
                .expectComplete()
                .verify();
    }
}
