package com.example.demo.service.Category;

import com.example.demo.model.Category;
import com.example.demo.model.Category;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface CategoryService {
    Iterable<Category> findAll();

    Category findCategoryById(Long id);

    void save(Category category);

    void remove(Long id);
}
