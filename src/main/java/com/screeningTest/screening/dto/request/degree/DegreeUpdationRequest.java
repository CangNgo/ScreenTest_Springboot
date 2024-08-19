package com.screeningTest.screening.dto.request.degree;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DegreeUpdationRequest {
    String degreeName;
}
