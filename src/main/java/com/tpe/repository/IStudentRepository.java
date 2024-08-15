package com.tpe.repository;

import com.tpe.domain.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository {//CRUD

    List<Student>findAll();

    void saveOrUpdate(Student student);

    void delete(Student student);

    Optional<Student>findById(Long id);//null pointer exception almamızı engeller
}