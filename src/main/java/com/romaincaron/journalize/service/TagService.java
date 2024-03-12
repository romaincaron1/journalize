package com.romaincaron.journalize.service;

import com.romaincaron.journalize.model.Article;
import com.romaincaron.journalize.model.Tag;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TagService {
    public List<Tag> getTags();
    public Tag persist(Tag article);
    public Tag getTag(Long id);
    public Tag update(Tag article);
    public void delete(Long id);
    public void deleteAll();
    public List<Tag> getTopTags();
}
