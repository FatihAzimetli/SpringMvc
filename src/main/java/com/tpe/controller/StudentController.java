package com.tpe.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller//bu class bir controller sınıfıdır.(requestler burada karşılanıp ilgili metodlara eşleştirilecek)
@RequestMapping("/students")//http://localhost:8080/SpringMvc/students/...
//class level:classtaki tüm metodlar için geçerlidir
//method level:sadece ilgili metod için geçerlidir.
public class StudentController {

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
    //model ı sayfa içine bind eder(yerleştirir.)




}