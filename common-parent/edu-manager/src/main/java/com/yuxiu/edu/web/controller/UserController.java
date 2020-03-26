package com.yuxiu.edu.web.controller;

import com.yuxiu.edu.model.User;
import com.yuxiu.edu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("login")
    public String login(){
        System.out.println(".....");
        return "Default";
    }

    @RequestMapping("find")
    public String find(Integer id){
        System.out.println(".....");
        User user = userService.findById(id);
        System.out.println(user);
        return null;
    }

    @RequestMapping("manage")
    public String manage(){
        return "user/manage";
    }

    @RequestMapping("info")
    public ModelAndView info(){
        User user=new User();
        user.setId(1);
        List<User> list=new ArrayList<>();
        list.add(user);
        list.add(user);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("user/info");
        return mv;
    }

    @RequestMapping("edit")
    public String edit(){
        return "user/edit";
    }
}
