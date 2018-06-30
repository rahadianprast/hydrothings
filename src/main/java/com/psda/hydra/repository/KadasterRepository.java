package com.psda.hydra.repository;


import com.psda.hydra.model.Kadaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;

public interface KadasterRepository extends JpaRepository<Kadaster, Long> {

    @Async
    Kadaster findByApiKey(String apiKey);

}
