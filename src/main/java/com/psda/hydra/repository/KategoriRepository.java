package com.psda.hydra.repository;


import com.psda.hydra.model.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

public interface KategoriRepository extends JpaRepository<Kategori, Long> {

}
