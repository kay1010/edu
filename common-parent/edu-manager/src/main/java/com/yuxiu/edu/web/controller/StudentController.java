package com.yuxiu.edu.web.controller;

import com.yuxiu.edu.model.Student;
import com.yuxiu.edu.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private  IStudentService studentService;




    @RequestMapping("find")
    public String find(Integer id){
        System.out.println(".....");
        Student user = studentService.findById(id);
        System.out.println(user);
        return null;
    }

    @RequestMapping("manage")
    public String manage(){
        return "student/manage";
    }

    @RequestMapping("info")
    public String info(){
        return "student/info";
    }

    @RequestMapping("edit")
    public String edit(){
        return "student/edit";
    }
}
