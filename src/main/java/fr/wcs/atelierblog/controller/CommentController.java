package fr.wcs.atelierblog.controller;

import fr.wcs.atelierblog.entity.Article;
import fr.wcs.atelierblog.entity.Comment;
import fr.wcs.atelierblog.repository.ArticleRepository;
import fr.wcs.atelierblog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/articles/{id}/comments")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping
    public List<Comment> getAllCommentsByArticle(@PathVariable int id){
        Article article =  articleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        /*return commentRepository.findByArticle(article);*/
        return article.getComments();
    }

    @PostMapping
    public Comment createCommentByArticle(@PathVariable int id, @RequestBody Comment comment){
        Article article =  articleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        comment.setArticle(article);
        commentRepository.save(comment);

        return comment;
    }
}
