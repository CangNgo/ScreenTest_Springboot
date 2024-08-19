package com.screeningTest.screening.service;

import com.screeningTest.screening.dto.request.ApiResponse;
import com.screeningTest.screening.dto.request.degree.DegreeCreationRequest;
import com.screeningTest.screening.dto.request.degree.DegreeUpdationRequest;
import com.screeningTest.screening.dto.response.DegreeResponse;
import com.screeningTest.screening.entity.Degree;
import com.screeningTest.screening.exception.AppException;
import com.screeningTest.screening.exception.ErrorCode;
import com.screeningTest.screening.mapper.IDegreeMapper;
import com.screeningTest.screening.repository.IDegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DegreeService {
    @Autowired
    private IDegreeRepository degreeRepository;
    @Autowired
    private IDegreeMapper degreeMapper;

    //tạo
    public DegreeResponse createDegree(DegreeCreationRequest request) {
        if (degreeRepository.existsByDegreeName(request.getDegreeName())) {
            throw new AppException(ErrorCode.DEGREE_EXISTED);
        }
        Degree degree = degreeMapper.toDegree(request);
        degree = degreeRepository.save(degree);
        return degreeMapper.toDegree(degree);
    }

    public DegreeResponse updateDegree(Long degreeId, DegreeUpdationRequest degreeRequest) {
        //kiểm tra xem có tồn tại teacher này không
        Degree degree = degreeRepository.findById(degreeId).orElseThrow
                (() -> new AppException(ErrorCode.DEGREE_NOT_FOUND));
        degree.setDegreeName(degreeRequest.getDegreeName());
        degreeRepository.save(degree);
        return degreeMapper.toDegree(degree);
    }

    public void deleteDegree(Long degreeId) {
        degreeRepository.deleteById(degreeId);
    }

    public List<Degree> getDegrees() {
        return degreeRepository.findAll();
    }

    public DegreeResponse getDegree(Long degreeId) {
        return degreeMapper.toDegree(degreeRepository.findById(degreeId).orElseThrow(() -> new AppException(ErrorCode.DEGREE_NOT_FOUND)));
    }
}
