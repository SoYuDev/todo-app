package com.luis.todoapp.tag.repository;

import com.luis.todoapp.tag.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    // Using Optional as a return value instead of Tag allows us to avoid returning null and reduce the risk of exceptions.
    Optional<Tag> findByText(String text);
}
