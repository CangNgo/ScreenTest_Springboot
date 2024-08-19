package com.screeningTest.screening.dto.request.Teacher;

import com.screeningTest.screening.entity.Contract;
import com.screeningTest.screening.entity.Degree;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherUpdationRequest {
    String lastName;
    String firstName;
    String image;
    double salary;
    Date firstDayOfWork;
    @ManyToOne
    @JoinColumn(name = "degreeId")
    Degree degreeId;
    @ManyToOne
    @JoinColumn(name = "contractId")
    Contract contractId;
}
