package com.school.school.repositories;

import com.school.school.entities.Attendance;
import com.school.school.entities.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,String> {
    // Find leave records by student ID
    @Query("SELECT a FROM LeaveRequest a WHERE a.student.id = :studentId ORDER BY a.studentLeaveRequestDate DESC")
    List<LeaveRequest> findByStudentId(@Param("studentId") String studentId);
}
