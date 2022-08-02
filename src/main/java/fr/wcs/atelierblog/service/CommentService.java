package fr.wcs.atelierblog.service;

import fr.wcs.atelierblog.dto.CommentDto;
import fr.wcs.atelierblog.entity.Article;
import fr.wcs.atelierblog.entity.Comment;
import fr.wcs.atelierblog.repository.ArticleRepository;
import fr.wcs.atelierblog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ArticleService articleService;

    public List<Comment> getCommentsByIdArticle(int id) {
        Article article = articleService.getById(id);
        return article.getComments();
    }

    public Comment createCommentByIdArticle(int id, CommentDto commentDto) {
        Article article = articleService.getById(id);
        Comment comment = new Comment(commentDto.getContent(), article);
        return commentRepository.save(comment);
    }

    public Comment updateCommentById(int idcomment, CommentDto commentDto) {
        Comment comment = commentRepository.findById(idcomment).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        comment.setContent(commentDto.getContent());
        return commentRepository.save(comment);
    }

    public void deleteById(int idComment) {
        try {
            commentRepository.deleteById(idComment);
        } catch (Exception exception ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
