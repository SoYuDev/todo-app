package com.luis.todoapp.category.service;

import com.luis.todoapp.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
// We could also use @RequiredArgsConstructor (Lombok) instead of typing the constructor manually.
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
