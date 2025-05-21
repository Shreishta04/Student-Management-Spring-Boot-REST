package com.example.student_management_api.services;

import com.example.student_management_api.dto.StudentDTO;
import com.example.student_management_api.exceptions.EntityNotFoundException;
import com.example.student_management_api.models.Student;
import com.example.student_management_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    public StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) throws EntityNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student with ID: " + id + " not found!"));
    }

    public Student createStudent(StudentDTO studentDTO) throws EntityNotFoundException {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setMark1(studentDTO.getMark1());
        student.setMark2(studentDTO.getMark2());
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, StudentDTO updatedStudentDTO) throws EntityNotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student with ID: " + id + " not found!"));
        student.setName(updatedStudentDTO.getName());
        student.setMark1(updatedStudentDTO.getMark1());
        student.setMark2(updatedStudentDTO.getMark2());
        return studentRepository.save(student);
    }

    public String deleteStudent(Long id) throws EntityNotFoundException {
        studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student with ID: " + id + " not found!"));
        studentRepository.deleteById(id);
        return "Student deleted.";
    }
}
