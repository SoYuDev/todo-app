package com.luis.todoapp.task.repository;

import com.luis.todoapp.category.model.Category;
import com.luis.todoapp.task.model.Task;
import com.luis.todoapp.user.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAuthor(User user, Sort sort);

    List<Task> findByCategory(Category category);
}
