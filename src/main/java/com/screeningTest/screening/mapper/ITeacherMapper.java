package com.screeningTest.screening.mapper;

import com.screeningTest.screening.dto.request.Teacher.TeacherCreationRequest;
import com.screeningTest.screening.dto.request.Teacher.TeacherUpdationRequest;
import com.screeningTest.screening.dto.response.TeacherResponse;
import com.screeningTest.screening.entity.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface  ITeacherMapper{
    Teacher toTeacher(TeacherCreationRequest request);
    Teacher toTeacher(TeacherUpdationRequest request);
    TeacherResponse toTeacherResponse(Teacher teacher);
    TeacherResponse toTeacherResponse(TeacherCreationRequest teacher);
}
