package com.romaincaron.journalize.controller;

import com.romaincaron.journalize.model.Article;
import com.romaincaron.journalize.model.Category;
import com.romaincaron.journalize.service.ArticleService;
import com.romaincaron.journalize.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {
    private CategoryService categoryService;
    private ArticleService articleService;

    public CategoryController(CategoryService categoryService, ArticleService articleService) {
        this.categoryService = categoryService;
        this.articleService = articleService;
    }

    @GetMapping("/categories/articles")
    public String showArticlesByCategory(@RequestParam Long categoryId, Model model) {
        Category category = categoryService.getCategory(categoryId);
        List<Article> articles = articleService.getArticlesByCategory(category);
        List<Article> lastArticles = articleService.getLastArticles();
        model.addAttribute("lastArticles", lastArticles);
        model.addAttribute("category", category);
        model.addAttribute("articles", articles);
        return "category/articles";
    }
}
