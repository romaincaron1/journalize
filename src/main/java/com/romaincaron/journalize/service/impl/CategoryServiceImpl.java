package com.romaincaron.journalize.service.impl;

import com.romaincaron.journalize.model.Category;
import com.romaincaron.journalize.repository.CategoryRepository;
import com.romaincaron.journalize.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category persist(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category getCategory(Long id) {
        return this.categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public Category update(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.findById(id).orElseThrow();
        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }
}
