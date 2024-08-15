package com.tpe.exception;

public class StudentNotFoundExeption extends RuntimeException {
    public StudentNotFoundExeption(String s) {
        super(s);
    }
}
