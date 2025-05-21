package com.example.student_management_api.controllers;

import com.example.student_management_api.dto.CourseDTO;
import com.example.student_management_api.dto.CourseDeleteDTO;
import com.example.student_management_api.dto.CourseUpdateDTO;
import com.example.student_management_api.exceptions.EntityNotFoundException;
import com.example.student_management_api.exceptions.UniqueEntityException;
import com.example.student_management_api.models.Course;
import com.example.student_management_api.services.CourseServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseServiceImpl courseService;

    @GetMapping()
    public ResponseEntity<List<Course>> getAllCourse() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseDTO courseDTO)  {
        try {
            Course course = courseService.createCourse(courseDTO);
            return new ResponseEntity<>(course, HttpStatusCode.valueOf(200));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        } catch (UniqueEntityException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatusCode.valueOf(409));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CourseUpdateDTO courseUpdateDTO) {
        try {
            Course course = courseService.updateCourse(id, courseUpdateDTO);
            return new ResponseEntity<>(course, HttpStatusCode.valueOf(200));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(404));
        } catch (UniqueEntityException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatusCode.valueOf(409));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id, @RequestBody CourseDeleteDTO courseDeleteDTO)  {
        try {
            String deletedCourse = courseService.deleteCourse(id, courseDeleteDTO);
            return new ResponseEntity<>(deletedCourse, HttpStatusCode.valueOf(200));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(404));
        }
    }
}
