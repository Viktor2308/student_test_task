package com.github.viktor2308.studenttesttask.repository;

import com.github.viktor2308.studenttesttask.entity.Student;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;


public interface StudentRepository extends R2dbcRepository<Student, UUID> {


}
