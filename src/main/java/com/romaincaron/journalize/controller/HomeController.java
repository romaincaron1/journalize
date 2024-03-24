package com.romaincaron.journalize.controller;

import com.romaincaron.journalize.model.Article;
import com.romaincaron.journalize.model.Category;
import com.romaincaron.journalize.model.Tag;
import com.romaincaron.journalize.service.ArticleService;
import com.romaincaron.journalize.service.CategoryService;
import com.romaincaron.journalize.service.TagService;
import com.romaincaron.journalize.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    private ArticleService articleService;
    private TagService tagService;
    private CategoryService categoryService;
    private UserSecurityService userSecurityService;
    @Autowired
    public HomeController(ArticleService articleService, TagService tagService, CategoryService categoryService, UserSecurityService userSecurityService) {
        this.articleService = articleService;
        this.tagService = tagService;
        this.categoryService = categoryService;
        this.userSecurityService = userSecurityService;
    }

    @GetMapping("")
    public String home(Model model) {
        // Categories
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);

        // Top 5 articles par catégorie
        Map<Category, List<Article>> top5ArticlesByCategory = new HashMap<>();
        for (Category category : categories) {
            List<Article> top5Articles = articleService.getTopArticlesByCategory(category, 5);
            top5ArticlesByCategory.put(category, top5Articles);
        }
        model.addAttribute("top5ArticlesByCategory", top5ArticlesByCategory);

        // lastArticles
        List<Article> lastArticles = articleService.getLast5Articles();
        model.addAttribute("lastArticles", lastArticles);
        model.addAttribute("user", userSecurityService.getCurrentUser());
        return "index";
    }

    @GetMapping("/recent")
    public String recent(Model model) {
        List<Article> lastArticles = articleService.getLastArticles();
        List<Article> last5Articles = articleService.getLast5Articles();
        model.addAttribute("articles", lastArticles);
        model.addAttribute("last5Articles", last5Articles);
        model.addAttribute("user", userSecurityService.getCurrentUser());
        return "recent";
    }

}
