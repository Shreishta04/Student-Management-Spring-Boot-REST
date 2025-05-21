package com.example.student_management_api.controllers;

import com.example.student_management_api.dto.CourseRegisterDTO;
import com.example.student_management_api.dto.StudentDTO;
import com.example.student_management_api.exceptions.EntityNotFoundException;
import com.example.student_management_api.exceptions.UniqueEntityException;
import com.example.student_management_api.models.Student;
import com.example.student_management_api.services.CourseServiceImpl;
import com.example.student_management_api.services.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private CourseServiceImpl courseService;

    @GetMapping()
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        try {
            Student student = studentService.getStudentById(id);
            return new ResponseEntity<>(student, HttpStatusCode.valueOf(200));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentDTO studentDTO){
        try {
            Student student = studentService.createStudent(studentDTO);
            return new ResponseEntity<>(student, HttpStatusCode.valueOf(200));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO updatedStudentDTO) {
        try {
            Student student = studentService.updateStudent(id, updatedStudentDTO);
            return new ResponseEntity<>(student, HttpStatusCode.valueOf(200));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(404));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            String deletedStudent = studentService.deleteStudent(id);
            return new ResponseEntity<>(deletedStudent, HttpStatusCode.valueOf(200));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(404));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCourse(@RequestBody CourseRegisterDTO courseRegisterDTO) {
        try {
            String registered = courseService.registerCourse(courseRegisterDTO);
            return new ResponseEntity<>(registered, HttpStatusCode.valueOf(200));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(404));
        } catch (UniqueEntityException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatusCode.valueOf(409));
        }
    }

}
