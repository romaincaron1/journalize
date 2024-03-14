package com.romaincaron.journalize.service.impl;

import com.romaincaron.journalize.model.Article;
import com.romaincaron.journalize.model.Category;
import com.romaincaron.journalize.repository.ArticleRepository;
import com.romaincaron.journalize.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getArticles() {
        return this.articleRepository.findAll();
    }

    @Override
    public Article persist(Article article) {
        return this.articleRepository.save(article);
    }

    @Override
    public Article getArticle(Long id) {
        return this.articleRepository.findById(id).orElseThrow();
    }

    @Override
    public Article update(Article article) {
        return this.articleRepository.save(article);
    }

    @Override
    public void delete(Long id) {
        articleRepository.findById(id).orElseThrow();
        articleRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        articleRepository.deleteAll();
    }

    @Override
    public List<Article> getLast5Articles() {
        return articleRepository.findTop5ByOrderByDateDesc();
    }

    @Override
    public List<Article> getTopArticlesByCategory(Category category, int limit) {
        return articleRepository.findTopArticlesByCategoryOrderByImpressionsDesc(category, Pageable.ofSize(limit));
    }

    @Override
    public List<Article> getArticlesByCategory(Category category) {
        return articleRepository.findArticlesByCategory(category);
    }

    // findLastArticles
    @Override
    public List<Article> getLastArticles() {
        return articleRepository.findLastArticles();
    }
}
