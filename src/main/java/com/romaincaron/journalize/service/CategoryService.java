package com.romaincaron.journalize.service;

import com.romaincaron.journalize.model.Category;

import java.util.List;


public interface CategoryService {
    public List<Category> getCategories();
    public Category persist(Category category);
    public Category getCategory(Long id);
    public Category update(Category category);
    public void delete(Long id);
    public void deleteAll();
}
