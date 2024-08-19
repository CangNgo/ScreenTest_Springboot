package com.screeningTest.screening.dto.response;

import com.screeningTest.screening.entity.Contract;
import com.screeningTest.screening.entity.Degree;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherResponse {
    Long id;
    String codeTeacher;
    String lastName;
    String firstName;
    String image;
    double salary;
    Date firstDayOfWork;
    String degreeName;
    String contractName;
}
