package com.screeningTest.screening.repository;

import com.screeningTest.screening.dto.response.TeacherResponse;
import com.screeningTest.screening.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface ITeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByCodeTeacher(String codeTeacher);
    @Query("select t from Teacher  t where t.codeTeacher =:codeTeacher")
    Teacher findByCodeTeacher(String codeTeacher);
    @Query("select t from Teacher  t where t.degreeId.id = :degreeid")
    List<Teacher> findTeacherByDegreeId(Long degreeid);
}
