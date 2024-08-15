package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
//gelen request(istek ve talep)leri bu class sayesinde karsılıyoruz ve ilgili methodlar ile mapleyerek cevaplıyoruz
@RequestMapping("/students")//http:localhost:8080/B256SpringMVC/students/....
public class StudentController {//gelen request(istek ve talep)leri bu class sayesinde karsılıyoruz ve ilgili methodlar ile mapleyerek cevaplıyoruz //http:localhost:8080/B256SpringMVC/students/....

    @Autowired
    private IStudentService service;

    //http:localhost:8080/B256SpringMVC/students/hi + get
    @GetMapping("/hi")
    public ModelAndView sayHi() {//response gonderiyoruz
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Selam,");
        mav.addObject("messagebody", "Ben Öğrenci Yönetim Sistemiyim");
        mav.setViewName("hi");
        return mav;
    }
    //view resolver /WEB-INF/views/hi.jsp

    //1-tüm öğrencileri listeleme
    //http:localhost:8080/B256SpringMVC/students
    @GetMapping
    public ModelAndView getStudents() {//response
        ModelAndView mav = new ModelAndView();
        List<Student> listStudent = service.listAllStudent();
        mav.addObject("students", listStudent);//bilgiyi db'den alıcaz repo'ya baglanmamız lazım repo'ya baglanabilmek icin service classına ihtiyacim var
        mav.setViewName("students");
        return mav;
    }

    //2-öğrenciyi kaydetme
    //http:localhost:8080/B256SpringMVC/students/form    + post
    @GetMapping("/form")//request alıyor
    public String sendForm(@ModelAttribute("student") Student student) {
        return "studentForm";
    }
    //ModelAttribute anatasyonu view katmani ile controller arasında baglantı kurup bu baglantı ile model transferini saglar


    //2-a db'e gondermedik yani db kaydetme işlemi

    //http:localhost:8080/B256SpringMVC/students/saveStudent
    //http:localhost:8080/B256SpringMVC/students/(geri donmeyi saglar)redirect:/students

    @PostMapping("/saveStudent")
    public String addStudents(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "studentForm";
        }
        service.addOrUpdateStudent(student);
        return "redirect:/students";
    }


    @GetMapping("/update")
    public ModelAndView sendFormForUpdate(@RequestParam("id") Long id) {

        Student foundStudent = service.findStudentById(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("student",foundStudent);
        mav.setViewName("studentForm");
        return mav;
    }

}
