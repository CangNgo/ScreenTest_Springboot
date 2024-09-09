package com.screeningTest.screening.controller;

import com.nimbusds.jose.proc.SecurityContext;
import com.screeningTest.screening.dto.request.ApiResponse;
import com.screeningTest.screening.dto.request.Teacher.TeacherCreationRequest;
import com.screeningTest.screening.dto.request.Teacher.TeacherUpdationRequest;
import com.screeningTest.screening.dto.response.TeacherResponse;
import com.screeningTest.screening.entity.Teacher;
import com.screeningTest.screening.service.TeacherService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "http://localhost:3000/")
public class TeacherController {
    TeacherService teacherService;

    @GetMapping("/finddegree/{degreeId}")
    ApiResponse<List<TeacherResponse>> getTeacherByDegreeId(@PathVariable Long degreeId) {

        ApiResponse<List<TeacherResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(teacherService.findTeacherByDegree(degreeId));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<TeacherResponse>> getAllTeachers() {

        //xuất thông tin role login
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(" User name : {} " , authentication.getName());
        log.info(" User id : {} " , authentication.getAuthorities().iterator().next().getAuthority());

        ApiResponse<List<TeacherResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(teacherService.getTeachers());
        return apiResponse;
    }

    @PostMapping
    ApiResponse<TeacherResponse> createTeacher(@RequestBody @Valid  TeacherCreationRequest teacher) {

        ApiResponse<TeacherResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult( teacherService.createTeacher(teacher) );

       return apiResponse;
    }

    @PutMapping("/{teachercode}")
    ApiResponse<TeacherResponse> updateTeacher(@PathVariable("teachercode") String teacherCode,  @RequestBody @Valid TeacherUpdationRequest request) {

        ApiResponse<TeacherResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult( teacherService.updateTeache(teacherCode,request ) );

        return apiResponse;
    }

    @GetMapping("/{teacherId}")
    ApiResponse<TeacherResponse> getTeacher( @PathVariable("teacherId") Long teacherId) {

        ApiResponse<TeacherResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult( teacherService.getTeacher(teacherId));
        return apiResponse;
    }

    @DeleteMapping("/{teacherId}")
    String deleteTeacher(@PathVariable("teacherId") Long teacherId) {
        teacherService.deleteTeacher(teacherId);
        return "Teacher has deleted";
    }
}
