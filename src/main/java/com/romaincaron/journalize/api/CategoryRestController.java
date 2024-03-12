package com.romaincaron.journalize.api;

import com.romaincaron.journalize.model.Category;
import com.romaincaron.journalize.model.Category;
import com.romaincaron.journalize.service.ArticleService;
import com.romaincaron.journalize.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories()
    {
        List<Category> categories = categoryService.getCategories();
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getArticle(@PathVariable("id") long id) {
        Category category = categoryService.getCategory(id);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category)
    {
        Category newArticle = categoryService.persist(category);
        return new ResponseEntity<Category>(newArticle, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category)
    {
        Category updatedArticle = categoryService.update(category);
        return new ResponseEntity<Category>(updatedArticle, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        categoryService.delete(id);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
