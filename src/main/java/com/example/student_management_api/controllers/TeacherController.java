package com.example.student_management_api.controllers;

import com.example.student_management_api.dto.TeacherDTO;
import com.example.student_management_api.exceptions.EntityNotFoundException;
import com.example.student_management_api.models.Teacher;
import com.example.student_management_api.services.TeacherServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherServiceImpl teacherService;

    @GetMapping()
    public ResponseEntity<List<Teacher>> getAllTeacher() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTeacher(@Valid @RequestBody TeacherDTO teacherDTO)  {
        try {
            Teacher teacher = teacherService.createTeacher(teacherDTO);
            return new ResponseEntity<>(teacher, HttpStatusCode.valueOf(200));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable Long id, @Valid @RequestBody TeacherDTO updatedTeacherDTO)  {
        try {
            Teacher teacher = teacherService.updateTeacher(id, updatedTeacherDTO);
            return new ResponseEntity<>(teacher, HttpStatusCode.valueOf(200));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(404));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id)  {
        try {
            String deletedTeacher = teacherService.deleteTeacher(id);
            return new ResponseEntity<>(deletedTeacher, HttpStatusCode.valueOf(200));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(404));
        }
    }
}
