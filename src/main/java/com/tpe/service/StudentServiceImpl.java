package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository repository;


    @Override
    public void saveStudent(Student student) {
        repository.save(student);
    }

    @Override
    public List<Student> getAllStudent() {

        List<Student> studentList=repository.getAll();

//        if (studentList.isEmpty()){
//            throw EmptyListException();
//        }
//Not:listenin boş olması durumunda mesaj verebilmek için jsp sayfası oluşturulmalı.
//projenin böyle bir işlevi yok

        return studentList;
    }

    @Override
    public Student findStudentById(Long id) {
        return null;
    }


    @Override
    public void deleteStudent(Long id) {
        //desim yapilacak
        //gitup 2 gün almamasi benim hatam

    }
}
