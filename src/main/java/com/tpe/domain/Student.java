package com.tpe.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "t_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "lütfen geçerli bir değer giriniz")
    @Column(nullable = false)//
    private String firstName;

    @NotEmpty(message = "lütfen geçerli bir değer giriniz")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "lütfen geçerli bir değer giriniz")
    @Column(nullable = false)
    private Double grade;

    private LocalDateTime createDate=LocalDateTime.now();

    //getter-setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }


//    public void setCreateDate(LocalDateTime createDate) {
//        this.createDate = createDate;
//    }

    public String getCreateDate() {
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd.MM.yy hh:mm");
        return createDate.format(dtf);
    }
}