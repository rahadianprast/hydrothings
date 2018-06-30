package com.psda.hydra.repository;

import com.psda.hydra.model.Kadaster;
import com.psda.hydra.model.Sungai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface SungaiRepository extends JpaRepository<Sungai, Long> {

    @Async
    Sungai findByKadaster(Kadaster kadaster);

    @Async
    Sungai findByKadaster_Id(Long kadasterId);
}
