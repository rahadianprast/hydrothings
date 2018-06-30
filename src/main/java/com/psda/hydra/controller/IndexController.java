package com.psda.hydra.controller;


import com.psda.hydra.model.*;
import com.psda.hydra.payload.ApiResponse;
import com.psda.hydra.payload.ArticleRequest;
import com.psda.hydra.repository.ArticleRepository;
import com.psda.hydra.repository.KategoriRepository;
import com.psda.hydra.repository.UserRepository;
import com.psda.hydra.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    KategoriRepository kategoriRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ArticleService articleService;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping
    public ResponseEntity<Page<Article>> getAllArticle(Pageable pageable){
        return new ResponseEntity<Page<Article>>(articleRepository.findAll(pageable), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Optional<Article> article = this.articleRepository.findById(id);
        if (article != null){
            return new ResponseEntity(this.articleRepository.findById(id), HttpStatus.OK);
        }else {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@Valid @RequestBody ArticleRequest request){
        Article article = articleService.createArticle(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(article.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "article Created Succesfully"));
    }

    @PostMapping("/kategori")
    public Kategori createKategori(@Valid @RequestBody Kategori kategori){
        return kategoriRepository.save(kategori);
    }
}
