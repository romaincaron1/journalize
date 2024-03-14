package com.romaincaron.journalize.service;

import com.romaincaron.journalize.model.Article;
import com.romaincaron.journalize.model.Category;

import java.util.List;

public interface ArticleService {
    public List<Article> getArticles();
    public Article persist(Article article);
    public Article getArticle(Long id);
    public Article update(Article article);
    public void delete(Long id);
    public void deleteAll();
    public List<Article> getLast5Articles();
    List<Article> getTopArticlesByCategory(Category category, int i);
    List<Article> getArticlesByCategory(Category category);
    public List<Article> getLastArticles();
}
