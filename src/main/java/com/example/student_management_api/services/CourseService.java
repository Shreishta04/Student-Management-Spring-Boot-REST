package com.example.student_management_api.services;

import com.example.student_management_api.dto.CourseDTO;
import com.example.student_management_api.dto.CourseDeleteDTO;
import com.example.student_management_api.dto.CourseRegisterDTO;
import com.example.student_management_api.dto.CourseUpdateDTO;
import com.example.student_management_api.exceptions.EntityNotFoundException;
import com.example.student_management_api.exceptions.UniqueEntityException;
import com.example.student_management_api.models.Course;

import java.util.List;

public interface CourseService {
    public List<Course> getAllCourses();

    public Course createCourse(CourseDTO courseDTO) throws EntityNotFoundException, UniqueEntityException;

    public Course updateCourse(Long id, CourseUpdateDTO courseUpdateDTO) throws EntityNotFoundException, UniqueEntityException;

    public String deleteCourse(Long id, CourseDeleteDTO courseDeleteDTO) throws EntityNotFoundException;

    public String registerCourse(CourseRegisterDTO courseRegisterDTO) throws EntityNotFoundException, UniqueEntityException;
}
