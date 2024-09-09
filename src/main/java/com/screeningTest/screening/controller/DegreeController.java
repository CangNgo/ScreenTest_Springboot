package com.screeningTest.screening.controller;

import com.screeningTest.screening.dto.request.ApiResponse;
import com.screeningTest.screening.dto.request.degree.DegreeCreationRequest;
import com.screeningTest.screening.dto.request.degree.DegreeUpdationRequest;
import com.screeningTest.screening.dto.response.DegreeResponse;
import com.screeningTest.screening.entity.Degree;
import com.screeningTest.screening.mapper.IDegreeMapper;
import com.screeningTest.screening.service.DegreeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/degree")
@CrossOrigin(origins = "http://localhost:3000/")
public class DegreeController {
    private DegreeService degreeService;

    @GetMapping
    ApiResponse<DegreeResponse> getAllDegrees() {
        ApiResponse apiResponse = new ApiResponse();
        List<Degree> listDegreeResponse = degreeService.getDegrees();
        apiResponse.setResult(listDegreeResponse);
        return apiResponse;
    }

    @GetMapping("/{degreeId}")
    ApiResponse<DegreeResponse> getDegreeById(@PathVariable Long degreeId) {
        ApiResponse<DegreeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(degreeService.getDegree(degreeId));
        return apiResponse;
    }

    @PostMapping
    ApiResponse<DegreeResponse> createDegree(@RequestBody DegreeCreationRequest degree) {
        ApiResponse<DegreeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(degreeService.createDegree(degree));
        return apiResponse;
    }

    @PutMapping("/{degreeId}")
    ApiResponse<DegreeResponse> updationDegree(@PathVariable Long degreeId, @RequestBody DegreeUpdationRequest degree) {
        ApiResponse<DegreeResponse> apiResponse = new ApiResponse();
        apiResponse.setResult(degreeService.updateDegree(degreeId, degree));
        return apiResponse;
    }

    @DeleteMapping("/{degreeId}")
    ApiResponse<DegreeResponse> deleteDegree(@PathVariable Long degreeId) {
        ApiResponse<DegreeResponse> apiResponse = new ApiResponse<>();

        degreeService.deleteDegree(degreeId);

        apiResponse.setMessage("Degree deleted");
        return apiResponse;
    }




}
