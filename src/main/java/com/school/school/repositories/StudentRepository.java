package com.school.school.repositories;

import com.school.school.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
    Optional<Student> findByStudentEmail(String studentEmail);
}
