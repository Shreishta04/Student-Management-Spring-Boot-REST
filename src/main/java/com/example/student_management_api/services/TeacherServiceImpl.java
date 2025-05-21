package com.example.student_management_api.services;


import com.example.student_management_api.dto.TeacherDTO;
import com.example.student_management_api.exceptions.EntityNotFoundException;
import com.example.student_management_api.models.Teacher;
import com.example.student_management_api.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher createTeacher(TeacherDTO teacherDTO) throws EntityNotFoundException {
        Teacher teacher = new Teacher();
        teacher.setName(teacherDTO.getName());
        teacher.setDept(teacherDTO.getDept());
        teacher.setSalary(teacherDTO.getSalary());
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Long id, TeacherDTO updatedTeacherDTO) throws EntityNotFoundException {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Teacher with ID: " + id + " not found!"));
        teacher.setName(updatedTeacherDTO.getName());
        teacher.setDept(updatedTeacherDTO.getDept());
        teacher.setSalary(updatedTeacherDTO.getSalary());
        return teacherRepository.save(teacher);
    }

    public String deleteTeacher(Long id) throws EntityNotFoundException {
        teacherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Teacher with ID: " + id + " not found!"));
        teacherRepository.deleteById(id);
        return "Teacher deleted.";
    }
}
