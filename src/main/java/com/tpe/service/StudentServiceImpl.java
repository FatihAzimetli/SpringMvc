package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.ResourceNotFoundException;
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

        Student student=repository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Student not found by id: "+id));

        //optional classı null değer dönme ihtimaline karşılık tetikte olmamızı sağlar.
        //findById student bulunursa optional içinde studentı getirir
        //ancak student bulunamazsa boş bir optional objesi döner
        //optionalın içi boşsa orElseThrow metodu exception fırlatmamızı sağlar.

        return student;
    }


    @Override
    public void deleteStudent(Long id) {
        Student student=findStudentById(id);
        //silmeye çalışmadan önce id si verilen student objesini buluyoruz.
        //eğer student bulunamazsa exception fırlatılacak, ve alttaki koda geçmeyecek.
        repository.delete(student);

    }
}
