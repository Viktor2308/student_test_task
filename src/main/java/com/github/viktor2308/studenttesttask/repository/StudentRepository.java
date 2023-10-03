package com.github.viktor2308.studenttesttask.repository;

import com.github.viktor2308.studenttesttask.entity.Student;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.r2dbc.repository.Query;
import reactor.core.publisher.Mono;

import java.util.UUID;


public interface StudentRepository extends R2dbcRepository<Student, UUID> {

    @Modifying
    @Query("DELETE FROM student where id = :id")
    Mono<Integer> deleteStudentById(UUID id);

}
