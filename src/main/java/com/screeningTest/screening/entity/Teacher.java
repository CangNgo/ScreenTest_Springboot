package com.screeningTest.screening.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeTeacher;
    private String lastName;
    private String firstName;
    private String image;
    private double salary;
    private Date firstDayOfWork;
    @ManyToOne
    @JoinColumn(name = "degreeId")
    private Degree degreeId;
    @ManyToOne
    @JoinColumn(name = "contractId")
    private Contract contractId;

}
