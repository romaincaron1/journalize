package com.romaincaron.journalize.controller;

import com.romaincaron.journalize.model.Article;
import com.romaincaron.journalize.model.Comment;
import com.romaincaron.journalize.service.ArticleService;
import com.romaincaron.journalize.service.CommentService;
import com.romaincaron.journalize.service.UserSecurityService;
import com.romaincaron.journalize.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CommentController {
    CommentService commentService;
    UserSecurityService userSecurityService;
    ArticleService articleService;
    UserService userService;

    public CommentController(CommentService commentService, UserSecurityService userSecurityService, ArticleService articleService, UserService userService) {
        this.commentService = commentService;
        this.userSecurityService = userSecurityService;
        this.articleService = articleService;
        this.userService = userService;
    }

    @PostMapping("/comments/add")
    public String addComment(@RequestParam("articleId") Long articleId,
                             @RequestParam("content") String content,
                             Model model) {
        // Créez un nouveau commentaire
        Comment comment = new Comment();
        comment.setContent(content);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        String timestampString = dateFormat.format(currentDate);
        comment.setDate(timestampString);
        comment.setUser(userSecurityService.getCurrentUser());
        userSecurityService.getCurrentUser().addComment(comment);
        Article article = articleService.getArticle(articleId);
        comment.setArticle(article);
        article.addComment(comment);
        article.setUpdatedAtNow();

        // Sauvegardez le commentaire
        commentService.persist(comment);
        articleService.persist(article);
        userService.persist(userSecurityService.getCurrentUser());

        return "redirect:/articles/show?articleId=" + articleId;
    }
}
