package com.romaincaron.journalize.service;

import com.romaincaron.journalize.model.Article;

import java.util.List;

public interface ArticleService {
    public List<Article> getArticles();
    public Article persist(Article article);
    public Article getArticle(Long id);
    public Article update(Article article);
    public void delete(Long id);
}
