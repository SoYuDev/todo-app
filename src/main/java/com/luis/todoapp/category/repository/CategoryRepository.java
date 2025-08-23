package com.luis.todoapp.category.repository;

import com.luis.todoapp.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
