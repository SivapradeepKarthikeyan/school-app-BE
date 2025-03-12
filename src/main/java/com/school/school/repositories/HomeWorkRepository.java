package com.school.school.repositories;

import com.school.school.entities.HomeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeWorkRepository extends JpaRepository<HomeWork, String> {

    @Query("SELECT h FROM HomeWork h WHERE h.date = :date AND h.className = :className AND h.school = :school")
    List<HomeWork> findByDateAndClassNameAndSchool(
            @Param("date") String date,
            @Param("className") String className,
            @Param("school") String school
    );

}