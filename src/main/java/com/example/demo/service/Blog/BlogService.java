package com.example.demo.service.Blog;

import com.example.demo.model.Blog;

import java.io.IOException;


public interface BlogService {
    Iterable<Blog> findAll();

    Blog findBlogById(Long id);

    void save(Blog blog);

    void remove(Long id);

    void saveContent(String content_id, String content) throws IOException;

    String getContent(String content_id) throws IOException;
}
