package com.example.student_management_api.services;

import com.example.student_management_api.dto.TeacherDTO;
import com.example.student_management_api.exceptions.EntityNotFoundException;
import com.example.student_management_api.models.Teacher;

import java.util.List;

public interface TeacherService {
    public List<Teacher> getAllTeachers();

    public Teacher createTeacher(TeacherDTO teacherDTO) throws EntityNotFoundException;

    public Teacher updateTeacher(Long id, TeacherDTO updatedTeacherDTO) throws EntityNotFoundException;

    public String deleteTeacher(Long id) throws EntityNotFoundException;
}
