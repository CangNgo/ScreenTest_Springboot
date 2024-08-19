package com.screeningTest.screening.dto.request.Teacher;

import com.screeningTest.screening.entity.Contract;
import com.screeningTest.screening.entity.Degree;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherCreationRequest {
    String codeTeacher;
    String lastName;
    String firstName;
    String image;
    double salary;
    Date firstDayOfWork;
    Degree degreeId;
    Contract contractId;
}
