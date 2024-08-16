package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.exception.StudentNotFoundException;
import com.tpe.service.IStudentService;
import com.tpe.service.StudentService;
import net.bytebuddy.matcher.StringMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller//gelen request(istek ve talep)leri bu class sayesinde karsılıyoruz ve ilgili methodlar ile mapleyerek cevaplıyoruz
@RequestMapping("/students")//http:localhost:8080/B256SpringMVC/students/....
public class StudentController {//gelen request(istek ve talep)leri bu class sayesinde karsılıyoruz ve ilgili methodlar ile mapleyerek cevaplıyoruz //http:localhost:8080/B256SpringMVC/students/....

    @Autowired
    private IStudentService service;

    //http:localhost:8080/B256SpringMVC/students/hi + get
    @GetMapping("/hi")
    public ModelAndView sayHi(){//response gonderiyoruz
        ModelAndView mav=new ModelAndView();
        mav.addObject("message","Selam,");
        mav.addObject("messagebody","Ben Öğrenci Yönetim Sistemiyim");
        mav.setViewName("hi");
        return mav;
    }
    //view resolver /WEB-INF/views/hi.jsp

    //1-tüm öğrencileri listeleme
    //http:localhost:8080/B256SpringMVC/students
    @GetMapping
    public ModelAndView getStudents(){//response
        ModelAndView mav=new ModelAndView();
        List<Student>listStudent =service.listAllStudent();
        mav.addObject("students",listStudent);//bilgiyi db'den alıcaz repo'ya baglanmamız lazım repo'ya baglanabilmek icin service classına ihtiyacim var
        mav.setViewName("students");
        return mav;
    }

    //2-öğrenciyi kaydetme
    //http:localhost:8080/B256SpringMVC/students/form    + get
    @GetMapping("/form")//request alıyor
    public String sendForm(@ModelAttribute("student")Student student){
        return "studentForm";
    }
    //ModelAttribute anatasyonu view katmani ile controller arasında baglantı kurup bu baglantı ile model transferini saglar
    //işlem sonunda student'in firstname,lastname ve grade degerleri sırasıyla set edilmiş oldu!!!


    //2-a db'e gondermedik yani db kaydetme işlemi

    //http:localhost:8080/B256SpringMVC/students/form
    //http:localhost:8080/B256SpringMVC/students/(geri donmeyi saglar)redirect:/students
    @PostMapping("/saveStudent")
    public String addStudent(@Valid @ModelAttribute("student")Student student, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return  "studentForm";
        }
        service.addOrUpdateStudent(student);
        return "redirect:/students";
    }

    //3-mevcut ogrenciyi güncelleme
    //http:localhost:8080/B256SpringMVC/students/update?id=1(id'si 1 olan) + get
    @GetMapping("/update")
    public ModelAndView sendFormForUpdate(@RequestParam("id")Long id){
        Student foundStudent=service.findStudentById(id);
        ModelAndView mav=new ModelAndView();
        mav.addObject("student",foundStudent);
        mav.setViewName("studentForm");
        return mav;
    }

    //4-mevcut ogrenciyi silme
    //http://localhost:8081/B256SpringMVC/students/delete/1 + get
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id")Long identity){
        service.deleteStudentById(identity);
        return "redirect:/students";
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ModelAndView handlerException(Exception ex){
        ModelAndView mav=new ModelAndView();
        mav.addObject("message",ex.getMessage());
        mav.setViewName("notFound");
        return mav;
    }




}