package com.example.student_management_api.services;

import com.example.student_management_api.dto.CourseDTO;
import com.example.student_management_api.dto.CourseDeleteDTO;
import com.example.student_management_api.dto.CourseRegisterDTO;
import com.example.student_management_api.dto.CourseUpdateDTO;
import com.example.student_management_api.exceptions.EntityNotFoundException;
import com.example.student_management_api.exceptions.UniqueEntityException;
import com.example.student_management_api.models.Course;
import com.example.student_management_api.models.Student;
import com.example.student_management_api.models.Teacher;
import com.example.student_management_api.repository.CourseRepository;
import com.example.student_management_api.repository.StudentRepository;
import com.example.student_management_api.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course createCourse(CourseDTO courseDTO) throws EntityNotFoundException, UniqueEntityException {
        Course course = new Course();
        Teacher teacher = teacherRepository.findById(courseDTO.getTeacherId()).orElseThrow(() -> new EntityNotFoundException("Teacher with ID: " + courseDTO.getTeacherId() + " not found!"));
        if (teacher.getCourses().contains(course)) {
            throw new UniqueEntityException("Course already exists.");
        }
        course.setCourseName(courseDTO.getCourseName());
        course.setTeacher(teacher);
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, CourseUpdateDTO courseUpdateDTO) throws EntityNotFoundException, UniqueEntityException {
        Course course = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course with ID: " + id + " not found!"));
        Teacher teacher = teacherRepository.findById(courseUpdateDTO.getTeacherId()).orElseThrow(() -> new EntityNotFoundException("Teacher with ID: " + courseUpdateDTO.getTeacherId() + " not found!"));
        if (!course.getTeacher().getId().equals(teacher.getId())) {
            throw new RuntimeException("Teacher not authorised to update course!");
        }
        if (teacher.getCourses().contains(course)) {
            throw new UniqueEntityException("Course already exists.");
        }
        course.setCourseName(courseUpdateDTO.getCourseName());
        return courseRepository.save(course);
    }

    public String deleteCourse(Long id, CourseDeleteDTO courseDeleteDTO) throws EntityNotFoundException {
        Teacher teacher = teacherRepository.findById(courseDeleteDTO.getTeacherId()).orElseThrow(() -> new EntityNotFoundException("Teacher with ID: " + courseDeleteDTO.getTeacherId() + " not found!"));
        Course course = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course with ID: " + id + " not found!"));
        if (!course.getTeacher().getId().equals(teacher.getId())) {
            throw new RuntimeException("Teacher not authorised to update course!");
        }
        courseRepository.deleteById(id);
        return "Course deleted.";
    }

    public String registerCourse(CourseRegisterDTO courseRegisterDTO) throws EntityNotFoundException, UniqueEntityException {
        Student student = studentRepository.findById(courseRegisterDTO.getStudentId()).orElseThrow(() -> new EntityNotFoundException("Student with ID: " + courseRegisterDTO.getStudentId() + " not found!"));
        Course course = courseRepository.findById(courseRegisterDTO.getCourseId()).orElseThrow(() -> new EntityNotFoundException("Course with ID: " + courseRegisterDTO.getCourseId() + " not found!"));
        if (student.getCourseList().contains(course)) {
            throw new UniqueEntityException("Student is already registered for this course!");
        }
        student.getCourseList().add(course);
        course.getStudents().add(student);
        studentRepository.save(student);
        courseRepository.save(course);
        return "Student registered successfully for: " + course.getCourseName();
    }
}
