package com.example.student_management_api.exceptions;

public class UniqueEntityException extends Exception {
    public UniqueEntityException(String message){
        super(message);
    }
}
