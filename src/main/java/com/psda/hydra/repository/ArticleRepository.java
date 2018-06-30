package com.psda.hydra.repository;


import com.psda.hydra.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findById(Long id);
    @Async
    Page<Article> findCreatedBy(Long userId, Pageable pageable);
}
