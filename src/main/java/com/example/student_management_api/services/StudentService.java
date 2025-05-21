package com.example.student_management_api.services;

import com.example.student_management_api.dto.StudentDTO;
import com.example.student_management_api.exceptions.EntityNotFoundException;
import com.example.student_management_api.models.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudents();

    public Student getStudentById(Long id) throws EntityNotFoundException;

    public Student createStudent(StudentDTO studentDTO) throws EntityNotFoundException;

    public Student updateStudent(Long id, StudentDTO updatedStudentDTO) throws EntityNotFoundException;

    public String deleteStudent(Long id) throws EntityNotFoundException;
}
