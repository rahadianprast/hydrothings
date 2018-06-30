package com.psda.hydra.repository;

import com.psda.hydra.model.Jenis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JenisRepository extends JpaRepository<Jenis, Long> {

    Optional<Jenis> findByType(String type);
    Boolean existsByType(String type);
}
