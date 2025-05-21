package com.example.student_management_api.dto;

import jakarta.validation.constraints.*;

public class StudentDTO {

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Mark cannot be blank")
    @Min(value = 0, message = "Mark must be at least 0")
    @Max(value = 100, message = "Mark cannot exceed 100")
    private int mark1;

    @NotNull(message = "Mark cannot be blank")
    @Min(value = 0, message = "Mark must be at least 0")
    @Max(value = 100, message = "Mark cannot exceed 100")
    private int mark2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMark1() {
        return mark1;
    }

    public void setMark1(int mark1) {
        this.mark1 = mark1;
    }

    public int getMark2() {
        return mark2;
    }

    public void setMark2(int mark2) {
        this.mark2 = mark2;
    }
}
