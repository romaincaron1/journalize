package com.romaincaron.journalize.controller;

import com.romaincaron.journalize.model.Article;
import com.romaincaron.journalize.model.Category;
import com.romaincaron.journalize.model.Tag;
import com.romaincaron.journalize.service.ArticleService;
import com.romaincaron.journalize.service.CategoryService;
import com.romaincaron.journalize.service.TagService;
import com.romaincaron.journalize.service.UserSecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@Controller
public class AdminController {
    CategoryService categoryService;
    TagService tagService;
    ArticleService articleService;
    UserSecurityService UserSecurityService;

    public AdminController(CategoryService categoryService, TagService tagService, ArticleService articleService, UserSecurityService UserSecurityService) {
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.articleService = articleService;
        this.UserSecurityService = UserSecurityService;
    }

    @GetMapping("/admin")
    public String index(Model model) {
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("tags", tagService.getTags());
        model.addAttribute("user", UserSecurityService.getCurrentUser());
        return "admin/index";
    }

    @PostMapping("/admin/categories/save")
    public String saveCategory(@RequestParam String name, @RequestParam String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        categoryService.persist(category);
        return "redirect:/admin";
    }

    @GetMapping("/admin/categories/delete")
    public String deleteCategory(@RequestParam Long categoryId) {
        categoryService.delete(categoryId);
        return "redirect:/admin";
    }

    @PostMapping("/admin/tags/save")
    public String saveTag(@RequestParam String name) {
        Tag tag = new Tag();
        tag.setTagName(name);
        tagService.persist(tag);
        return "redirect:/admin";
    }

    @GetMapping("/admin/tags/delete")
    public String deleteTag(@RequestParam Long tagId) {
        Tag tag = tagService.getTag(tagId);
        if (tag != null) {
            for (Article article : tag.getArticles()) {
                article.getTags().remove(tag);
                articleService.persist(article);
            }
            tagService.delete(tagId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/categories/edit")
    public String editCategory(@RequestParam Long categoryId, Model model) {
        Category category = categoryService.getCategory(categoryId);
        if (category == null) {
            return "redirect:/admin";
        }
        model.addAttribute("category", category);
        model.addAttribute("user", UserSecurityService.getCurrentUser());
        return "admin/editCategory";
    }

    @PostMapping("/admin/categories/edit")
    public String updateCategory(@RequestParam Long categoryId, @RequestParam String name, @RequestParam String description) {
        Category category = categoryService.getCategory(categoryId);
        category.setName(name);
        category.setDescription(description);
        categoryService.persist(category);
        return "redirect:/admin";
    }

    @GetMapping("/admin/tags/edit")
    public String editTag(@RequestParam Long tagId, Model model) {
        Tag tag = tagService.getTag(tagId);
        if (tag == null) {
            return "redirect:/admin";
        }
        model.addAttribute("tag", tag);
        model.addAttribute("user", UserSecurityService.getCurrentUser());
        return "admin/editTag";
    }

    @PostMapping("/admin/tags/edit")
    public String updateTag(@RequestParam Long tagId, @RequestParam String name) {
        Tag tag = tagService.getTag(tagId);
        tag.setTagName(name);
        tagService.persist(tag);
        return "redirect:/admin";
    }
}
