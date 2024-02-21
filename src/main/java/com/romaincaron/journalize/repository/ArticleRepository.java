package com.romaincaron.journalize.repository;

import com.romaincaron.journalize.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}