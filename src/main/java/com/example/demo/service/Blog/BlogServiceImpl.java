package com.example.demo.service.Blog;

import com.example.demo.model.Blog;
import com.example.demo.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class BlogServiceImpl implements BlogService {

    public static final String CONTENT_SOURCE = "D:\\blog-springboot\\src\\main\\resources";
    @Autowired
    private BlogRepository blogRepository;
    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        String content_id = findBlogById(id).getContent_id();
        File file = new File(CONTENT_SOURCE + "\\templates\\content\\" + content_id + ".txt");
        if(file.delete()){
            System.out.println("Deleted");
        }
        blogRepository.deleteById(id);
    }

    @Override
    public void saveContent(String content_id, String content) throws IOException {
        File file = new File(CONTENT_SOURCE + "\\templates\\content\\" + content_id + ".txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(content);
        bufferedWriter.close();
    }

    @Override
    public String getContent(String content_id) throws IOException {
        String content = "";
        File file = new File(CONTENT_SOURCE + "\\templates\\content\\" + content_id + ".txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String text;
        while ((text = bufferedReader.readLine()) != null) {
            content += text;
        }
        return content;
    }
}
