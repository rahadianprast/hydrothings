package com.psda.hydra.repository;


import com.psda.hydra.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

public interface LevelRepository extends JpaRepository<Level, Long> {

    @Async
    Level findTopBySungaiOrderByCreatedDesc(Level level);
}
