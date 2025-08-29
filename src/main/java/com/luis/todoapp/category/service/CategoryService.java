package com.luis.todoapp.category.service;

import com.luis.todoapp.category.model.Category;
import com.luis.todoapp.category.repository.CategoryRepository;
import com.luis.todoapp.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final TaskService taskService;

    public List<Category> findAll() {
        return categoryRepository.findAll(Sort.by("title").ascending());
    }

    public void deleteById(Long id) {
        if (id != 1L) {
            Category oldCategory = categoryRepository.getReferenceById(id);
            Category mainCategory = categoryRepository.getReferenceById(1L);
            taskService.updateCategory(oldCategory, mainCategory);
            categoryRepository.deleteById(id);
        }
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

}
