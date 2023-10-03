package com.github.viktor2308.studenttesttask.mapper;

import com.github.viktor2308.studenttesttask.Dto.NewStudentRequest;
import com.github.viktor2308.studenttesttask.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target ="studentId", ignore = true)
    @Mapping(target ="performance", ignore = true)
    Student toStudent(NewStudentRequest newStudentRequest);
}
