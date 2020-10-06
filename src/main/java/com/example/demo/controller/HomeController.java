package com.example.demo.controller;

import com.example.demo.model.Blog;
import com.example.demo.model.Users;
import com.example.demo.service.Blog.BlogService;
import com.example.demo.service.Users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private UsersService usersService;

    @GetMapping("")
    public ModelAndView home(){
        Iterable<Blog> blogs = blogService.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("login")
    public ModelAndView usersHome(){
        Iterable<Users> users = usersService.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("users", users);
        return modelAndView;
    }
}
