package com.romaincaron.journalize.controller;

import com.romaincaron.journalize.model.Article;
import com.romaincaron.journalize.model.Category;
import com.romaincaron.journalize.model.Tag;
import com.romaincaron.journalize.service.ArticleService;
import com.romaincaron.journalize.service.CategoryService;
import com.romaincaron.journalize.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ArticleController {
    private ArticleService articleService;
    private TagService tagService;
    private CategoryService categoryService;

    public ArticleController(ArticleService articleService, TagService tagService, CategoryService categoryService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }

    @GetMapping("/articles/show")
    public String showArticle(@RequestParam Long articleId, Model model) {
        Article article = articleService.getArticle(articleId);
        article.incrementImpressions();
        articleService.persist(article);
        model.addAttribute("article", article);
        return "article/show";
    }

    @GetMapping("/articles/add")
    public String addArticle(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) Long articleId, Model model) {
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("tags", tagService.getTags());
        if (articleId != null) {
            model.addAttribute("article", articleService.getArticle(articleId));
        }
        return "article/add";
    }

    @PostMapping("/articles/add")
    public String addArticle(@RequestParam String title, @RequestParam String content, @RequestParam Long categoryId, @RequestParam(required = false) Long[] tags){
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCategory(categoryService.getCategory(categoryId));
        if (tags != null) {
            for (Long tagId : tags) {
                Tag tag = tagService.getTag(tagId);
                article.addTag(tag);
                tag.addArticle(article);
                tagService.persist(tag);
            }
        }
        article.setUser(null);
        article.setDate(new Date());
        article.setUpdatedAtNow();

        articleService.persist(article);
        return "redirect:/";
    }

    @PostMapping("/articles/edit")
    public String editArticle(@RequestParam Long articleId, @RequestParam String title, @RequestParam String content, @RequestParam Long categoryId, @RequestParam(required = false) Long[] tags) {
        Article article = articleService.getArticle(articleId);
        article.setTitle(title);
        article.setContent(content);
        article.setCategory(categoryService.getCategory(categoryId));

        List<Tag> tagsToRemove = new ArrayList<>(article.getTags());

        for (Tag tag : tagsToRemove) {
            tag.removeArticle(article);
            article.removeTag(tag);
            tagService.persist(tag);
        }

        if (tags != null) {
            for (Long tagId : tags) {
                Tag tag = tagService.getTag(tagId);
                article.addTag(tag);
                tag.addArticle(article);
                tagService.persist(tag);
            }
        }

        article.setUpdatedAtNow();
        articleService.persist(article);
        return "redirect:/";
    }

    @GetMapping("/articles/delete")
    public String deleteArticle(@RequestParam Long articleId) {
        articleService.delete(articleId);
        return "redirect:/";
    }

    @GetMapping("/categories/articles")
    public String showArticlesByCategory(@RequestParam Long categoryId, Model model) {
        Category category = categoryService.getCategory(categoryId);
        List<Article> articles = articleService.getArticlesByCategory(category);
        List<Article> lastArticles = articleService.getLast5Articles();
        model.addAttribute("lastArticles", lastArticles);
        model.addAttribute("category", category);
        model.addAttribute("articles", articles);
        return "article/articles";
    }

}
