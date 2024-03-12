package com.romaincaron.journalize.api;

import com.romaincaron.journalize.model.Article;
import com.romaincaron.journalize.service.ArticleService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleRestController {
    private final ArticleService articleService;

    @Autowired
    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<List<Article>> getArticles()
    {
        List<Article> articles = articleService.getArticles();
        return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Article> getArticle(@PathVariable("id") long id) {
        Article article = articleService.getArticle(id);
        return new ResponseEntity<Article>(article, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Article> add(@RequestBody Article article)
    {
        Article newArticle = articleService.persist(article);
        return new ResponseEntity<Article>(newArticle, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Article> update(@RequestBody Article article)
    {
        Article updatedArticle = articleService.update(article);
        return new ResponseEntity<Article>(updatedArticle, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        articleService.delete(id);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
