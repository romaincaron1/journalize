package com.romaincaron.journalize.repository;

import com.romaincaron.journalize.model.Article;
import com.romaincaron.journalize.model.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    public List<Article> findTop5ByOrderByDateDesc();
    @Query("SELECT a FROM article a WHERE a.category = ?1 ORDER BY a.impressions DESC")
    List<Article> findTopArticlesByCategoryOrderByImpressionsDesc(Category category, Pageable pageable);
    @Query("SELECT a FROM article a WHERE a.category = ?1 ORDER BY a.impressions DESC")
    List<Article> findArticlesByCategory(Category category);
}