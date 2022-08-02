package fr.wcs.atelierblog.controller;

import fr.wcs.atelierblog.dto.ArticleDto;
import fr.wcs.atelierblog.entity.Article;
import fr.wcs.atelierblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping
    public List<Article> getAll(){
        return articleService.findAll();
    }

    @GetMapping("/{id}")
    public Article getById(@PathVariable int id){
        return articleService.getById(id);
    }

    @PostMapping
    public Article create(@RequestBody @Valid ArticleDto articleDto) {
        return articleService.create(articleDto);
    }

    @PutMapping("/{id}")
    public Article update(@PathVariable Integer id, @RequestBody @Valid ArticleDto articleDto){
        return articleService.update(id, articleDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        articleService.deleteById(id);
    }
}
