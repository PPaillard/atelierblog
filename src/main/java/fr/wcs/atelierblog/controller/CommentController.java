package fr.wcs.atelierblog.controller;

import fr.wcs.atelierblog.dto.CommentDto;
import fr.wcs.atelierblog.entity.Article;
import fr.wcs.atelierblog.entity.Comment;
import fr.wcs.atelierblog.repository.ArticleRepository;
import fr.wcs.atelierblog.repository.CommentRepository;
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
    public Comment createCommentByArticle(@PathVariable int id, @RequestBody @Valid CommentDto commentDto){
        Article article =  articleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        Comment comment = new Comment(commentDto.getContent(), article);
        /*Comment comment = new Comment();
        comment.setContent();
        comment.setArticle(article);*/

        return commentRepository.save(comment);
    }

    @PutMapping("/{idcomment}")
    public Comment updateCommentById(@PathVariable int idcomment,  @RequestBody @Valid CommentDto commentDto){
        Comment comment = commentRepository.findById(idcomment).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        comment.setContent(commentDto.getContent());
        return commentRepository.save(comment);
    }

    @DeleteMapping("/{idComment}")
    public void deleteById(@PathVariable int idComment){
        try {
            commentRepository.deleteById(idComment);
        } catch (Exception exception ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
