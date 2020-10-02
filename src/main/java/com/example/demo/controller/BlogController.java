package com.example.demo.controller;

import com.example.demo.model.Blog;
import com.example.demo.model.Category;
import com.example.demo.service.Blog.BlogService;
import com.example.demo.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Value("${upload.path}")
    private String path;

//    @Autowired
//    private Environment environment;

    @ModelAttribute("categorys")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @GetMapping("/blog")
    public ModelAndView listBlog(){
        Iterable<Blog> blogs = blogService.findAll();
        ModelAndView modelAndView = new ModelAndView("blog/list");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/create/blog")
    public ModelAndView showCreateBlog(){
        ModelAndView modelAndView = new ModelAndView("blog/create");
        modelAndView.addObject("blogs", new Blog());
        return modelAndView;
    }

    @PostMapping("/create/blog")
    public ModelAndView saveBlog(@ModelAttribute Blog blog){
        MultipartFile file = blog.getUpload();
        String image = file.getOriginalFilename();
        blog.setImage(image);
//        String path = environment.getProperty("upload.path").toString();
        try{
            FileCopyUtils.copy(file.getBytes(), new File(path + image));
        } catch (IOException e){
            e.printStackTrace();
        }
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("blog/create");
        modelAndView.addObject("blogs", new Blog());
        return modelAndView;
    }


    @GetMapping("edit/blog/{id}")
    public ModelAndView showEditBlog(@PathVariable Long id){
        Blog blog = blogService.findBlogById(id);
        ModelAndView modelAndView = new ModelAndView("blog/edit");
        modelAndView.addObject("blogs", blog);
        return modelAndView;
    }

    @PostMapping("edit/blog")
    public ModelAndView updateBlog(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("blog/edit");
        modelAndView.addObject("blogs", new Blog());
        return modelAndView;
    }

    @GetMapping("/delete/blog/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Blog blog = blogService.findBlogById(id);
        ModelAndView modelAndView = new ModelAndView("blog/delete");
        modelAndView.addObject("blogs", blog);
        return modelAndView;
    }


    @PostMapping("/delete/blog")
    public String deleteCustomer(@ModelAttribute("blog") Blog blog){
        blogService.remove(blog.getId());
        return "redirect:/blog";
    }

}
