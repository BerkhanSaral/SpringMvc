package com.tpe.service;


import com.tpe.domain.Student;

import java.util.List;

public interface IStudentService {//Create + Update --read -- delete
    List<Student> listAllStudent();

    void addOrUpdateStudent(Student student);

    Student findStudentById(Long id);

    void deleteStudentById(Long id);

}