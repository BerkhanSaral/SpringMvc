package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.StudentNotFoundException;
import com.tpe.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {
    @Autowired
    IStudentRepository studentRepository;

    public List<Student> listAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public void addOrUpdateStudent(Student student) {
        studentRepository.saveOrUpdate(student);
    }

    @Override
    public Student findStudentById(Long id) {
        Student foundStudent = studentRepository.findById(id).
                orElseThrow(() -> new StudentNotFoundException
                        ("Student not fount with id : " + id));
        return foundStudent;
    }

    @Override
    public void deleteStudentById(Long id) {
        Student foundStudent=findStudentById(id);
        studentRepository.delete(foundStudent);
    }
}