package com.screeningTest.screening.service;

import com.screeningTest.screening.dto.request.Teacher.TeacherCreationRequest;
import com.screeningTest.screening.dto.request.Teacher.TeacherUpdationRequest;
import com.screeningTest.screening.dto.response.TeacherResponse;
import com.screeningTest.screening.entity.Contract;
import com.screeningTest.screening.entity.Degree;
import com.screeningTest.screening.entity.Teacher;
import com.screeningTest.screening.exception.AppException;
import com.screeningTest.screening.exception.ErrorCode;
import com.screeningTest.screening.mapper.ITeacherMapper;
import com.screeningTest.screening.repository.IContractRepository;
import com.screeningTest.screening.repository.IDegreeRepository;
import com.screeningTest.screening.repository.ITeacherRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class TeacherService {
    @Autowired
    ITeacherRepository teacherRepository;
    IDegreeRepository degreeRepository;
    IContractRepository contractRepository;
    ITeacherMapper teacherMapper;

    public TeacherResponse getTeacher(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new AppException(ErrorCode.TEACHER_NOT_FOUND));
        //mapp teacher sang teacherResponse
        TeacherResponse teacherResponse = teacherMapper.toTeacherResponse(teacher);
        teacherResponse.setDegreeName(teacher.getDegreeId().getDegreeName());
        teacherResponse.setContractName(teacher.getContractId().getContractName());
        return teacherResponse;
    }

    public List<TeacherResponse> getTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherResponse> teacherResponses = new ArrayList<TeacherResponse>();
        for (Teacher teacher : teachers) {
            TeacherResponse teacherResponse = teacherMapper.toTeacherResponse(teacher);
            teacherResponse.setDegreeName(teacher.getDegreeId().getDegreeName());
            teacherResponse.setContractName(teacher.getContractId().getContractName());
            teacherResponses.add(teacherResponse);
        }
        return teacherResponses;
    }

    public TeacherResponse createTeacher(TeacherCreationRequest request) {
        if (teacherRepository.existsByCodeTeacher(request.getCodeTeacher()))
            throw new AppException(ErrorCode.TEACHER_EXISTED);

        Contract contract = contractRepository.findById(request.getContractId().getId())
                .orElseThrow(() -> new AppException(ErrorCode.DEGREE_NOT_FOUND));
        Degree degree = degreeRepository.findById(request.getDegreeId().getId())
                .orElseThrow(() -> new AppException(ErrorCode.DEGREE_NOT_FOUND));

        Teacher teacher = teacherMapper.toTeacher(request);
        teacher.setContractId(contract);
        teacher.setDegreeId(degree);
        teacher = teacherRepository.save(teacher);
        TeacherResponse response = teacherMapper.toTeacherResponse(teacher);
        response.setContractName(teacher.getContractId().getContractName());
        response.setDegreeName(teacher.getDegreeId().getDegreeName());
        return response;
    }

    public TeacherResponse updateTeache(String codeTeacher, TeacherUpdationRequest request) {
        if (teacherRepository.existsByCodeTeacher(codeTeacher))
            throw new AppException(ErrorCode.TEACHER_EXISTED);

        Contract contract = contractRepository.findById(request.getContractId().getId())
                .orElseThrow(() -> new AppException(ErrorCode.DEGREE_NOT_FOUND));
        Degree degree = degreeRepository.findById(request.getDegreeId().getId())
                .orElseThrow(() -> new AppException(ErrorCode.DEGREE_NOT_FOUND));

        Teacher teacher =teacherMapper.toTeacher(request);
        teacher.setCodeTeacher(codeTeacher);
        teacher.setContractId(contract);
        teacher.setDegreeId(degree);
        teacher = teacherRepository.save(teacher);
        //trả về kết quả là 2 trường dữ liệu String thay vì object
        TeacherResponse response = teacherMapper.toTeacherResponse(teacher);
        response.setContractName(teacher.getContractId().getContractName());
        response.setDegreeName(teacher.getDegreeId().getDegreeName());

        return response;
    }

    public void deleteTeacher(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    public List<TeacherResponse> findTeacherByDegree(Long degreeId) {
        List<Teacher> teachers = teacherRepository.findTeacherByDegreeId(degreeId);
        List<TeacherResponse> teacherResponses = new ArrayList<TeacherResponse>();
        for (Teacher teacher : teachers) {
            TeacherResponse teacherResponse = teacherMapper.toTeacherResponse(teacher);
            teacherResponse.setDegreeName(teacher.getDegreeId().getDegreeName());
            teacherResponse.setContractName(teacher.getContractId().getContractName());
            teacherResponses.add(teacherResponse);
        }
        return teacherResponses;
    }
}
