package com.romaincaron.journalize.service.impl;

import com.romaincaron.journalize.model.Article;
import com.romaincaron.journalize.repository.ArticleRepository;
import com.romaincaron.journalize.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
