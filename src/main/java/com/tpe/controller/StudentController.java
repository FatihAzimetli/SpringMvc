package com.tpe.controller;


import com.tpe.domain.Student;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller//bu class bir controller sınıfıdır.(requestler burada karşılanıp ilgili metodlara eşleştirilecek)
@RequestMapping("/students")//http://localhost:8080/SpringMvc/students/...
//class level:classtaki tüm metodlar için geçerlidir
//method level:sadece ilgili metod için geçerlidir.
public class StudentController {

    @Autowired
    private StudentService service;


    //controllerdan ModelAndView(model+view name) objesi veya String olarak
    // view name döndürülür

    //http://localhost:8080/SpringMvc/students/hi + GET
    //@RequestMapping("/students")
    @GetMapping("/hi")
    public ModelAndView sayHi(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("message","Hi;");
        mav.addObject("messagebody","I'm a Student Management System");
        mav.setViewName("hi");
        return mav;
    }

    //view resolver:/WEB_INF/views/hi.jsp isimli sayfayı bulur ve mav objesi içindeki
    //model(data) ı sayfa içine bind eder(yerleştirir.)


    //addStudent:http://localhost:8080/SpringMvc/students/new + GET
    @GetMapping("/new")
    public String sendForm(@ModelAttribute("student") Student student){

        return "studentForm";
    }

    //studentFormdaki ismi student olan modelAttribute ile kullanıcının
    // inputları student içine yerleştirilir.
    //@ModelAttribute("student") studentFormdaki student modelının
    //controller katmanına aktarılmasını sağlar.


    //student kaydedildiğinde tüm studentlar listelensin.
    //http://localhost:8080/SpringMvc/students/saveStudent + POST
    @PostMapping("/saveStudent")
    public String createStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult){

        //bindingResult validasyon sırasında oluşan hataları saklar,
        //hata varsa formu göster diyoruz ki  hata mesajlarını formda görüp
        //yeni değer girilebilsin.
        if (bindingResult.hasErrors()){
            return "studentForm";
        }

        //hata yoksa kaydetme ile devam edecek
        service.saveStudent(student);

        return "redirect:/students";//http://localhost:8080/SpringMvc/students
    }

    //listStudent
    //http://localhost:8080/SpringMvc/students + GET
    @GetMapping
    public ModelAndView listAllStudents(){

        List<Student> allStudent =service.getAllStudent();

        ModelAndView mav=new ModelAndView();
        mav.addObject("studentList",allStudent);
        mav.setViewName("students");
        return mav;
    }

    //update
    //http://localhost:8080/SpringMvc/students/update?id=1 + GET
    @GetMapping("/update")
    public ModelAndView showForm(@RequestParam("id") Long identity ){

        Student foundStudent=service.findStudentById(identity);

        ModelAndView mav=new ModelAndView();
        mav.addObject("student",foundStudent);
        mav.setViewName("studentForm");

        return mav;
    }


    //delete:silme işleminden sonra tüm studentlar gösterilsin
    //http://localhost:8080/SpringMvc/students/delete/1 + GET
    @GetMapping("/delete/{stdid}")
    public String deleteStudent(@PathVariable("stdid") Long identity){

        service.deleteStudent(identity);

        return "redirect:/students";
    }

    //redirect:Spring Mvc tarafından gelen request "redirect:/path" ile
    //farklı bir requeste(/path url ile) yeniden yönlendirilir.


    //@ExceptionHandler:try-catch bloğunun mantığıyla çalışır.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleException(Exception ex){
        ModelAndView mav=new ModelAndView();
        mav.addObject("message",ex.getMessage());
        mav.setViewName("notFound");
        return mav;
    }









}
