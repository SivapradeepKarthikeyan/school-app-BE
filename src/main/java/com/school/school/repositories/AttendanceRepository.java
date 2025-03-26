package com.school.school.repositories;

import com.school.school.entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance,String> {
    // Find attendance records by student ID
    @Query("SELECT a FROM Attendance a WHERE a.student.id = :studentId ORDER BY a.date DESC")
    List<Attendance> findByStudentId(@Param("studentId") String studentId);
}
