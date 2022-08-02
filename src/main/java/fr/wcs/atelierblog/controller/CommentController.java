package fr.wcs.atelierblog.controller;

import fr.wcs.atelierblog.dto.CommentDto;
import fr.wcs.atelierblog.entity.Article;
import fr.wcs.atelierblog.entity.Comment;
import fr.wcs.atelierblog.repository.ArticleRepository;
import fr.wcs.atelierblog.repository.CommentRepository;
import fr.wcs.atelierblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/articles/{id}/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public List<Comment> getAllCommentsByArticle(@PathVariable int id){
        return commentService.getCommentsByIdArticle(id);
    }

    @PostMapping
    public Comment createCommentByArticle(@PathVariable int id, @RequestBody @Valid CommentDto commentDto){
        return commentService.createCommentByIdArticle(id, commentDto);
    }

    @PutMapping("/{idcomment}")
    public Comment updateCommentById(@PathVariable int idcomment,  @RequestBody @Valid CommentDto commentDto){
        return commentService.updateCommentById(idcomment, commentDto);
    }

    @DeleteMapping("/{idComment}")
    public void deleteById(@PathVariable int idComment){
        commentService.deleteById(idComment);
    }
}
