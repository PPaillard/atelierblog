package fr.wcs.atelierblog.controller;

import fr.wcs.atelierblog.dto.ArticleDto;
import fr.wcs.atelierblog.entity.Article;
import fr.wcs.atelierblog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping
    public List<Article> getAll(){
        return articleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Article getById(@PathVariable int id){
        /*Optional<Article> optionalArticle = articleRepository.findById(id);

        if(optionalArticle.isPresent()) return optionalArticle.get();
        return null;*/

        /*Optional<Article> optionalArticle = articleRepository.findById(id);

        return optionalArticle.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );*/
        // FindbyId renvoi un optional
        // Sur l'optional, soit
        return articleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }


    @PostMapping
    public Article create(@RequestBody @Valid ArticleDto articleDto) {
        Article article = new Article();
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        return articleRepository.save(article);
    }

    @PutMapping("/{id}")
    public Article update(@PathVariable Integer id, @RequestBody @Valid ArticleDto articleDto){
        Article articleToUpdate =  articleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        articleToUpdate.setTitle(articleDto.getTitle());
        articleToUpdate.setContent(articleDto.getContent());
        return articleRepository.save(articleToUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        try {
            articleRepository.deleteById(id);
        } catch (Exception exception ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
