package com.psda.hydra.service;

import com.psda.hydra.model.Article;
import com.psda.hydra.payload.ArticleRequest;
import com.psda.hydra.model.Kategori;
import com.psda.hydra.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article createArticle(ArticleRequest articleRequest) {
        Article article = new Article();
        article.setJudul(articleRequest.getJudul());
        article.setBody(articleRequest.getBody());
        article.setGambar(articleRequest.getGambar());
        articleRequest.getKategoris().forEach(kategoriRequest-> {
            article.addKategori(new Kategori(kategoriRequest.getTag()));
        });
        return articleRepository.save(article);
    }

}
