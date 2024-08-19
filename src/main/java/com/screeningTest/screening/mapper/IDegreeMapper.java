package com.screeningTest.screening.mapper;

import com.screeningTest.screening.dto.request.degree.DegreeCreationRequest;
import com.screeningTest.screening.dto.response.DegreeResponse;
import com.screeningTest.screening.entity.Degree;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IDegreeMapper {
    DegreeResponse toDegree(Degree degree);

    Degree toDegree(DegreeCreationRequest request);
}
