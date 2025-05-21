package com.example.student_management_api.repository;

import com.example.student_management_api.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
