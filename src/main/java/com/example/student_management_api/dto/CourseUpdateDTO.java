package com.example.student_management_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CourseUpdateDTO {
    @NotNull(message = "Teacher Id cannot be blank.")
    private Long teacherId;

    @NotBlank(message = "Course name cannot be blank.")
    private String courseName;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
