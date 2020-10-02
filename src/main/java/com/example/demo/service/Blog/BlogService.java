package com.example.demo.service.Blog;

import com.example.demo.model.Blog;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface BlogService {
    Iterable<Blog> findAll();

    Blog findBlogById(Long id);

    void save(Blog blog);

    void remove(Long id);
}
