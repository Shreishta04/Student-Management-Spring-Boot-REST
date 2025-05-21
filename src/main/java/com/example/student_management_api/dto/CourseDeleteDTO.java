package com.example.student_management_api.dto;

import jakarta.validation.constraints.NotNull;

public class CourseDeleteDTO {

    @NotNull(message = "Teacher Id cannot be blank.")
    private Long teacherId;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

}
