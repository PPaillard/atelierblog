package fr.wcs.atelierblog.repository;

import fr.wcs.atelierblog.entity.Article;
import fr.wcs.atelierblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByArticle(Article article);
}
