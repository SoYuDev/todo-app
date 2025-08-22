package com.luis.todoapp.tag.service;

import com.luis.todoapp.tag.model.Tag;
import com.luis.todoapp.tag.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    /*
    Receives a list of tags as Strings and for each one of them:
    - Checks if exists and retrieves it.
    - If it does not exist, creates it and save it.
    */
    public List<Tag> saveOrGet(List<String> tags) {
        List<Tag> result = new ArrayList<>();

        tags.forEach(tag -> {
            Optional<Tag> val = tagRepository.findByText(tag);
            result.add(val.orElseGet(() -> tagRepository.save(Tag.builder().text(tag).build())));
        });
        return result;
    }

    // Explicit way to write the previous function.
//    public List<Tag> saveOrGet(List<String> tags) {
//    List<Tag> result = new ArrayList<>();
//
//    for (String tag : tags) {
//        Optional<Tag> val = tagRepository.findByText(tag);
//
//        if (val.isPresent()) {
//            // If the tag exists, add it to the result list
//            result.add(val.get());
//        } else {
//            // If the tag does not exist, create, save, and add it
//            Tag newTag = Tag.builder().text(tag).build();
//            Tag savedTag = tagRepository.save(newTag);
//            result.add(savedTag);
//        }
//    }
//
//    return result;
//}

}
