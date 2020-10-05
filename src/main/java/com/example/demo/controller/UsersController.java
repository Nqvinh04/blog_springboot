package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.service.Role.RoleService;
import com.example.demo.service.Users.UsersService;
import com.sun.tracing.dtrace.ModuleAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private RoleService roleService;

    @Value("D:/Users-springboot/src/main/resources/templates/image/user/")
    private String Path;

    @GetMapping("/users")
    public ModelAndView listUser(){
        Iterable<Users> users = usersService.findAll();
        ModelAndView modelAndView = new ModelAndView("users/list");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/users/create")
    public ModelAndView showCreateUser(){
        ModelAndView modelAndView = new ModelAndView("users/create");
        modelAndView.addObject("users", new Users());
        return modelAndView;
    }

    @PostMapping("/users/create")
    public ModelAndView saveUser(@ModelAttribute("users") Users users){
        usersService.save(users);
        ModelAndView modelAndView = new ModelAndView("users/create");
        modelAndView.addObject("users", new Users());
                return modelAndView;
    }

    @GetMapping("/users/edit/{id}")
    public ModelAndView showEditUser(@PathVariable Long id){
        Users users = usersService.findUsersById(id);
        ModelAndView modelAndView = new ModelAndView("users/edit");
        modelAndView.addObject("users",users);
        return modelAndView;
    }

    @PostMapping("/users/edit")
    public ModelAndView updateUser(@ModelAttribute("Users") Users users) throws IOException {
        usersService.save(users);
        ModelAndView modelAndView = new ModelAndView("users/edit");
        modelAndView.addObject("users", new Users());
        return modelAndView;
    }

    @GetMapping("/users/delete/{id}")
    public ModelAndView deleteUser(@PathVariable Long id){
        Users users = usersService.findUsersById(id);
        usersService.remove(users.getUserId());
        ModelAndView modelAndView = new ModelAndView("redirect:/users");
        return modelAndView;
    }

}
