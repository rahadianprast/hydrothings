package com.psda.hydra.repository;

import com.psda.hydra.model.Rumus;
import com.psda.hydra.model.Sungai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

public interface RumusRepository extends JpaRepository<Rumus, Long> {

    @Async
    Rumus findTopBySungaiOrderByCreatedDesc(Sungai sungai);
}
