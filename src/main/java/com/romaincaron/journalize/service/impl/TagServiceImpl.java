package com.romaincaron.journalize.service.impl;

import com.romaincaron.journalize.model.Tag;
import com.romaincaron.journalize.repository.TagRepository;
import com.romaincaron.journalize.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getTags() {
        return this.tagRepository.findAll();
    }

    @Override
    public Tag persist(Tag tag) {
        return this.tagRepository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return this.tagRepository.findById(id).orElseThrow();
    }

    @Override
    public Tag update(Tag tag) {
        return this.tagRepository.save(tag);
    }

    @Override
    public void delete(Long id) {
        tagRepository.findById(id).orElseThrow();
        tagRepository.deleteById(id);
    }
}
