package com.example.demo.controller;

import com.example.demo.model.Blog;
import com.example.demo.model.Category;
import com.example.demo.service.Blog.BlogService;
import com.example.demo.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;


@Controller
@RequestMapping("/blog")
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

    @GetMapping("")
    public ModelAndView listBlog(){
        Iterable<Blog> blogs = blogService.findAll();
        ModelAndView modelAndView = new ModelAndView("blog/create");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }


//    @GetMapping("")
//    public ModelAndView home(){
//        Iterable<Blog> blogs = blogService.findAll();
//        ModelAndView modelAndView = new ModelAndView("index");
//        modelAndView.addObject("blogs", blogs);
//        return modelAndView;
//    }

    @GetMapping("/create")
    public ModelAndView showCreateBlog(){
        ModelAndView modelAndView = new ModelAndView("blog/create");
        modelAndView.addObject("blogs", new Blog());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveBlog(@ModelAttribute("blog") Blog blog, @RequestParam ("content-html")String content) throws IOException {
        MultipartFile file = blog.getUpload();
        String image = file.getOriginalFilename();
        blog.setImage(image);
//        String path = environment.getProperty("upload.path").toString();
        try{
            FileCopyUtils.copy(file.getBytes(), new File(path + image));
        } catch (IOException e){
            e.printStackTrace();
        }
        String content_id = blog.getTitle().replaceAll("\\s+","-").toLowerCase();
        blogService.saveContent(content_id,content);
        blog.setContent_id(content_id);
        blogService.save(blog);

        ModelAndView modelAndView = new ModelAndView("blog/create");
        modelAndView.addObject("blogs", new Blog());
        return modelAndView;
    }


    @GetMapping("/edit/{id}")
    public ModelAndView showEditBlog(@PathVariable Long id) throws IOException {
        Blog blog = blogService.findBlogById(id);
        ModelAndView modelAndView;
        if(blog != null){
            modelAndView = new ModelAndView("blog/edit");
            modelAndView.addObject("blogs", blog);
            String content_id = blog.getContent_id();
            String content = blogService.getContent(content_id);
            modelAndView.addObject("content", content);
        } else {
            modelAndView = new ModelAndView("error");
        }

        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView updateBlog(@ModelAttribute("blog") Blog blog, @RequestParam("content-html") String content) throws IOException {
        String content_id = blogService.findBlogById(blog.getId()).getContent_id();
        blog.setContent_id(content_id);
        blogService.save(blog);
        blogService.saveContent(content_id, content);
        ModelAndView modelAndView = new ModelAndView("blog/edit");
        modelAndView.addObject("blogs", new Blog());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
//        Blog blog = blogService.findBlogById(id);
        blogService.remove(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/blog");
//        ModelAndView modelAndView = new ModelAndView("blog/delete");
//        modelAndView.addObject("blogs", blog);
        return modelAndView;
    }

//
//    @PostMapping("/delete/blog")
//    public String deleteCustomer(@ModelAttribute("blog") Blog blog){
//        blogService.remove(blog.getId());
//        return "redirect:/blog";
//    }

//    @GetMapping("/view/blog/{id}")
//    public ModelAndView

}
