package com.school.school.repositories;

import com.school.school.entities.Achievements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementsRepository extends JpaRepository<Achievements,String> {
    @Query("SELECT a FROM Achievements a WHERE a.student.id = :studentId ORDER BY a.achievementDate DESC")
    List<Achievements> findByStudentId(@Param("studentId") String studentId);
}
