package com.screeningTest.screening.repository;

import com.screeningTest.screening.entity.Degree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDegreeRepository extends JpaRepository<Degree, Long> {
    boolean existsByDegreeName(String degreeName);
}
