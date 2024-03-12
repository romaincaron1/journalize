package com.romaincaron.journalize;

import com.romaincaron.journalize.model.Article;
import com.romaincaron.journalize.model.Category;
import com.romaincaron.journalize.model.Tag;
import com.romaincaron.journalize.model.User;
import com.romaincaron.journalize.service.ArticleService;
import com.romaincaron.journalize.service.CategoryService;
import com.romaincaron.journalize.service.TagService;
import com.romaincaron.journalize.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class JournalizeApplication {
    public static void main(String[] args) {
        SpringApplication.run(JournalizeApplication.class, args);
    }

    @Bean
    CommandLineRunner setupCategoriesAndTagsAndArticles(CategoryService categoryService, ArticleService articleService, UserService userService, TagService tagService) {
        return args -> {

        };
    }
}
